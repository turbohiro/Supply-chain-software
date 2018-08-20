package com.changhong.system.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.common.ExceptionReturn;
import com.changhong.common.ExtGridReturn;
import com.changhong.common.ExtPager;
import com.changhong.common.ExtReturn;
import com.changhong.system.model.Employee;
import com.changhong.system.service.IEmployeeService;
import com.changhong.util.EncryptUtil;
import com.changhong.util.WebConstants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

@Controller  
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	/**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String emp() {
        return "system/user";
    }
    
    /**
     * 查找所有的用户
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object all(ExtPager pager, @RequestParam(required = false, defaultValue = "") String realName) {
        try {
            PageHelper.startPage(pager.getStart(), pager.getLimit());
            List<Employee> list = employeeService.queryAll(realName==null?"":realName);
            PageInfo<Employee> pageInfo = new PageInfo<>(list);
            return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
            
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 用户修改密码页面
     */
    @RequestMapping(value = "/changepwd", method = RequestMethod.GET)
    public String changePwd() {
        return "system/changepwd";
    }

    /**
     * 修改自己的密码
     */
    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    @ResponseBody
    public Object changePassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String comparePassword,
                                 @RequestParam String userId, HttpSession session) {
        try {
            if (null == userId) {
                return new ExtReturn(false, "用户编号不能为空！");
            }
            if (StringUtils.isBlank(oldPassword)) {
                return new ExtReturn(false, "原密码不能为空！");
            }
            if (StringUtils.isBlank(newPassword)) {
                return new ExtReturn(false, "新密码不能为空！");
            }
            if (StringUtils.isBlank(comparePassword)) {
                return new ExtReturn(false, "确认密码不能为空！");
            }
            if (!comparePassword.equals(newPassword)) {
                return new ExtReturn(false, "两次输入的密码不一致！");
            }
            Employee user = (Employee) session.getAttribute(WebConstants.CURRENT_USER);
            Map<String, Object> parameters = Maps.newHashMap();
            parameters.put("userId", userId);
            // 传入的所有密码已经通过md5加密过一次且为小写
            parameters.put("oldPassword", oldPassword);
            parameters.put("newPassword", newPassword);

            // 比较原密码
            if (!userId.equals(user.getEmpcode()) || !EncryptUtil.match(oldPassword, user.getEmppass())) {
                return new ExtReturn(false, "原密码不正确！请重新输入！");
            }

            employeeService.updateUserPassword(parameters);
            session.removeAttribute(WebConstants.CURRENT_USER);
            session.invalidate();
            return new ExtReturn(true, "修改密码成功！请重新登录！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 获取用户的所有角色
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Object myRole(@PathVariable String userId) {
        try {
            return employeeService.selectEmpRoleByUserId(userId);
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 重置用户的密码
     */
    @RequestMapping("/reset/{userId}")
    @ResponseBody
    public Object resetPassword(@PathVariable String userId) {
        try {
            if (null == userId) {
                return new ExtReturn(false, "用户主键不能为空！");
            }
            String result = employeeService.resetPwdByPrimaryKey(userId);
            if ("01".equals(result)) {
                return new ExtReturn(true, "重置密码成功！");
            } else if ("00".equals(result)) {
                return new ExtReturn(false, "重置密码失败！");
            } else {
                return new ExtReturn(false, result);
            }
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 删除该用户
     */
    @RequestMapping("/del/{userId}")
    @ResponseBody
    public Object delete(@PathVariable String userId, HttpSession session) {
        try {
            if (null == userId) {
                return new ExtReturn(false, "用户主键不能为空！");
            }
            // 不能删除自己
            Employee user = (Employee) session.getAttribute(WebConstants.CURRENT_USER);
            if (userId.equals(user.getEmpcode())) {
                return new ExtReturn(false, "不能删除自己的帐号！");
            }
            employeeService.deleteByPrimaryKey(userId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 验证用户名是否注册
     */
    @RequestMapping("/validate")
    @ResponseBody
    public Object validateAccount(@RequestParam(value = "account", required = false, defaultValue = "") String account,
                                  @RequestParam String userId) {
        try {
            if (StringUtils.isBlank(account)) {
                return new ExtReturn(false, "帐号不能为空!");
            }
            boolean result = employeeService.validateAccount(account);
            if (result) {
                return new ExtReturn(true, "帐号未被注册！");
            } else {
                return new ExtReturn(false, "帐号已经被注册！请重新填写!");
            }
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 保存用户信息
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(Employee user, @RequestParam Collection<String> roleIds) {
        try {
            if (roleIds == null || roleIds.size() == 0) {
                return new ExtReturn(false, "请至少选择一个角色！");
            }
            if (StringUtils.isBlank(user.getEmpename())) {
                return new ExtReturn(false, "帐号不能为空！");
            }
            
            user.setOrganizationcode("1");
            user.setDepartmentcode("1");
            user.setCompanycode("1");
            
            String result = employeeService.saveUser(user, roleIds);
            if ("01".equals(result)) {
                return new ExtReturn(true, "保存成功！");
            } else {
                return new ExtReturn(false, result);
            }
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 编辑用户
     */
    @RequestMapping(value = "/myinfo", method = RequestMethod.GET)
    public String myinfo() {
        return "system/myinfo";
    }

    /**
     * 保存用户自己编辑的信息
     */
    @RequestMapping(value = "/myinfo", method = RequestMethod.POST)
    @ResponseBody
    public Object saveMyinfo(Employee user,HttpSession session) {
        try {
            if (user == null) {
                return new ExtReturn(false, "用户不能为空！");
            }
            if (user.getEmpcode() == null) {
                return new ExtReturn(false, "用户ID不能为空！");
            }
            employeeService.updateByPrimaryKey(user);
            session.removeAttribute(WebConstants.CURRENT_USER);
            session.invalidate();
            return new ExtReturn(true, "用户信息更新成功！请重新登录！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
}

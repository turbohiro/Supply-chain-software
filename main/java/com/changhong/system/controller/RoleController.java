package com.changhong.system.controller;

import java.util.List;

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
import com.changhong.system.model.Role;
import com.changhong.system.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller  
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	/**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String role() {
        return "system/role";
    }
    
    /**
     * 查找所有的角色
     */
    @RequestMapping("/all")
    @ResponseBody
    public Object all(ExtPager pager, @RequestParam(required = false) String roleName) {
        PageHelper.startPage(pager.getStart(), pager.getLimit()); 
        List<Role> list = roleService.queryAll(roleName==null?"":roleName);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 保存角色信息
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(Role role) {
        try {
            if (role == null) {
                return new ExtReturn(false, "角色不能为空!");
            }
            if (StringUtils.isBlank(role.getRolename())) {
                return new ExtReturn(false, "角色名称不能为空!");
            }
            
            role.setIsmanager(false);
            role.setCompanycode("1");
            
            if (StringUtils.isBlank(role.getId())) {
            	String id =  java.util.UUID.randomUUID().toString();
            	role.setId(id);
            	roleService.insert(role);
            }else{
            	roleService.updateByPrimaryKey(role);
            }
            
            return new ExtReturn(true, "保存成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    /**
     * 删除该角色
     */
    @RequestMapping("/del/{roleId}")
    @ResponseBody
    public Object delete(@PathVariable String roleId) {
        try {
            if (null == roleId) {
                return new ExtReturn(false, "角色主键不能为空！");
            }
            roleService.deleteByPrimaryKey(roleId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
}

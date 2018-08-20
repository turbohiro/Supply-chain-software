package com.changhong.system.controller;

import com.changhong.common.ExceptionReturn;
import com.changhong.common.ExtReturn;
import com.changhong.common.Tree;
import com.changhong.system.model.Employee;
import com.changhong.system.service.IEmployeeService;
import com.changhong.system.service.IModuleService;
import com.changhong.util.WebConstants;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;

/**
 * 用户登录相关
 *
 * @author sw.j
 * @date 2018-5-2 上午11:45:00
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private IEmployeeService sysEmployeeService;
    @Autowired
    private IModuleService sysModelService;
    /**
     * 限制时间
     */
    @Value("${limit.millis:3600000}")
    private Long millis;

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(HttpSession session, Locale locale) {
        try {
            session.removeAttribute(WebConstants.CURRENT_USER);
            session.invalidate();
            return new ExtReturn(true, "退出系统成功！");
        } catch (Exception e) {
            logger.error(WebConstants.EXCEPTION, e);
            return new ExceptionReturn(e);
        }
    }

    /**
     * 用户菜单treeMenu的数据
     *
     * @param session
     * @param response
     * @return
     */
    @RequestMapping("/treeMenu")
    @ResponseBody
    public Object treeMenu(HttpSession session, HttpServletResponse response) {
        try {
        	Employee user = (Employee) session.getAttribute(WebConstants.CURRENT_USER);
            // 得到的是根菜单
        	Tree tree = this.sysModelService.queryModulesByUser(user);
            // 返回根菜单下面的子菜单
            return tree.getChildren();
        } catch (Exception e) {
            logger.error(WebConstants.EXCEPTION, e);
            return new ExceptionReturn(e);
        }
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam String account, @RequestParam String password, HttpSession session, HttpServletRequest request) {
        try {
            if (StringUtils.isBlank(account)) {
                return new ExtReturn(false, "帐号不能为空！");
            }
            if (StringUtils.isBlank(password)) {
                return new ExtReturn(false, "密码不能为空！");
            }
            Map<String, Object> parameters = Maps.newHashMap();
            parameters.put("account", account);
            parameters.put("passwordIn", password);
            String result = sysEmployeeService.selectByAccount(parameters);
            if ("01".equals(result)) {
            	Employee baseUser = (Employee) parameters.get("baseUser");
                session.setAttribute(WebConstants.CURRENT_USER, baseUser);
                logger.info("{}登陆成功", baseUser.getEmpname());
                return new ExtReturn(true, "success");
            } else if ("00".equals(result)) {
                return new ExtReturn(false, "用户名或者密码错误!");
            } else {
                return new ExtReturn(false, result);
            }
        } catch (Exception e) {
            logger.error(WebConstants.EXCEPTION, e);
            if (StringUtils.isNotBlank(e.getMessage())) {
                return new ExtReturn(false, e.getMessage());
            } else {
                return new ExceptionReturn(e);
            }
        }
    }

    /**
     * 转到找回用户密码页面
     */
    @RequestMapping(value = "/findpwd", method = RequestMethod.GET)
    public String findpwd() {
        return "system/findpwd";
    }

}

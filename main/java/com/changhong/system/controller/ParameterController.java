package com.changhong.system.controller;

import java.util.Date;
import java.util.List;

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
import com.changhong.system.model.Parameter;
import com.changhong.system.service.IParameterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller  
@RequestMapping("/parameter")
public class ParameterController {

	@Autowired
	private IParameterService parameterService;
	
	/**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String parameter() {
    	return "system/parameter";
	}
	
    /**
     * 所有系统字段
     */
    @RequestMapping("/all")
    @ResponseBody
    public Object all(ExtPager pager, @RequestParam(required = false) String fieldName) {
        try {
            PageHelper.startPage(pager.getStart(), pager.getLimit());
            List<Parameter> list = parameterService.queryAll(fieldName==null?"":fieldName);
            PageInfo<Parameter> pageInfo = new PageInfo<>(list);
            return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(Parameter sp) {
        try {
            if (sp == null) {
                return new ExtReturn(false, "系统字段不能为空！");
            }
            if (StringUtils.isBlank(sp.getViewtype())) {
                return new ExtReturn(false, "字段不能为空！");
            }
            if (StringUtils.isBlank(sp.getParameterdesc())) {
                return new ExtReturn(false, "字段名称不能为空！");
            }
            if (StringUtils.isBlank(sp.getParametervalue())) {
                return new ExtReturn(false, "字段值不能为空！");
            }
            if (StringUtils.isBlank(sp.getValuedesc())) {
                return new ExtReturn(false, "字段显示值不能为空！");
            }
            
            sp.setDefaultvalue("0");
            sp.setModifyuser("admin");
            sp.setModifydate(new Date());
            sp.setCompanycode("1");
            
            if (StringUtils.isBlank(sp.getId())) {
                String id =  java.util.UUID.randomUUID().toString();
                sp.setId(id);
                parameterService.insert(sp);
            } else {
            	parameterService.updateByPrimaryKey(sp);
            }
            
            return new ExtReturn(true, "保存成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
	
    /**
     * 删除
     */
    @RequestMapping("/del/{pId}")
    @ResponseBody
    public Object delete(@PathVariable String pId) {
        try {
            if (pId == null) {
                return new ExtReturn(false, "主键不能为空！");
            }
            parameterService.deleteByPrimaryKey(pId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
	
    /**
     * 内存同步
     */
    @RequestMapping("/synchro")
    @ResponseBody
    public Object synchro(HttpSession session) {
        try {
            session.getServletContext().removeAttribute("parameters");
            session.getServletContext().setAttribute("parameters", parameterService.selectAllPar());
            return new ExtReturn(true, "同步成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
}

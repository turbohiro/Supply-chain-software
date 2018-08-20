package com.changhong.appraise.controller;

import com.changhong.appraise.model.AppraiseScheme;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.changhong.appraise.service.AppraiseSchemeService;
import com.changhong.common.ExceptionReturn;
import com.changhong.common.ExtGridReturn;
import com.changhong.common.ExtPager;
import com.changhong.common.ExtReturn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;





@Controller  
@RequestMapping("/appraiseScheme")
public class AppraiseSchemeController {
	
	@Autowired
	private AppraiseSchemeService appraiseschemeService;
	
	/**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "appraise/appraiseScheme";
    }
    
    /**
     * 查询所有
     */
    @RequestMapping("/all")
    @ResponseBody
    public Object all(ExtPager pager, @RequestParam(required = false) String appraiseSchemeName) {
        try {
        	PageHelper.startPage(pager.getStart(), pager.getLimit());
            List<AppraiseScheme> list = appraiseschemeService.queryAll(appraiseSchemeName==null?"":appraiseSchemeName);
            PageInfo<AppraiseScheme> pageInfo = new PageInfo<>(list);
            return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 保存系统菜单信息
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(AppraiseScheme schemes) {
        try {
            if (schemes == null) {
                return new ExtReturn(false, "模块不能为空！");
            }
            if (StringUtils.isBlank(schemes.getSchemename())) {
                return new ExtReturn(false, "模块名称不能为空！");
            }
            
            if (StringUtils.isBlank(schemes.getId())) {
            	 String id =  java.util.UUID.randomUUID().toString();
            	 schemes.setId(id);
            	 appraiseschemeService.insert(schemes);
            }else{
            	appraiseschemeService.updateByPrimaryKey(schemes);
            }
            return new ExtReturn(true, "保存成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 删除该模块
     */
    @RequestMapping("/del/{schemeId}")
    @ResponseBody
    public Object delete(@PathVariable String schemeId) {
        try {
            if (null == schemeId) {
                return new ExtReturn(false, "模块主键不能为空！");
            }
            appraiseschemeService.deleteByPrimaryKey(schemeId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
}
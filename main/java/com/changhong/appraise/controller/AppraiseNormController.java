package com.changhong.appraise.controller;

import com.alibaba.fastjson.JSON;

import com.changhong.appraise.model.AppraiseNorm;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.changhong.common.Tree;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.changhong.appraise.service.AppraiseNormService;

@Controller  
@RequestMapping("/appraiseNorm")
public class AppraiseNormController {
	
	@Autowired
	private AppraiseNormService appraisenormService;
	/**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "appraise/appraiseNorm";
    }
    
    /**
     * 查找树形组织机构
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    public void all(HttpServletResponse response) throws IOException {
        Tree tree = appraisenormService.queryNorms();
        String json = JSON.toJSONString(tree.getChildren());
        // 加入check
        response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
    

    /**
     * 保存
     * hiddenValue 取comboxtree的隱藏值
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(AppraiseNorm norm,@RequestParam(required = false) String hiddenValue) {
        try {
            norm.setCompanycode("1");
            if(hiddenValue.contains("_chmesnorm_")){
            	//去除前缀
            	norm.setNormcodefather(hiddenValue.replace("_chmesnorm_", ""));
            }
            String result = appraisenormService.saveNorm(norm);
            if(result.equals("01")){
            	return new ExtReturn(true, "保存成功！");
            }else{
            	return new ExtReturn(false, "保存失败！");
            }
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    /**
     * 查找所有的机构
     */
    @RequestMapping("/all")
    @ResponseBody
    public Object all(ExtPager pager, @RequestParam(required = false) String roleName, @RequestParam(required = false) String normId) {
    	if(StringUtils.isBlank(normId)){
    		PageHelper.startPage(pager.getStart(), pager.getLimit()); 
    		List<AppraiseNorm> list = appraisenormService.queryAll(roleName);
    		PageInfo<AppraiseNorm> pageInfo = new PageInfo<>(list);
    		return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
    	}else{
    		PageHelper.startPage(pager.getStart(), pager.getLimit()); 
    		List<AppraiseNorm> list = appraisenormService.queryAllByNormId(normId);
    		PageInfo<AppraiseNorm> pageInfo = new PageInfo<>(list);
    		return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
    	}
    }
    
    
    
    /**
     * 删除该模块
     */
    @RequestMapping("/del/{normId}")
    @ResponseBody
    public Object delete(@PathVariable String normId) {
        try {
            if (null == normId) {
                return new ExtReturn(false, "模块主键不能为空！");
            }
            appraisenormService.deleteByPrimaryKey(normId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
}

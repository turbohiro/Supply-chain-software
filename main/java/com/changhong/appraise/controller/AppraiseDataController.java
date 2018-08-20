package com.changhong.appraise.controller;

import com.changhong.appraise.model.AppraiseDataList;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.changhong.appraise.model.AppraiseFileList;
import com.changhong.appraise.service.AppraiseDataListService;
import com.changhong.appraise.service.AppraiseFileListService;
import com.changhong.common.ExceptionReturn;
import com.changhong.common.ExtGridReturn;
import com.changhong.common.ExtPager;
import com.changhong.common.ExtReturn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller  
@RequestMapping("/appraiseData")
public class AppraiseDataController {
	
	@Autowired
	private AppraiseFileListService  appraisefileListService;
	@Autowired
	private AppraiseDataListService  appraisedataListService;
	
	/**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "appraise/appraiseData";
    }
    
    /**
     * 查询所有文件类文本
     */
    @RequestMapping("/all_file")
    @ResponseBody
    public Object all_file(ExtPager pager, @RequestParam(required = false) String appraisefileName) {
        try {
        	PageHelper.startPage(pager.getStart(), pager.getLimit());
            List<AppraiseFileList> list = appraisefileListService.queryAll(appraisefileName==null?"":appraisefileName);
            PageInfo<AppraiseFileList> pageInfo = new PageInfo<>(list);
            return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 查询所有数据类文本
     */
    @RequestMapping("/all_data")
    @ResponseBody
    public Object all_data(ExtPager pager, @RequestParam(required = false) String appraisedataName) {
        try {
        	PageHelper.startPage(pager.getStart(), pager.getLimit());
            List<AppraiseDataList> list = appraisedataListService.queryAll(appraisedataName==null?"":appraisedataName);
            PageInfo<AppraiseDataList> pageInfo = new PageInfo<>(list);
            return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    
    
    
    /**
     * 保存系统文本栏菜单信息
     */
    @RequestMapping("/save_file")
    @ResponseBody
    public Object save_file(AppraiseFileList files,@RequestParam(required = false) String hiddenValue2) {
        try {
        	files.setCompanycode("1");
        	if(hiddenValue2.contains("_chmesnorm_")){
            	//去除前缀
            	files.setNormcode(hiddenValue2.replace("_chmesnorm_", ""));
            }else{
            	files.setNormcode(hiddenValue2);
            }
            if (StringUtils.isBlank(files.getFilename())) {
                return new ExtReturn(false, "模块名称不能为空！");
            }
            
            if (StringUtils.isBlank(files.getId())) {
            	 String id =  java.util.UUID.randomUUID().toString();
            	 files.setId(id);
            	 appraisefileListService.insert(files);
            }else{
            	appraisefileListService.updateByPrimaryKey(files);
            }
            return new ExtReturn(true, "保存成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 保存系统数据栏菜单信息
     */
    @RequestMapping("/save_data")
    @ResponseBody
    public Object save_data(AppraiseDataList datas,@RequestParam(required = false) String hiddenValue1) {
        try {
        	datas.setCompanycode("1");
        	if(hiddenValue1.contains("_chmesnorm_")){
            	//去除前缀
            	datas.setNormcode(hiddenValue1.replace("_chmesnorm_", ""));
            }else{
            	datas.setNormcode(hiddenValue1);
            }
            if (StringUtils.isBlank(datas.getDatatype())) {
                return new ExtReturn(false, "模块名称不能为空！");
            }
            
            if (StringUtils.isBlank(datas.getId())) {
            	 String id =  java.util.UUID.randomUUID().toString();
            	 datas.setId(id);
            	 appraisedataListService.insert(datas);
            }else{
            	appraisedataListService.updateByPrimaryKey(datas);
            }
            return new ExtReturn(true, "保存成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 删除该数据模块
     */
    @RequestMapping("/del_data/{dataId}")
    @ResponseBody
    public Object delete_data(@PathVariable String dataId) {
        try {
            if (null == dataId) {
                return new ExtReturn(false, "模块主键不能为空！");
            }
            appraisedataListService.deleteByPrimaryKey(dataId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
    
    /**
     * 删除该文本模块
     */
    @RequestMapping("/del_file/{appraisefileId}")
    @ResponseBody
    public Object delete_file(@PathVariable String appraisefileId) {
        try {
            if (null == appraisefileId) {
                return new ExtReturn(false, "模块主键不能为空！");
            }
            appraisefileListService.deleteByPrimaryKey(appraisefileId);
            return new ExtReturn(true, "删除成功！");
        } catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }
}

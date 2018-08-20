package com.changhong.appraise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.common.ExceptionReturn;
import com.changhong.common.ExtGridReturn;
import com.changhong.common.ExtPager;
import com.changhong.common.ExtReturn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.changhong.appraise.service.AppraiseScoreService;
import com.changhong.appraise.service.AppraiseFileListService;
import com.changhong.appraise.model.AppraiseFileList;
import com.changhong.appraise.model.AppraiseScore;
import com.changhong.appraise.model.AppraiseDataList;
import com.changhong.appraise.service.AppraiseDataListService;
import com.changhong.appraise.service.AppraiseScorePrincipleService;
import com.changhong.appraise.model.AppraiseScorePrinciple;

@Controller  
@RequestMapping("/appraiseScore")
public class AppraiseScoreController {
	
	@Autowired
	private AppraiseScoreService appraisescoreService;
	
	@Autowired
	private AppraiseFileListService appraisefilelistService;
	
	@Autowired
	private AppraiseDataListService appraisedatalistService;
	
	@Autowired
	private AppraiseScorePrincipleService appraiseScorePrincipleService;
	

	/**
     * index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "appraise/appraiseScore";
    }
    
    /**
     * 查找指标编号对应的文件类数据
     */
    @RequestMapping("/querry_scorePrinciple")
    @ResponseBody
    public Object querry_scorePrinciple(@RequestParam(required = false) String normId) {	
    	AppraiseScorePrinciple principle = appraiseScorePrincipleService.queryAllByNormId(normId);	
    	return new ExtReturn(true,principle);  	
    }
    
    /**
     * 查找指标编号对应的文件类数据
     */
    @RequestMapping("/querry_file")
    @ResponseBody
    public Object querry_file(ExtPager pager, @RequestParam(required = false) String normId) {
    	
    		PageHelper.startPage(pager.getStart(), pager.getLimit()); 
    		List<AppraiseFileList> list = appraisefilelistService.queryAllByNormId(normId);
    		PageInfo<AppraiseFileList> pageInfo = new PageInfo<>(list);
    		return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());    	
    }
    
    /**
     * 查找指标编号对应的数据类类数据
     */
    @RequestMapping("/querry_data")
    @ResponseBody
    public Object querry_data(ExtPager pager, @RequestParam(required = false) String normId) {
    	
    		PageHelper.startPage(pager.getStart(), pager.getLimit()); 
    		List<AppraiseDataList> list = appraisedatalistService.queryAllByNormId(normId);
    		PageInfo<AppraiseDataList> pageInfo = new PageInfo<>(list);
    		return new ExtGridReturn(pageInfo.getTotal(), pageInfo.getList());    	
    }
    
    /**保存对应指标编号的专家给分数据**/
    @RequestMapping("/save")
    @ResponseBody
    public Object save(AppraiseScore score, @RequestParam(required = false) String normId) {
        try {
            score.setEmpCode("1");
            score.setCompanycode("1");  
       	 	String id =  java.util.UUID.randomUUID().toString();
       	 	score.setId(id);
       	 	score.setNormcode(normId);
        if ((appraisescoreService.queryAll(normId).size()==0)) {
       	 	appraisescoreService.insert(score);
        }else{
        	appraisescoreService.updateByPrimaryKey(score);
           }
            return new ExtReturn(true, "保存成功！");
      }catch (Exception e) {
            return new ExceptionReturn(e);
        }
    }

    
    /**保留专家打分界面过去保留的分数**/
    @RequestMapping("/all_score")
    @ResponseBody
    public Object all_score(@RequestParam(required = false) String normId) { 	
    		AppraiseScore score = appraisescoreService.queryAllByNormId(normId);
    		return new ExtReturn(true,score);    	
    }
    
    
}
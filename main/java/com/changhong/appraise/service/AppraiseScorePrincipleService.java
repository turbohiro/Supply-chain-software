package com.changhong.appraise.service;

import java.util.List;


import com.changhong.appraise.model.AppraiseScorePrinciple;

public interface AppraiseScorePrincipleService {
	
	int deleteByPrimaryKey(String id);       
	
	int insert(AppraiseScorePrinciple record);    
	
	int insertSelective(AppraiseScorePrinciple record);
	
	AppraiseScorePrinciple selectByPrimaryKey(String id);   
	
	List<AppraiseScorePrinciple> queryAll(String searchText);  
	
	int updateByPrimaryKeySelective(AppraiseScorePrinciple record);   
	
	int updateByPrimaryKey(AppraiseScorePrinciple record);  
	
	List<AppraiseScorePrinciple> selectAppraiseScorePrinciple();
	
	List<AppraiseScorePrinciple> selectBynormCode(String normCode);
	
	AppraiseScorePrinciple queryAllByNormId(String normId);
}

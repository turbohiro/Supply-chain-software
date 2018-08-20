package com.changhong.appraise.service;

import java.util.List;

import com.changhong.appraise.model.ListAnalyze;

public interface ListAnalyzeService {
    int deleteByPrimaryKey(int id);
	
	int insert(ListAnalyze record); 
	
	int insertSelective(ListAnalyze record);
	
	ListAnalyze selectByPrimaryKey(int id);
	
	List<ListAnalyze> queryAll(String searchText);
	
	int updateByPrimaryKeySelective(ListAnalyze record); 
	
	int updateByPrimaryKey(ListAnalyze record); 

}

package com.changhong.appraise.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.changhong.appraise.model.ListAnalyze;

@Mapper
@Repository
public interface ListAnalyzeMapper {
	
	int deleteByPrimaryKey(int id);
	
	int insert(ListAnalyze record); 
	
	int insertSelective(ListAnalyze record);
	
	ListAnalyze selectByPrimaryKey(int id);
	
	List<ListAnalyze> queryAll(String searchText);
	
	int updateByPrimaryKeySelective(ListAnalyze record); 
	
	int updateByPrimaryKey(ListAnalyze record); 

}

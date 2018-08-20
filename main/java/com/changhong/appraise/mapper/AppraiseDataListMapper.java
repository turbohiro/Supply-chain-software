package com.changhong.appraise.mapper;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.changhong.appraise.model.AppraiseDataList;




@Mapper
@Repository
public interface AppraiseDataListMapper {

	int deleteByPrimaryKey(String id);       
	
	int insert(AppraiseDataList record);    
	
	int insertSelective(AppraiseDataList record);
	
	AppraiseDataList selectByPrimaryKey(String id);   
	
	List<AppraiseDataList> queryAll(String searchText);  
	
	int updateByPrimaryKeySelective(AppraiseDataList record);   
	
	int updateByPrimaryKey(AppraiseDataList record); 
	
	List<AppraiseDataList> selectAppraiseDataLists();
	
	List<AppraiseDataList> selectBynormCode(String normCode);
}

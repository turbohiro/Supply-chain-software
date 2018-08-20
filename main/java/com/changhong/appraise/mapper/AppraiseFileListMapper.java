package com.changhong.appraise.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.changhong.appraise.model.AppraiseFileList;


@Mapper
@Repository
public interface AppraiseFileListMapper {
	
	int deleteByPrimaryKey(String id);       
	
	int insert(AppraiseFileList record);    
	
	int insertSelective(AppraiseFileList record);
	
	AppraiseFileList selectByPrimaryKey(String id);   
	
	List<AppraiseFileList> queryAll(String searchText);  
	
	int updateByPrimaryKeySelective(AppraiseFileList record);   
	
	int updateByPrimaryKey(AppraiseFileList record);  
	
	List<AppraiseFileList> selectAppraiseFileLists();
	
	List<AppraiseFileList> selectBynormCode(String normCode);
}

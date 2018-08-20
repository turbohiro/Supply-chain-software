package com.changhong.appraise.mapper;

import com.changhong.appraise.model.AppraiseScorePrinciple;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppraiseScorePrincipleMapper {
	
	int deleteByPrimaryKey(String id);       
	
	int insert(AppraiseScorePrinciple record);    
	
	int insertSelective(AppraiseScorePrinciple record);
	
	AppraiseScorePrinciple selectByPrimaryKey(String id);   
	
	List<AppraiseScorePrinciple> queryAll(String searchText);  
	
	int updateByPrimaryKeySelective(AppraiseScorePrinciple record);   
	
	int updateByPrimaryKey(AppraiseScorePrinciple record);  
	
	List<AppraiseScorePrinciple> selectAppraiseScorePrinciple();
	
	AppraiseScorePrinciple selectBynormCode(String normCode);

}

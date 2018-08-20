package com.changhong.appraise.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.changhong.appraise.model.AppraiseNorm;

@Mapper
@Repository
public interface AppraiseNormMapper {
	
	int deleteByPrimaryKey(String id);
	
	int insert(AppraiseNorm record); 
	
	int insertSelective(AppraiseNorm record);
	
	AppraiseNorm selectByPrimaryKey(String id);
	
	AppraiseNorm  selectBynormCode(String normCode);
	
	List<AppraiseNorm> queryAll(String searchText);
	
	int updateByPrimaryKeySelective(AppraiseNorm record); 
	
	int updateByPrimaryKey(AppraiseNorm record); 
	
	List<AppraiseNorm> selectAppraiseNormsByUser(String userId);
	
	List<AppraiseNorm> selectAppraiseNorms();
	
	List<AppraiseNorm> queryAllModelFather(String transflag);
	
	Map<String, String> selectAllPar();     	
	
	List<AppraiseNorm> selectNorms();
	
	List<AppraiseNorm> queryAllByNormId(String normId);
	
	String selectMaxId();

}

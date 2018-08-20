package com.changhong.appraise.mapper;

import com.changhong.appraise.model.AppraiseScheme;
import com.changhong.system.model.Parameter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface AppraiseSchemeMapper {
	
	int deleteByPrimaryKey(String id);
	
	int insert(AppraiseScheme record); 
	
	int insertSelective(AppraiseScheme record);
	
	AppraiseScheme selectByPrimaryKey(String id);
	
	List<AppraiseScheme> queryAll(String searchText);
	
	int updateByPrimaryKeySelective(AppraiseScheme record); 
	
	int updateByPrimaryKey(AppraiseScheme record); 
	
	List<AppraiseScheme> selectAppraiseSchemesByUser(String userId);
	
	List<AppraiseScheme> selectAppraiseSchemes();
	
	List<AppraiseScheme> queryAllModelFather(String transflag);
	
	Map<String, String> selectAllPar();     
	
	List<Parameter> findAll();

}

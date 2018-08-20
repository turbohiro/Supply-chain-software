package com.changhong.appraise.service;

import java.util.List;
import java.util.Map;

import com.changhong.appraise.model.AppraiseNorm;
import com.changhong.common.Tree;

public interface AppraiseNormService {
	
	int deleteByPrimaryKey(String id);

    int insert(AppraiseNorm record);                  //对应controller的“/save"

    int insertSelective(AppraiseNorm record);

    AppraiseNorm selectByPrimaryKey(String id);       
    
    List<AppraiseNorm> queryAll(String searchText);   //对应controller的"/all"

    int updateByPrimaryKeySelective(AppraiseNorm record);  

    int updateByPrimaryKey(AppraiseNorm record);     //对应controller的“/save"
    
    Tree queryAppraiseNorms();                 
    
    Map<String, Object> queryAllModelFather();
    
    String delete(String id);                  //对应controller层的"/delete"
    
    Tree queryNorms();                      //对应controller层的查找树形结构
    
    List<AppraiseNorm> queryAllByNormId(String normId);  //对应controller层的查找树形结构
    
    String saveNorm(AppraiseNorm record);  //对应controller层的查找树形结构

}

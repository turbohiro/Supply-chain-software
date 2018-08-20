package com.changhong.appraise.service;

import java.util.List;
import com.changhong.appraise.model.AppraiseDataList;



public interface AppraiseDataListService {
	
	int deleteByPrimaryKey(String id);

    int insert(AppraiseDataList record);                  //对应controller的“/save"

    int insertSelective(AppraiseDataList record);

    AppraiseDataList selectByPrimaryKey(String id);       
    
    List<AppraiseDataList> queryAll(String searchText);   //对应controller的"/all"

    int updateByPrimaryKeySelective(AppraiseDataList record);  

    int updateByPrimaryKey(AppraiseDataList record);     //对应controller的“/save"
    
    String delete(String id);                  //对应controller层的"/delete"
	
    List<AppraiseDataList> queryAllByNormId(String normId);

}

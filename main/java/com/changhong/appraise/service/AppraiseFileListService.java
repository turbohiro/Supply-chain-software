package com.changhong.appraise.service;

import java.util.List;

import com.changhong.appraise.model.AppraiseFileList;

public interface AppraiseFileListService {
	
	int deleteByPrimaryKey(String id);

    int insert(AppraiseFileList record);                  //对应controller的“/save"

    int insertSelective(AppraiseFileList record);

    AppraiseFileList selectByPrimaryKey(String id);       
    
    List<AppraiseFileList> queryAll(String searchText);   //对应controller的"/all"

    int updateByPrimaryKeySelective(AppraiseFileList record);  

    int updateByPrimaryKey(AppraiseFileList record);     //对应controller的“/save"
       
    String delete(String id);                  //对应controller层的"/delete"
    
    List<AppraiseFileList> queryAllByNormId(String normId);

}

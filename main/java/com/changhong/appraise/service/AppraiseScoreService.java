package com.changhong.appraise.service;

import java.util.List;

import com.changhong.appraise.model.AppraiseScore;
import com.changhong.common.Tree;

public interface AppraiseScoreService {
	
	int deleteByPrimaryKey(String id);

    int insert(AppraiseScore record);                  //对应controller的“/save"

    int insertSelective(AppraiseScore record);

    AppraiseScore selectByPrimaryKey(String id);       
    
    List<AppraiseScore> queryAll(String searchText);   //对应controller的"/all"

    int updateByPrimaryKeySelective(AppraiseScore record);  

    int updateByPrimaryKey(AppraiseScore record);     //对应controller的“/save"
    
    String delete(String id);                  //对应controller层的"/delete"
    
    AppraiseScore queryAllByNormId(String normId);

}

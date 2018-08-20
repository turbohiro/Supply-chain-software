package com.changhong.appraise.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.changhong.appraise.model.AppraiseDataList;
import com.changhong.appraise.model.AppraiseScore;

@Mapper
@Repository
public interface AppraiseScoreMapper {
	
	int deleteByPrimaryKey(String id);

    int insert(AppraiseScore record);                  

    int insertSelective(AppraiseScore record);

    AppraiseScore selectByPrimaryKey(String id);       
    
    List<AppraiseScore> queryAll(String searchText);   

    int updateByPrimaryKeySelective(AppraiseScore record);  

    int updateByPrimaryKey(AppraiseScore record);     
    
    String delete(String id);     
    
    AppraiseScore selectBynormCode(String normCode);

}

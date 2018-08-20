package com.changhong.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.changhong.system.model.Parameter;

@Mapper
@Repository
public interface ParameterMapper {
    int deleteByPrimaryKey(String id);

    int insert(Parameter record);

    int insertSelective(Parameter record);

    Parameter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);
    
    List<Parameter> queryAll(String searchText);
    
    List<Parameter> findAll();
    
}
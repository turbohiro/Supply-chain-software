package com.changhong.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.changhong.system.model.RoleModel;

@Mapper
@Repository
public interface RoleModelMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleModel record);

    int insertSelective(RoleModel record);

    RoleModel selectByPrimaryKey(String id);

    List<RoleModel> selectRoleModuleByRoleId(String roleId);
    
    int updateByPrimaryKeySelective(RoleModel record);

    int updateByPrimaryKey(RoleModel record);
}
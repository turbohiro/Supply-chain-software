package com.changhong.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.changhong.system.model.Module;

@Mapper
@Repository
public interface ModuleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(String id);

    List<Module> queryAll(String searchText);
    
    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
    
    List<Module> selectModulesByUser(String userId);
    
    List<Module> selectModulesByRole(String roleId);
    
    List<Module> selectModules();
    
    List<Module> queryAllModelFather(String transflag);
    
}
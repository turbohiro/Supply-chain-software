package com.changhong.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.changhong.common.Tree;
import com.changhong.system.model.Employee;
import com.changhong.system.model.Module;
import com.changhong.system.model.RoleModel;

public interface IModuleService {
	int deleteByPrimaryKey(String id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(String id);
    
    List<Module> queryAll(String searchText);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
    
    Tree queryModulesByUser(Employee user);
    
    Tree queryModules();
    
    Map<String, Object> queryAllModelFather();
    
    List<RoleModel> selectRoleModuleByRoleId(String roleId);
    
    String saveModel(String roleId,ArrayList<String> list);
    
    String saveModules(Module model);
    
    String delete(String id);
    
}

package com.changhong.system.service;

import java.util.List;
import com.changhong.system.model.Role;

public interface IRoleService {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> queryAll(String searchText);
}

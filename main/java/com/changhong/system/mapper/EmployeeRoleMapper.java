package com.changhong.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.changhong.system.model.EmployeeRole;

@Mapper
@Repository
public interface EmployeeRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(EmployeeRole record);

    int insertSelective(EmployeeRole record);

    EmployeeRole selectByPrimaryKey(String id);
    
    List<EmployeeRole> selectEmpRoleByUserId(String userId);

    int updateByPrimaryKeySelective(EmployeeRole record);

    int updateByPrimaryKey(EmployeeRole record);
    
    int deleteByEmp(String empcode);
}
package com.changhong.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.changhong.system.model.Employee;

@Mapper
@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(String empcode);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String empcode);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKeyWithBLOBs(Employee record);

    int updateByPrimaryKey(Employee record);
    
    List<Employee> findByAccount(String empname);
    
    List<Employee> queryAll(String searchText);
    
    String selectMaxId();
    
}
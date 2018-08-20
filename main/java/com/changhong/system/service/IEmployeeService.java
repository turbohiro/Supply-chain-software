package com.changhong.system.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.changhong.system.model.Employee;
import com.changhong.system.model.EmployeeRole;

public interface IEmployeeService {
	int deleteByPrimaryKey(String empcode);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String empcode);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKeyWithBLOBs(Employee record);

    int updateByPrimaryKey(Employee record);
    
    String selectByAccount(Map<String, Object> parameters);
    
    List<Employee> queryAll(String searchText);
    
    void updateUserPassword(Map<String, Object> map);
    
    List<EmployeeRole> selectEmpRoleByUserId(String id);
    
    String resetPwdByPrimaryKey(String id);
    
    boolean validateAccount(String account);
    
    String saveUser(Employee user,Collection<String> roleIds);
    
}

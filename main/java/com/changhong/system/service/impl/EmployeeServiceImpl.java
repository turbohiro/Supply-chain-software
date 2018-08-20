package com.changhong.system.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.changhong.system.mapper.EmployeeMapper;
import com.changhong.system.mapper.EmployeeRoleMapper;
import com.changhong.system.model.Employee;
import com.changhong.system.model.EmployeeRole;
import com.changhong.system.service.IEmployeeService;
import com.changhong.util.EncryptUtil;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	/**
     * 读取配置文件的值
     * 重置的密码
     */
    @Value("${reset.password:e10adc3949ba59abbe56e057f20f883e}")
    private String resetPassword;
    
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private EmployeeRoleMapper employeeRoleMapper;
	
	@Override
	public int deleteByPrimaryKey(String empcode) {
		return employeeMapper.deleteByPrimaryKey(empcode);
	}

	@Override
	public int insert(Employee record) {
		return employeeMapper.insert(record);
	}

	@Override
	public int insertSelective(Employee record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee selectByPrimaryKey(String empcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Employee record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Employee record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Employee record) {
		record.setDepartmentcode("1");
		record.setOrganizationcode("1");
		return employeeMapper.updateByPrimaryKey(record);
	}

	@Override
	public String selectByAccount(Map<String, Object> parameters) {
		String account = MapUtils.getString(parameters, "account");
        // 条件查询
        List<Employee> list = employeeMapper.findByAccount(account);
        if (CollectionUtils.isEmpty(list)) {
            // 没有此用户名
            return "00";
        }
        Employee dataBaseUser = list.get(0);
        // 传入的password已经md5过一次了,并且为小写
        if (!EncryptUtil.match(MapUtils.getString(parameters, "passwordIn"), dataBaseUser.getEmppass())) {
            return "密码不正确";
        }
        // controller中取出放到session中
        parameters.put("baseUser", dataBaseUser);
        return "01";
	}

	@Override
	public List<Employee> queryAll(String searchText) {
		return employeeMapper.queryAll(searchText);
	}

	@Override
	public void updateUserPassword(Map<String, Object> map) {
		String userId = MapUtils.getString(map, "userId");
        String newPassword = MapUtils.getString(map, "newPassword");
        Employee emp = employeeMapper.selectByPrimaryKey(userId);
        emp.setEmppass(newPassword);
        employeeMapper.updateByPrimaryKey(emp);
	}

	@Override
	public List<EmployeeRole> selectEmpRoleByUserId(String id) {
		List<EmployeeRole> list = employeeRoleMapper.selectEmpRoleByUserId(id);
		return list;
	}

	@Override
	public String resetPwdByPrimaryKey(String id) {
		Employee emp = employeeMapper.selectByPrimaryKey(id);
		if(emp!=null){
			emp.setEmppass(resetPassword);
			employeeMapper.updateByPrimaryKey(emp);
			return "01";
		}else{
			return "该账号不存在!";
		}
	}

	@Override
	public boolean validateAccount(String account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String saveUser(Employee user, Collection<String> roleIds) {
		String empId = "";
		if(StringUtils.isBlank(user.getEmpcode())){
			//新增用户
			List<Employee> list = employeeMapper.findByAccount(user.getEmpcode());
			if(list.size()>0){
				return "帐号已经被注册！请重新填写!";
			}
			
			String empCode = employeeMapper.selectMaxId();
			if(StringUtils.isBlank(empCode)){
				user.setEmpcode(String.valueOf("1"));
			}else{
				int newId = Integer.parseInt(empCode)+1;
				user.setEmpcode(String.valueOf(newId));
			}
			int userId = employeeMapper.insert(user);
			empId = String.valueOf(userId);
		}else{
			//修改用户
			employeeMapper.updateByPrimaryKey(user);
			empId = user.getEmpcode();
		}
		//保存角色信息,先删除之前的角色信息
		employeeRoleMapper.deleteByEmp(empId);
		for(String roleId:roleIds){
			EmployeeRole emprole = new EmployeeRole();
			String id = java.util.UUID.randomUUID().toString();
			emprole.setId(id);
			emprole.setEmpcode(empId);
			emprole.setRoleid(roleId);
			emprole.setCompanycode("1");
			employeeRoleMapper.insert(emprole);
		}
		return "01";
	}

}

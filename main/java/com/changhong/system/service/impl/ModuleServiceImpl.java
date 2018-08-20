package com.changhong.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.common.Tree;
import com.changhong.common.TreeMenu;
import com.changhong.system.mapper.ModuleMapper;
import com.changhong.system.mapper.RoleModelMapper;
import com.changhong.system.model.Employee;
import com.changhong.system.model.Module;
import com.changhong.system.model.RoleModel;
import com.changhong.system.service.IModuleService;
import com.google.common.collect.Maps;
@Service("moduleService")
public class ModuleServiceImpl implements IModuleService {

	@Autowired
	private ModuleMapper moduleMapper;
	@Autowired
	private RoleModelMapper roleModelMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return moduleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Module record) {
		return moduleMapper.insert(record);
	}

	@Override
	public int insertSelective(Module record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Module selectByPrimaryKey(String id) {
		Module model = moduleMapper.selectByPrimaryKey(id);
		return model;
	}

	@Override
	public int updateByPrimaryKeySelective(Module record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Module record) {
		return moduleMapper.updateByPrimaryKey(record);
	}

	@Override
	public Tree queryModulesByUser(Employee user) {
		List<Module> list = moduleMapper.selectModulesByUser(user.getEmpcode());
		TreeMenu menu = new TreeMenu(list);
		return menu.getTreeJson();
	}

	@Override
	public Map<String, Object> queryAllModelFather() {
		// transflag=0 的即为父节点
		List<Module> list = moduleMapper.queryAllModelFather("0");
		Map<String, Object> map = Maps.newHashMap();
		for(Module sm:list){
			map.put(sm.getId(), sm.getModelname());
		}
		return map;
	}

	@Override
	public List<Module> queryAll(String searchText) {
		List<Module> list = moduleMapper.queryAll(searchText);
		return list;
	}

	@Override
	public List<RoleModel> selectRoleModuleByRoleId(String roleId) {
		return roleModelMapper.selectRoleModuleByRoleId(roleId);
	}

	@Override
	public String saveModel(String roleId, ArrayList<String> list) {
		try {
			for(String str:list){
				RoleModel rm = new RoleModel();
				String id = java.util.UUID.randomUUID().toString();
				rm.setId(id);
				rm.setRoleid(roleId);
				rm.setModelid(str);
				rm.setCompanycode("1");
				roleModelMapper.insert(rm);
			}
			
			return "01";
		} catch (Exception e) {
			e.printStackTrace();
			return "00";
		}
		
	}

	@Override
	public String saveModules(Module model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tree queryModules() {
		List<Module> list = moduleMapper.selectModules();
		TreeMenu menu = new TreeMenu(list);
		return menu.getTreeJson();
	}

	
}


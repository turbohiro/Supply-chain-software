package com.changhong.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.common.Tree;
import com.changhong.common.TreeMenuOrg;
import com.changhong.system.mapper.OrganizationMapper;
import com.changhong.system.model.Organization;
import com.changhong.system.service.IOrganizationService;

@Service("organizationService")
public class OrganizationServiceImpl implements IOrganizationService{

	@Autowired
	private OrganizationMapper organizationMapper;
	private List<Organization> orglist;
	
	@Override
	public int deleteByPrimaryKey(String organizationcode) {
		return organizationMapper.deleteByPrimaryKey(organizationcode);
	}

	@Override
	public int insert(Organization record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Organization record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Organization selectByPrimaryKey(String organizationcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Organization record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Organization record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Organization> queryAll(String searchText) {
		return organizationMapper.queryAll(searchText);
	}

	@Override
	public String saveOrg(Organization record) {
		if(StringUtils.isBlank(record.getOrganizationcode())){
			String orgCode = organizationMapper.selectMaxId();
			if(StringUtils.isBlank(orgCode)){
				record.setOrganizationcode(String.valueOf("1000"));
			}else{
				int newId = Integer.parseInt(orgCode)+1;
				record.setOrganizationcode(String.valueOf(newId));
			}
			organizationMapper.insert(record);
		}else{
			organizationMapper.updateByPrimaryKey(record);
		}
		return "01";
	}

	@Override
	public Tree queryOrgs() {
		List<Organization> list = organizationMapper.selectOrgs();
		TreeMenuOrg menu = new TreeMenuOrg(list);
		return menu.getTreeJson();
	}

	@Override
	public List<Organization> queryAllByOrgId(String orgId) {
		orglist = new ArrayList<Organization>();
		Organization org = organizationMapper.selectByPrimaryKey(orgId);
		orglist.add(org);//添加自身
		orgChild(orgId);
		return orglist;
	}

	/**
	 * 功能描述： 递归查询所有子机构
	 * @param orgId
	 * @author sw.j
	 */
	public void orgChild(String orgId){
		List<Organization> ls = organizationMapper.queryAllByOrgId(orgId);
		if(ls.size()>0){
			orglist.addAll(ls);
			for(Organization org:ls){
				orgChild(org.getOrganizationcode());
			}
		}
	}
}

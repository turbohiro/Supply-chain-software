package com.changhong.system.service;

import java.util.List;

import com.changhong.common.Tree;
import com.changhong.system.model.Organization;

public interface IOrganizationService {
	int deleteByPrimaryKey(String organizationcode);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String organizationcode);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    List<Organization> queryAll(String searchText);
    
    List<Organization> queryAllByOrgId(String orgId);
    
    String saveOrg(Organization record);
    
    Tree queryOrgs();
}

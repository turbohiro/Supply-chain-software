package com.changhong.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.changhong.system.model.Organization;

@Mapper
@Repository
public interface OrganizationMapper {
    int deleteByPrimaryKey(String organizationcode);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String organizationcode);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    List<Organization> queryAll(String searchText);
    
    List<Organization> queryAllByOrgId(String orgId);
    
    String selectMaxId();
    
    List<Organization> selectOrgs();
    
}
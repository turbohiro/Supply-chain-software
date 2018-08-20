package com.changhong.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.changhong.system.model.EmployeeOrganization;

@Mapper
@Repository
public interface EmployeeOrganizationMapper {
    int deleteByPrimaryKey(String id);

    int insert(EmployeeOrganization record);

    int insertSelective(EmployeeOrganization record);

    EmployeeOrganization selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmployeeOrganization record);

    int updateByPrimaryKey(EmployeeOrganization record);
}
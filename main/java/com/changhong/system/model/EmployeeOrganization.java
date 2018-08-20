package com.changhong.system.model;

import java.io.Serializable;

/**
 * 
 * 功能描述： 员工组织机构关系
 * @author sw.j
 * @date 2018年5月18日 下午4:07:16
 * @version 1.0
 */
public class EmployeeOrganization implements Serializable {
    private String id;

    private String empcode;

    private String organizationcode;

    private String companycode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode == null ? null : empcode.trim();
    }

    public String getOrganizationcode() {
        return organizationcode;
    }

    public void setOrganizationcode(String organizationcode) {
        this.organizationcode = organizationcode == null ? null : organizationcode.trim();
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }
}
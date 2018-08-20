package com.changhong.system.model;

import java.io.Serializable;

/**
 * 
 * 功能描述： 员工角色关系
 * @author sw.j
 * @date 2018年5月18日 下午4:06:40
 * @version 1.0
 */
public class EmployeeRole implements Serializable {
    private String id;

    private String roleid;

    private String empcode;

    private String companycode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode == null ? null : empcode.trim();
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }
}
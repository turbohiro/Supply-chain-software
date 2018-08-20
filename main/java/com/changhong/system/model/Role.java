package com.changhong.system.model;

import java.io.Serializable;

/**
 * 
 * 功能描述： 角色信息
 * @author sw.j
 * @date 2018年5月18日 下午4:08:29
 * @version 1.0
 */
public class Role implements Serializable {
    private String id;
    /**角色名称*/
    private String rolename;
    /**描述*/
    private String roledesc;
    /**备注*/
    private String remark;
    /**是否系统管理员0:假，1为真*/
    private Boolean ismanager;
    /**系统类型(MES/WMS)*/
    private String systemtype;
    /**公司级别*/
    private String companycode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getIsmanager() {
        return ismanager;
    }

    public void setIsmanager(Boolean ismanager) {
        this.ismanager = ismanager;
    }

    public String getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype == null ? null : systemtype.trim();
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }
}
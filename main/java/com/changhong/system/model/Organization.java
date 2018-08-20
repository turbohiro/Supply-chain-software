package com.changhong.system.model;

import java.io.Serializable;

/**
 * 
 * 功能描述： 组织机构
 * @author sw.j
 * @date 2018年5月18日 下午4:08:08
 * @version 1.0
 */
public class Organization implements Serializable {
	/**机构编号*/
    private String organizationcode;
    /**区域代码*/
    private String regioncode;
    /**机构名称*/
    private String organizationname;
    /**隶属机构代码*/
    private String father;
    /**地址*/
    private String address;
    /**电话*/
    private String phone;
    /**备注*/
    private String remark;
    /**机构路径*/
    private String path;
    /**公司级别*/
    private String companycode;
    
    private static final long serialVersionUID = 1L;

    public String getOrganizationcode() {
        return organizationcode;
    }

    public void setOrganizationcode(String organizationcode) {
        this.organizationcode = organizationcode == null ? null : organizationcode.trim();
    }

    public String getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode == null ? null : regioncode.trim();
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname == null ? null : organizationname.trim();
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father == null ? null : father.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }
}
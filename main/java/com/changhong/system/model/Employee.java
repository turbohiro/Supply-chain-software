package com.changhong.system.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 功能描述： 员工信息
 * @author sw.j
 * @date 2018年5月18日 下午4:07:41
 * @version 1.0
 */
public class Employee implements Serializable {
	
	/**员工代码（主键）*/
    private String empcode;
    /**员工账号*/
    private String empname;
    /**？*/
    private String emprank;
    /**？(员工姓名)*/
    private String classname;
    /**群组*/
    private String stationname;
    /**离职时间*/
    private Date quitdate;
    /**密码*/
    private String emppass;
    /**？*/
    private String empbc;
    /**系统类型 MES WMS 或者为空值，代表通用*/
    private String systemtype;
    /**机构代码*/
    private String organizationcode;
    /**部门代码*/
    private String departmentcode;
    /**员工英文名称*/
    private String empename;
    /**员工分类(0:内部 1:外部)*/
    private String emptype;
    /**EMAIL*/
    private String email;
    /**手机号*/
    private String phone;
    /**所属客户*/
    private String custcode;
    /**所属供应商*/
    private String suppliercode;
    /**公司级别*/
    private String companycode;
    /**肖像图*/
    private byte[] photo;

    private static final long serialVersionUID = 1L;

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode == null ? null : empcode.trim();
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname == null ? null : empname.trim();
    }

    public String getEmprank() {
        return emprank;
    }

    public void setEmprank(String emprank) {
        this.emprank = emprank == null ? null : emprank.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname == null ? null : stationname.trim();
    }

    public Date getQuitdate() {
        return quitdate;
    }

    public void setQuitdate(Date quitdate) {
        this.quitdate = quitdate;
    }

    public String getEmppass() {
        return emppass;
    }

    public void setEmppass(String emppass) {
        this.emppass = emppass == null ? null : emppass.trim();
    }

    public String getEmpbc() {
        return empbc;
    }

    public void setEmpbc(String empbc) {
        this.empbc = empbc == null ? null : empbc.trim();
    }

    public String getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype == null ? null : systemtype.trim();
    }

    public String getOrganizationcode() {
        return organizationcode;
    }

    public void setOrganizationcode(String organizationcode) {
        this.organizationcode = organizationcode == null ? null : organizationcode.trim();
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode == null ? null : departmentcode.trim();
    }

    public String getEmpename() {
        return empename;
    }

    public void setEmpename(String empename) {
        this.empename = empename == null ? null : empename.trim();
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype == null ? null : emptype.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCustcode() {
        return custcode;
    }

    public void setCustcode(String custcode) {
        this.custcode = custcode == null ? null : custcode.trim();
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode == null ? null : suppliercode.trim();
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
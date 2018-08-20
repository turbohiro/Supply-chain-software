package com.changhong.system.model;

import java.io.Serializable;

/**
 * 功能描述： 功能菜单
 * @author sw.j
 * @date 2018年5月10日 下午1:53:48
 * @version 1.0
 */
public class Module implements Serializable {
    private String id;
    /**模块名称*/
    private String modelname;
    /**系统编号*/
    private Byte systemid=1;
    /**父模块ID*/
    private String parentid;
    /**排序*/
    private Integer orderno;
    /**菜单级别(0-模块 1-功能 2-按钮)*/
    private Integer grade;
    /**模块对应的css样式*/
    private String dllfname;
    /**模块路径*/
    private String classname;
    /**类型：0普通，1，报表，2，通用窗体*/
    private String modeltype;
    /**是否默认展开菜单*/
    private String modelparameter;
    /**是否显示*/
    private Boolean isshow;
    /**系统类型(MES/WMS)*/
    private String sytemtype;
    /**类型：0父节点，1，子节点*/
    private String transflag;
    /**模块名称代码*/
    private String menucode;
    /**公司级别*/
    private String companycode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname == null ? null : modelname.trim();
    }

    public Byte getSystemid() {
        return systemid;
    }

    public void setSystemid(Byte systemid) {
        this.systemid = systemid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getDllfname() {
        return dllfname;
    }

    public void setDllfname(String dllfname) {
        this.dllfname = dllfname == null ? null : dllfname.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public String getModeltype() {
        return modeltype;
    }

    public void setModeltype(String modeltype) {
        this.modeltype = modeltype == null ? null : modeltype.trim();
    }

    public String getModelparameter() {
        return modelparameter;
    }

    public void setModelparameter(String modelparameter) {
        this.modelparameter = modelparameter == null ? null : modelparameter.trim();
    }

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    public String getSytemtype() {
        return sytemtype;
    }

    public void setSytemtype(String sytemtype) {
        this.sytemtype = sytemtype == null ? null : sytemtype.trim();
    }

    public String getTransflag() {
        return transflag;
    }

    public void setTransflag(String transflag) {
        this.transflag = transflag == null ? null : transflag.trim();
    }

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode == null ? null : menucode.trim();
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }
}
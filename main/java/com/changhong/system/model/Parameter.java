package com.changhong.system.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述： 系统参数(数据字典)
 * @author sw.j
 * @date 2018年5月18日 下午4:02:37
 * @version 1.0
 */
public class Parameter implements Serializable {
    private String id;
    /**大分类*/
    private String bigclass;
    /**小分类*/
    private String smallclass;
    /**描述*/
    private String parameterdesc;
    /**名称*/
    private String viewtype;
    /**值*/
    private String parametervalue;
    /**值的描述*/
    private String valuedesc;
    /**默认值*/
    private String defaultvalue;
    /**排序号*/
    private Integer sequence;
    /**(0：系统级 1 ：辅料制具 2 ：smt 3：pth 4：成品仓  5 ：原料仓)*/
    private String type;
    /**显示标志Y 显示在界面上 N 不显示在界面上(0/1)*/
    private String vierflag;
    /**修改人*/
    private String modifyuser;
    /**修改时间*/
    private Date modifydate;
    /**公司级别*/
    private String companycode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBigclass() {
        return bigclass;
    }

    public void setBigclass(String bigclass) {
        this.bigclass = bigclass == null ? null : bigclass.trim();
    }

    public String getSmallclass() {
        return smallclass;
    }

    public void setSmallclass(String smallclass) {
        this.smallclass = smallclass == null ? null : smallclass.trim();
    }

    public String getParameterdesc() {
        return parameterdesc;
    }

    public void setParameterdesc(String parameterdesc) {
        this.parameterdesc = parameterdesc == null ? null : parameterdesc.trim();
    }

    public String getViewtype() {
        return viewtype;
    }

    public void setViewtype(String viewtype) {
        this.viewtype = viewtype == null ? null : viewtype.trim();
    }

    public String getParametervalue() {
        return parametervalue;
    }

    public void setParametervalue(String parametervalue) {
        this.parametervalue = parametervalue == null ? null : parametervalue.trim();
    }

    public String getValuedesc() {
        return valuedesc;
    }

    public void setValuedesc(String valuedesc) {
        this.valuedesc = valuedesc == null ? null : valuedesc.trim();
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue == null ? null : defaultvalue.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getVierflag() {
        return vierflag;
    }

    public void setVierflag(String vierflag) {
        this.vierflag = vierflag == null ? null : vierflag.trim();
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser == null ? null : modifyuser.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }
}
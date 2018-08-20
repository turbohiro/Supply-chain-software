package com.changhong.appraise.model;

import java.io.Serializable;


/**
 * 功能描述：数据类列表（AppariseDataFile)实体层数据库字段定义,
 */


public class AppraiseDataList implements Serializable{
	
	private String id;
	/**数据类别**/
	private String datatype;
	/**数据量**/
	private String datavalue;
	/**单位符号**/
	private String unitsymbol;
	/**指标编号**/
	private String normcode;
	/**备注**/
	private String remake;
	/**公司级别**/
	private String companycode;
	
	private String normname;
	
	
	private static final long serialVersionUID = 1L;
	
	public String getId() {
	        return id;
	    }

	public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }
	
    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype == null ? null : datatype.trim();
    }
    
    public String getDatavalue() {
        return datavalue;
    }

    public void setDatavalue(String datavalue) {
        this.datavalue = datavalue == null ? null : datavalue.trim();
    }
    
    public String getUnitsymbol() {
        return unitsymbol;
    }

    public void setUnitsymbol(String unitsymbol) {
        this.unitsymbol = unitsymbol == null ? null : unitsymbol.trim();
    }
    
    public String getNormname() {
        return normname;
    }

    public void setNormname(String normname) {
        this.normname= normname == null ? null : normname.trim();
    }
    
    public String getNormcode() {
        return normcode;
    }

    public void setNormcode(String normcode) {
        this.normcode= normcode == null ? null : normcode.trim();
    }
        
    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake ) {
        this.remake = remake  == null ? null : remake.trim();
    }
    
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode ) {
        this.companycode = companycode  == null ? null : companycode.trim();
    }
    

}

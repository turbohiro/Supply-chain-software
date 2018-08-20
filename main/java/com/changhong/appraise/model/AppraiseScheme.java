package com.changhong.appraise.model;

import java.io.Serializable;

/**
 * 功能描述：评价方案（AppariseScheme)实体层数据库字段定义,
 */

public class AppraiseScheme implements Serializable{
	
	private String id;
	/**评价方案的名字**/
	private String schemename;
	/**评价方案的编号**/
	private String schemecode;
	/**评价方案的方案类型**/
	private String schemetype;
	/**评价方案的备注**/
	private String remake;
	
	private String companycode;
	
	private static final long serialVersionUID = 1L;
	
	public String getId() {
	        return id;
	    }

	public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }
	
    public String getSchemename() {
        return schemename;
    }

    public void setSchemename(String schemename) {
        this.schemename = schemename == null ? null : schemename.trim();
    }
    
    public String getSchemecode() {
        return schemecode;
    }

    public void setSchemecode(String schemecode) {
        this.schemecode = schemecode == null ? null : schemecode.trim();
    }
    
    public String getSchemetype() {
        return schemetype;
    }

    public void setSchemetype(String schemetype) {
        this.schemetype = schemetype == null ? null : schemetype.trim();
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

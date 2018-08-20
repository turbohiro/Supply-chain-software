package com.changhong.appraise.model;

import java.io.Serializable;


/**
 * 功能描述：评价指标（AppariseNorm)实体层数据库字段定义,
 */


public class AppraiseNorm implements Serializable{
	
	private String id;
	/**评价指标的名字**/
	private String normname;
	/**评价指标的编号**/
	private String normcode;
	/**评价方案的编号**/
	private String schemecode;
	/**父指标的编号**/
	private String normcodefather;
	/**指标级别**/
	private String normlevel;
	/**备注**/
	private String remake;
	/**公司级别**/
	private String companycode;
	
	
	private static final long serialVersionUID = 1L;
	
	public String getId() {
	        return id;
	    }

	public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }
	
    public String getNormname() {
        return normname;
    }

    public void setNormname(String normname) {
        this.normname = normname == null ? null : normname.trim();
    }
    
    public String getNormcode() {
        return normcode;
    }

    public void setNormcode(String normcode) {
        this.normcode = normcode == null ? null : normcode.trim();
    }
    
    public String getSchemecode() {
        return schemecode;
    }

    public void setSchemecode(String schemecode) {
        this.schemecode = schemecode == null ? null : schemecode.trim();
    }
    
    public String getNormcodefather() {
        return normcodefather;
    }

    public void setNormcodefather(String normcodefather) {
        this.normcodefather = normcodefather == null ? null : normcodefather.trim();
    }
    
    public String getNormlevel() {
        return normlevel;
    }

    public void setNormlevel(String normlevel ) {
        this.normlevel = normlevel  == null ? null : normlevel.trim();
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

package com.changhong.appraise.model;

import java.io.Serializable;


/**
 * 功能描述：评价标准（AppariseScorePrinciple)实体层数据库字段定义,
 */


public class AppraiseScorePrinciple implements Serializable{
	
	private String id;
	/**附件名字**/
	private String scoreprinciple;
	/**指标编号**/
	private String normcode;
	/**备注**/
	private String companycode;
	
	private String normname;
	
	
	private static final long serialVersionUID = 1L;
	
	public String getId() {
	        return id;
	    }

	public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }
	
    public String getScoreprinciple() {
        return scoreprinciple;
    }

    public void setScoreprinciple(String scoreprinciple) {
        this.scoreprinciple = scoreprinciple == null ? null : scoreprinciple.trim();
    }
    
    public  String getNormcode() {
        return normcode;
    }

    public void setNormcode(String normcode) {
        this.normcode = normcode == null ? null : normcode.trim();
    }
    
    public String getNormname() {
        return normname;
    }

    public void setNormname(String normname) {
        this.normname = normname == null ? null : normname.trim();
    }
    
    
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode ) {
        this.companycode = companycode  == null ? null : companycode.trim();
    }

}

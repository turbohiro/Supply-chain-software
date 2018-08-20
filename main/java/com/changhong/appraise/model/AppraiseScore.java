package com.changhong.appraise.model;

import java.io.Serializable;
/**
 * 功能描述：专家得分列表（AppariseScore)实体层数据库字段定义,
 */


public class AppraiseScore implements Serializable{
	
	private String id;
	/**指标编号**/
	private String normcode;
	/**专家编号**/
	private String empcode;
	/**专家给分**/
	private String score;
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
	
	 public String getNormcode() {
	        return normcode;
	    }

    public void setNormcode(String normcode) {
        this.normcode= normcode == null ? null : normcode.trim();
    }

    public String getEmpCode() {
        return empcode;
    }

    public void setEmpCode(String empcode) {
        this.empcode = empcode == null ? null : empcode.trim();
    }
    
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
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

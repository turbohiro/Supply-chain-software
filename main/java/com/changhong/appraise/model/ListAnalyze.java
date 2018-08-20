package com.changhong.appraise.model;

/**列出所有评价指标的类型进行锁死，得分情况可以关联AppraiseScore表*/

public class ListAnalyze{
	private int id;
	
	private String normname;

	private String score;
	
	private String normcode;
	
	private String empcode;
	
	private String companycode;
	
	public int getId() {
	        return id;
	    }

	public void setId(int id) {
	        this.id = id ;
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
    
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }
    
    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode ) {
        this.empcode = empcode  == null ? null : empcode.trim();
    }
    
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode ) {
        this.companycode = companycode  == null ? null : companycode.trim();
    }

}

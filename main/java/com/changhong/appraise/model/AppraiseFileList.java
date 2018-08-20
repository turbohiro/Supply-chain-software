package com.changhong.appraise.model;

import java.io.Serializable;


/**
 * 功能描述：文本类列表（AppariseDataFile)实体层数据库字段定义,
 */


public class AppraiseFileList implements Serializable{
	
	private String id;
	/**附件名字**/
	private String filename;
	/**附件路径**/
	private String filepath;
	/**附件内容**/
	private String filecontext;
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
	
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
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
    
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }
    
    public String getFilecontext() {
        return filecontext;
    }

    public void setFilecontext(String filecontext) {
        this.filecontext = filecontext == null ? null : filecontext.trim();
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

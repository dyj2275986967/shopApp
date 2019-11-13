package com.zx.vo;

public class ArticleTypeVo {
	private String code;
	private String name;
	private String remark;
	/**
	 * @return the code
	 */
	

	public ArticleTypeVo() {

	}
	public ArticleTypeVo(String code, String name, String remark) {
		super();
		this.code = code;
		this.name = name;
		this.remark = remark;
	}
	
	
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
	
	
}

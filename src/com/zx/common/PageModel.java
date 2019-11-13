package com.zx.common;

public class PageModel {
	//当前页码
	private int pageIndex=1;
	//每页显示多少记录数
	private int pageSize=8;
	//总记录数
	 private int totalNum;
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @return the totalNum
	 */
	public int getTotalNum() {
		return totalNum;
	}
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	 
	 //计算每一页开始时的记录数（是从第几条记录数开始）
	 public int   getStarNum() {
		 
		  
		 return (this.getPageIndex()-1)*this.getPageSize();
		 
	 }
	

}

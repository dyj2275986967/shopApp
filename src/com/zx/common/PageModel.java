package com.zx.common;

public class PageModel {
	//��ǰҳ��
	private int pageIndex=1;
	//ÿҳ��ʾ���ټ�¼��
	private int pageSize=8;
	//�ܼ�¼��
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
	 
	 //����ÿһҳ��ʼʱ�ļ�¼�����Ǵӵڼ�����¼����ʼ��
	 public int   getStarNum() {
		 
		  
		 return (this.getPageIndex()-1)*this.getPageSize();
		 
	 }
	

}

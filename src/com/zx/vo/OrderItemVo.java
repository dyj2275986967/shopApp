package com.zx.vo;

import java.util.List;

public class OrderItemVo {
	private int orderId;
    private int articleId;
	private int orderNum;
	private ArticleVo  article;
	private List<ShopCarVo> shopCarList;

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @return the orderNum
	 */
	public int getOrderNum() {
		return orderNum;
	}
	/**
	 * @return the shopCarList
	 */
	public List<ShopCarVo> getShopCarList() {
		return shopCarList;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * @param shopCarList the shopCarList to set
	 */
	public void setShopCarList(List<ShopCarVo> shopCarList) {
		this.shopCarList = shopCarList;
	}
	/**
	 * @return the articleId
	 */
	public int getArticleId() {
		return articleId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	/**
	 * @return the article
	 */
	public ArticleVo getArticle() {
		return article;
	}
	/**
	 * @param article the article to set
	 */
	public void setArticle(ArticleVo article) {
		this.article = article;
	}
	
	


	

}

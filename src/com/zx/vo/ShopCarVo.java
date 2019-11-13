package com.zx.vo;

public class ShopCarVo {
	private int buynum;
	private int userId;
	private int articleId;
	//存商品信息;
	
	
	
	private ArticleVo article;

	/**
	 * @return the buynum
	 */
	public int getBuynum() {
		return buynum;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @return the articleId
	 */
	public int getArticleId() {
		return articleId;
	}
	/**
	 * @return the article
	 */
	public ArticleVo getArticle() {
		return article;
	}
	/**
	 * @param buynum the buynum to set
	 */
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	/**
	 * @param article the article to set
	 */
	public void setArticle(ArticleVo article) {
		this.article = article;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShopCarVo [buynum=" + buynum + ", userId=" + userId + ", articleId=" + articleId + ", article="
				+ article + "]";
	}
	
	
	

}

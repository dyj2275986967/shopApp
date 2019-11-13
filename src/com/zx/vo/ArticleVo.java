package com.zx.vo;

public class ArticleVo {
     private int id;
	private String title;
	private String supplier;
	private double price;
	private double discount;
	private String  locality;
	private String   putawayDate;
	private int storage;
	private String image;
	private String description;
	private String typeCode;
	private String createDate;
	private String disabled;

	public ArticleVo() {
		
		
	}
	 
	
	
	public ArticleVo(int id, String title, String supplier, double price, double discount, String locality,
			String putawayDate, int storage, String iamge, String description, String typeCode, String createDate,
			String disabled) {
		super();
		this.id = id;
		this.title = title;
		this.supplier = supplier;
		this.price = price;
		this.discount = discount;
		this.locality = locality;
		this.putawayDate = putawayDate;
		this.storage = storage;
		this.image = iamge;
		this.description = description;
		this.typeCode = typeCode;
		this.createDate = createDate;
		this.disabled = disabled;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the supplier
	 */
	public String getSupplier() {
		return supplier;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}
	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}
	/**
	 * @return the putawayDate
	 */
	public String getPutawayDate() {
		return putawayDate;
	}
	/**
	 * @return the storage
	 */
	public int getStorage() {
		return storage;
	}
	/**
	 * @return the iamge
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @return the disabled
	 */
	public String getDisabled() {
		return disabled;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}
	/**
	 * @param putawayDate the putawayDate to set
	 */
	public void setPutawayDate(String putawayDate) {
		this.putawayDate = putawayDate;
	}
	/**
	 * @param storage the storage to set
	 */
	public void setStorage(int storage) {
		this.storage = storage;
	}
	/**
	 * @param iamge the iamge to set
	 */
	public void setIamge(String iamge) {
		this.image = iamge;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	
	
	
	

}

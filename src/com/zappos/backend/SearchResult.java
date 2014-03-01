package com.zappos.backend;

public class SearchResult {
	/*
	 * "styleId": "556677", "productId": "123456", "brandName": "Ugg",
	 * "productName": "Classic Tall", "thumbnailImageUrl":
	 * "http://www.zappos.com/images/image.jpg", "originalPrice": "$198.00",
	 * "price": "$198.00", "percentOff": "19%", "productUrl":
	 * "http://www.zappos.com/product/101183/color/381"
	 */

	private Integer styleId;
	private Integer productId;
	private String brandName;
	private String thumbnailImageUrl;
	private Double orignalPrice;
	private String price;
	private Double priceConverted;
	private String percentage;
	private String productURL;

	public SearchResult(Integer styleId, Integer productId, String brandName,
			String thumbnailImageUrl, Double orignalPrice, String price,
			String percentage, String productURL) {
		super();
		this.styleId = styleId;
		this.productId = productId;
		this.brandName = brandName;
		this.thumbnailImageUrl = thumbnailImageUrl;
		this.orignalPrice = orignalPrice;
		this.price = price;
		this.percentage = percentage;
		this.productURL = productURL;
	}

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getThumbnailImageUrl() {
		return thumbnailImageUrl;
	}

	public void setThumbnailImageUrl(String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

	public Double getOrignalPrice() {
		return orignalPrice;
	}

	public void setOrignalPrice(Double orignalPrice) {
		this.orignalPrice = orignalPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getProductURL() {
		return productURL;
	}

	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}

	public Double getPriceConverted() {
		return priceConverted;
	}

	public void setPriceConverted(Double priceConverted) {
		this.priceConverted = priceConverted;
	}

}

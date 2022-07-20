package com.ecom.dto;

public class BrandResponse {
	private String categoryName;
	private String brandName;

	public BrandResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BrandResponse(String categoryName, String brandName) {
		super();
		this.categoryName = categoryName;
		this.brandName = brandName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "BrandResponse [brandName=" + brandName + ", categoryName=" + categoryName + "]";
	}

}

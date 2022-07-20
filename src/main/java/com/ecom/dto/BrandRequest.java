package com.ecom.dto;

import com.ecom.model.Category;

public class BrandRequest {
	private Category category;

	public BrandRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BrandRequest(Category category) {
		super();
		this.category = category;
	}

	@Override
	public String toString() {
		return "BrandRequest [category=" + category + "]";
	}
	
		

}

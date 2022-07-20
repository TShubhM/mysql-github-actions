package com.ecom.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "brand")
@ApiModel(description = "Details about a brand")
public class Brand {
	@Id
	@Column(name = "brand_id")
	private int brandId;

	@Column(name = "brand_name")
	private String brandName;

	@Column(name = "brand_logo")
	@ApiModelProperty(notes = "This shows image of brand")
	private String brandLogo;

	@Column(name = "category_id")
	private int categoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;
	
	@OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
	@JoinColumn(name= "pb_fk",referencedColumnName = "brand_id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private List<Product> products;
	
	public Brand() {
		super();
		brandId = 1;
		// TODO Auto-generated constructor stub
	}

	public Brand(int brandId, String brandName, String brandLogo, int categoryId) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.brandLogo = brandLogo;
		this.categoryId = categoryId;
	}

	public Brand(String brandName, String brandLogo, int categoryId) {
		super();
		this.brandName = brandName;
		this.brandLogo = brandLogo;
		this.categoryId = categoryId;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", brandName=" + brandName + ", brandLogo=" + brandLogo + ", categoryId="
				+ categoryId + "]";
	}

}

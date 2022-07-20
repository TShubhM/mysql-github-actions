package com.ecom.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "category")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "category_name", length = 25)
	private String categoryName;

	@Column()
	private boolean categoryEnabled;

	@OneToMany(targetEntity = Brand.class,cascade = CascadeType.ALL)
	@JoinColumn(name= "cb_fk",referencedColumnName = "category_id")
	private List<Brand> brands;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String categoryName, boolean categoryEnabled) {
		super();
		this.categoryName = categoryName;
		this.categoryEnabled = categoryEnabled;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public boolean isCategoryEnabled() {
		return categoryEnabled;
	}

	public void setCategoryEnabled(boolean categoryEnabled) {
		this.categoryEnabled = categoryEnabled;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryEnabled="
				+ categoryEnabled + "]";
	}

}

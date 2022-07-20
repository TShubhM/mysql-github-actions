package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.ecom.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findByBrandId(int brandId);
	public List<Product> findByProductNameAndBrandId(String productName, int brandId);
	public Product findByProductName(String productName);
	public List<Product> findByProductPriceGreaterThanEqualAndProductPriceLessThanEqual(double productPrice1,double productPrice2);

	
	
	
}

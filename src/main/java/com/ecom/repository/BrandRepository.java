package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

	public List<Brand> findByCategoryId(int categoryId);
	public Brand findByBrandName(String brandName);
	
	@Query("Select b from Brand b where b.brandId=1")
	List<Brand> findAllBrands();
	
	@Query("SELECT 'Category = '||c.categoryName||'  Brand = '||b.brandName FROM Category c JOIN c.brands b where c.categoryEnabled=false")
	List<String> getJoinInformation();
	
	@Query("SELECT 'Category = '|| c.categoryName ||' Brand = ' || b.brandName || ' Product Name = '|| p.productName FROM Category c JOIN c.brands b JOIN b.products p where c.categoryEnabled=true")
	List<String> getAllJoinInformationByQuery();
	
	

}

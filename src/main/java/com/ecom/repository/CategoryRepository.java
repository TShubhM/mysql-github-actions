package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;

import com.ecom.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public Category findByCategoryName(String categoryName);

	public List<Category> findByCategoryEnabled(boolean categoryEnabled);

	public boolean findCategoryEnabledByCategoryId(int categoryId);

	@Query("SELECT 'Category = '||c.categoryName||'  Brand = '||b.brandName FROM Category c JOIN c.brands b where c.categoryEnabled=false")
	List<String> getJoinInformation();

	@Query("SELECT 'Category = '|| c.categoryName ||' Brand = ' || b.brandName || ' Product Name = '|| p.productName FROM Category c JOIN c.brands b JOIN b.products p where c.categoryEnabled=true")
	List<String> getAllJoinInformationByQuery();

}

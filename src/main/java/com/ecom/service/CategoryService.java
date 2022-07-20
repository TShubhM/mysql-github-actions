package com.ecom.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.EmptyInputException;
import com.ecom.exception.NoSuchObjectExistException;
import com.ecom.exception.ObjectAlreadyExistException;
import com.ecom.model.Category;
import com.ecom.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	public CategoryRepository categoryRepo;

	public Category addCategory(Category category) {
		if (category.getCategoryId() == 0 || category.getCategoryName().isEmpty()) {
			throw new EmptyInputException("204", "Input field is empty, please check again.");
		}
		Category existingCategory = categoryRepo.findById(category.getCategoryId()).orElse(null);
		if (existingCategory == null) {
			Category savedCategory = categoryRepo.save(category);
			return savedCategory;
		} else
			throw new ObjectAlreadyExistException("400", "Category with id " + category.getCategoryId() + " And Name "
					+ category.getCategoryName() + " Already Exist.");
	}

	public Category updateCategory(Category category) {
		Category existingCategory = categoryRepo.findById(category.getCategoryId()).orElse(null);
		if (existingCategory == null) {
			throw new NoSuchObjectExistException("204", "No such Category exists.");
		} else {
			existingCategory.setCategoryName(category.getCategoryName());
			existingCategory.setCategoryEnabled(category.isCategoryEnabled());
			categoryRepo.save(existingCategory);
			return existingCategory;
		}
	}

	public List<Category> getAllCategories() {
		List<Category> categoryList = categoryRepo.findAll();
		if (categoryList.isEmpty()) {
			throw new RuntimeException("The list you are requesting is empty");
		} else {
			return categoryList;
		}
	}

	public Category getCategoryById(int catId) {
		Category retrievedCat = categoryRepo.findById(catId).get();
		if (retrievedCat != null) {
			return retrievedCat;
		} else {
			throw new NoSuchElementException("The category with " + catId + " Id is not available.");
		}
	}

	public String deleteCategoryById(int catId) {
		Category existingCategory = categoryRepo.findById(catId).get();
		if (existingCategory != null) {
			categoryRepo.deleteById(catId);
			return "Category Deleted Successfully";
		} else {
			return "Category with Id provided is not present";
		}
	}

	public Category findByCategoryName(String name) {
		Category category = categoryRepo.findByCategoryName(name);
		if (category != null)
			return category;
		else
			throw new NoSuchElementException(
					"The category with the name " + name + " is not present. Please check Category name.");
	}

	public List<Category> findBycategoryEnabled(boolean categoryEnabled) {
		List<Category> listOfCategory = categoryRepo.findByCategoryEnabled(categoryEnabled);
		if (listOfCategory.isEmpty()) {
			throw new RuntimeException("The List you are requesting with category availability is empty");
		} else {
			return listOfCategory;
		}
	}

	public List<String> getJoinInformation() {
		// TODO Auto-generated method stub
		List<String> brandlst = categoryRepo.getJoinInformation();
		if (brandlst.isEmpty()) {
			throw new NoSuchElementException("the List you are requesting is empty");
		} else {
			return brandlst;
		}
	}
	
	public List<String> getAllJoinInformation() {
		List<String> productlst = categoryRepo.getAllJoinInformationByQuery();
		if(productlst.isEmpty()) {
			throw new NoSuchElementException("The list you are requesting is empty");
		}else {
			return productlst;
		}
	}

}

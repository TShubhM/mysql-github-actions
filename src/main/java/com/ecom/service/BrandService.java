package com.ecom.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.EmptyInputException;
import com.ecom.exception.ObjectAlreadyExistException;
import com.ecom.model.Brand;
import com.ecom.repository.BrandRepository;

@Service
public class BrandService {
	@Autowired
	public BrandRepository brandRepo;


//	Saving new Brand in the DB
	public Brand addBrand(Brand brand) {
		List<Brand> existingList = brandRepo.findByCategoryId(brand.getCategoryId());
		if(existingList.isEmpty()) {
			throw new EmptyInputException("204","The Category is not present to add your brand.");
		}
		
		if (brand.getBrandId() == 0 || brand.getBrandName().isEmpty() || brand.getBrandLogo().isEmpty()
				|| brand.getCategoryId() == 0) {
			throw new EmptyInputException("204", "Input field is empty, please check again.");
		}
		Brand existingBrand = brandRepo.findById(brand.getBrandId()).orElse(null);
		if (existingBrand == null) {
			Brand savedBrand = brandRepo.save(brand);
			return savedBrand;
		} else
			throw new ObjectAlreadyExistException("400",
					"Brand with Id " + brand.getBrandId() + " And Name " + brand.getBrandName() + " already exist");
	}

//	Fetching all Brands List from DB
	public List<Brand> getAllBrands() {
		List<Brand> brandList = brandRepo.findAll();
		if (brandList.isEmpty()) {
			throw new RuntimeException("The list you are requesting is empty.");
		} else {
			return brandList;
		}
	}

//	Fetching List by Category Id
	public List<Brand> getBrandByCategoryId(int catId) {
		List<Brand> brandList = brandRepo.findByCategoryId(catId);
		if (brandList.isEmpty()) {
			throw new RuntimeException("The list you are requesting is empty.");
		} else {
			return brandList;
		}
	}

//	Getting brand by providing brandId
	public Brand getBrandById(int brandId) {
//		Brand retrievedBrand = brandRepo.findById(brandId).get();
		return brandRepo.findById(brandId)
				.orElseThrow(() -> new NoSuchElementException("No Brand present with Id " + brandId));
	}

//	Deleting brand with brandId from database
	public String deleteBrandById(int brandId) {
		Brand existingBrand = brandRepo.findById(brandId).get();
		if (existingBrand != null) {
			brandRepo.deleteById(brandId);
			return "Brand deleted Successfully";
		} else {
			return "Category with Id " + brandId + " is not present.";
		}

	}

//	Updating the brand object present in the DB
	public Brand updateBrand(Brand brand) {
		Brand existingBrand = brandRepo.findById(brand.getBrandId()).orElse(null);
		if (existingBrand != null) {
			existingBrand.setBrandLogo(brand.getBrandLogo());
			existingBrand.setBrandName(brand.getBrandName());
			existingBrand.setCategoryId(brand.getCategoryId());
			brandRepo.save(existingBrand);
			return existingBrand;
		} else {
			throw new NoSuchElementException("Brand with Given Id " + brand.getBrandId() + " is not present.");
		}
	}

//	Getting brand information by providing brandName
	public Brand getByBrandName(String brandName) {
		Brand brandRetrieved = brandRepo.findByBrandName(brandName);
		if (brandRetrieved != null) {
			return brandRetrieved;
		} else {
			throw new NoSuchElementException("brand with " + brandName + " is not present.");
		}
	}

	public List<Brand> getBrandsWithQuery() {
		List<Brand> brandList = brandRepo.findAllBrands();
		return brandList;
	}
	
	public List<String> getJoinInformation() {
		// TODO Auto-generated method stub
		List<String> brandlst = brandRepo.getJoinInformation();
		if (brandlst.isEmpty()) {
			throw new NoSuchElementException("the List you are requesting is empty");
		} else {
			return brandlst;
		}
	}

	public List<String> getAllJoinInformation() {
		List<String> productlst = brandRepo.getAllJoinInformationByQuery();
		if(productlst.isEmpty()) {
			throw new NoSuchElementException("The list you are requesting is empty");
		}else {
			return productlst;
		}
	}
}

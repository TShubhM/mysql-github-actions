package com.ecom.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.EmptyInputException;
import com.ecom.exception.NoSuchObjectExistException;
import com.ecom.exception.ObjectAlreadyExistException;
import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	public ProductRepository prodRepo;

	public Product addProduct(Product product) {
		List<Product> existingProd = prodRepo.findByBrandId(product.getBrandId());
		if(existingProd.isEmpty()) {
			throw new EmptyInputException("204","The brand is not present to your product.");
		}
		
		if (product.getProductId() == 0 || product.getBrandId() == 0 || product.getProductDescription().isEmpty()
				|| product.getProductImage().isEmpty() || product.getProductName().isEmpty()
				|| product.getProductPrice() == 0) {
			throw new EmptyInputException("403", "Input field is empty, please check again.");
		}
		Product existingProduct = prodRepo.findById(product.getProductId()).orElse(null);
		if (existingProduct == null) {
			Product savedProduct = prodRepo.save(product);
			return savedProduct;
		} else
			throw new ObjectAlreadyExistException("400", "Product with Id " + product.getProductId() + " And Name "
					+ product.getProductName() + " Already Exist.");

	}

	public List<Product> getAllProducts() {
		List<Product> productList = prodRepo.findAll();
		if (productList.isEmpty()) {
			throw new RuntimeException("The list you are requesting is empty.");
		} else {
			return productList;
		}

	}

	public Product getProductById(int prodId) {
//		Product productRetrieved = prodRepo.findById(prodId).get();
		return prodRepo.findById(prodId)
				.orElseThrow(() -> new NoSuchElementException("No Product present with Id " + prodId));
	}

	public String deleteProductById(int prodId) {
		Product existingProduct = prodRepo.findById(prodId).get();
		if (existingProduct != null) {
			prodRepo.deleteById(prodId);
			return "Product Deleted Successfully";
		} else {
			return "Product with Id " + prodId + " is not present.";
		}
	}

	public Product updateProduct(Product product) {
		Product existingProduct = prodRepo.findById(product.getProductId()).orElse(null);
		if (existingProduct == null) {
			throw new NoSuchObjectExistException("204",
					"No such Product with this Id " + product.getProductId() + " does not exist.");
		} else {
			existingProduct.setBrandId(product.getBrandId());
			existingProduct.setProductDescription(product.getProductDescription());
			existingProduct.setProductImage(product.getProductImage());
			existingProduct.setProductName(product.getProductName());
			existingProduct.setProductPrice(product.getProductPrice());
			prodRepo.save(existingProduct);
			return existingProduct;
		}
	}

	public List<Product> getProductByBrandId(int brandId) {
		List<Product> listOfAllProducts = prodRepo.findByBrandId(brandId);
		if (listOfAllProducts.isEmpty()) {
			throw new RuntimeException("The list you are requesting is empty.");
		} else {
			return listOfAllProducts;
		}
	}

	public List<Product> getProductByProductNameAndBrandId(String productName, int brandId) {
		List<Product> prodList = prodRepo.findByProductNameAndBrandId(productName, brandId);
		if (prodList.isEmpty()) {
			throw new RuntimeException("The list you are requesting is empty.");
		} else {
			return prodList;
		}

	}

	public Product getProductByProductName(String productName) {
		Product product = prodRepo.findByProductName(productName);
		if (product != null) {
			return product;
		} else {
			throw new NoSuchElementException("The category with " + productName + " is not present.");
		}
	}

	public List<Product> getProductByPriceBetween(double productPrice1, double productPrice2) {
		List<Product> productList = prodRepo
				.findByProductPriceGreaterThanEqualAndProductPriceLessThanEqual(productPrice1, productPrice2);
		if (productList.isEmpty()) {
			throw new RuntimeException("The list you are requesting is empty.");
		} else {
			return productList;
		}

	}
	
//	public List<String> getAllJoinInformation(){
//		List<String> productlst = prodRepo.getAllJoinInformation();
//		if(productlst.isEmpty()) {
//			throw new NoSuchElementException("The list you are requesting is empty");
//		}else {
//			return productlst;
//		}
//	}

	

}

package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.model.Product;
import com.ecom.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService prodService;

	@PostMapping("/save")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product savedProduct = prodService.addProduct(product);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> listOfProducts = prodService.getAllProducts();
		return new ResponseEntity<List<Product>>(listOfProducts, HttpStatus.FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		Product productRetrieved = prodService.getProductById(id);
		return new ResponseEntity<Product>(productRetrieved, HttpStatus.FOUND);
	}

	@GetMapping("/get/{brandId}")
	public ResponseEntity<List<Product>> getProductByBrandId(@PathVariable int brandId) {
		List<Product> prodList = prodService.getProductByBrandId(brandId);
		return new ResponseEntity<List<Product>>(prodList, HttpStatus.FOUND);
	}

	@GetMapping("/byBrandName/{productName}")
	public ResponseEntity<Product> getProductByProductName(@PathVariable String productName) {
		Product productRetrieved = prodService.getProductByProductName(productName);
		return new ResponseEntity<Product>(productRetrieved, HttpStatus.FOUND);
	}

	@GetMapping("/nameAndBrandId/{name}/{brandId}")
	public ResponseEntity<List<Product>> getProductByProductNameAndBrandId(@PathVariable String name,
			@PathVariable int brandId) {
		List<Product> prodList = prodService.getProductByProductNameAndBrandId(name, brandId);
		return new ResponseEntity<List<Product>>(prodList, HttpStatus.FOUND);
	}

	@GetMapping("/betweenRange/{price1}/{price2}")
	public ResponseEntity<List<Product>> getProductByPriceBetween(@PathVariable long price1,
			@PathVariable long price2) {
		List<Product> prodList = prodService.getProductByPriceBetween(price1, price2);
		return new ResponseEntity<List<Product>>(prodList, HttpStatus.FOUND);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProdById(@PathVariable int id) {
		prodService.deleteProductById(id);
		return new ResponseEntity<String>("Product Deleted successfully", HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	public ResponseEntity<Product> updateProdById(@RequestBody Product product) {
		Product productSaved = prodService.updateProduct(product);
		return new ResponseEntity<Product>(productSaved, HttpStatus.OK);
	}

}

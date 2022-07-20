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

import com.ecom.model.Brand;
//import com.ecom.repository.BrandRepository;
//import com.ecom.repository.CategoryRepository;
import com.ecom.service.BrandService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("brand")
public class BrandController {
	@Autowired
	private BrandService brandService;
	
//	@Autowired
//	private CategoryRepository categoryRepository;
//	
//	@Autowired
//	private BrandRepository brandRepository;

	@PostMapping("/save")
	@ApiOperation(value = "Add the brand with brand object",notes = "Provide a JSON Brand Object to store new brand")
	public ResponseEntity<Brand> addBrand(@RequestBody Brand brand){
		Brand savedBrand = brandService.addBrand(brand);
		return new ResponseEntity<Brand>(savedBrand, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Brand>> getAllBrands() {
		List<Brand> listOfBrands = brandService.getAllBrands();
		return new ResponseEntity<List<Brand>>(listOfBrands, HttpStatus.FOUND);
	}

	@GetMapping("/load/{id}")
	public ResponseEntity<Brand> getBrandById(@PathVariable int id) {
		Brand brandRetrieved = brandService.getBrandById(id);
//		if()
		return new ResponseEntity<Brand>(brandRetrieved, HttpStatus.FOUND);
	}

	@GetMapping("/findByCatId/{catId}")
	public ResponseEntity<List<Brand>> getBrandByCategoryId(@PathVariable int catId) {
		List<Brand> brandList = brandService.getBrandByCategoryId(catId);
		return new ResponseEntity<List<Brand>>(brandList, HttpStatus.FOUND);
	}

	@GetMapping("findByBrandName/{name}")
	public ResponseEntity<Brand> getBrandByBrandName(@ApiParam(value = "provide brand name",required = true)@PathVariable String name) {
		Brand brandRetrieved = brandService.getByBrandName(name);
		return new ResponseEntity<Brand>(brandRetrieved, HttpStatus.FOUND);
	}

	@GetMapping("allWithQuery")
	public ResponseEntity<List<Brand>> getBrandWithQuery() {
		List<Brand> brandlst = brandService.getBrandsWithQuery();
		return new ResponseEntity<List<Brand>>(brandlst, HttpStatus.FOUND);
	}
	
	@GetMapping("/allJoin")
	public List<String> getJoinInformationWithQuery(){
		return brandService.getAllJoinInformation();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBrandById(@PathVariable int id) {
		Brand brand = brandService.getBrandById(id);
		if (brand.getBrandId() != 0) {
			brandService.deleteBrandById(id);
			return new ResponseEntity<String>("Brand Deleted Successfully", HttpStatus.ACCEPTED);
		} else
			throw new RuntimeException("Brand not found for this id " + id);
	}

	@PutMapping("/update")
	public ResponseEntity<Brand> updateBrandById(@RequestBody Brand brand) {
		Brand brandSaved = brandService.updateBrand(brand);
		return new ResponseEntity<Brand>(brandSaved, HttpStatus.OK);
	}
	
	@GetMapping("/getInfo")
	public List<String> getJoinInformation() {
		return brandService.getJoinInformation();
	}

}

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

import com.ecom.model.Category;
import com.ecom.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService catService;

	@PostMapping("/save")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category savedCat = catService.addCategory(category);
		return new ResponseEntity<Category>(savedCat, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> listOfCats = catService.getAllCategories();
		return new ResponseEntity<List<Category>>(listOfCats, HttpStatus.OK);
	}

	@GetMapping("/load/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
		Category catRetrieved = catService.getCategoryById(id);
		return new ResponseEntity<Category>(catRetrieved, HttpStatus.FOUND);
	}

	@GetMapping("/retrieve/{name}")
	public ResponseEntity<Category> getCatByName(@PathVariable String name) {
		Category catRetrieved = catService.findByCategoryName(name);
		return new ResponseEntity<Category>(catRetrieved, HttpStatus.FOUND);
	}

	@GetMapping("/catEnabled/{catEnable}")
	public ResponseEntity<List<Category>> getCategoriesByCategoryEnabled(@PathVariable boolean catEnable) {
		List<Category> listOfCat = catService.findBycategoryEnabled(catEnable);
		return new ResponseEntity<List<Category>>(listOfCat, HttpStatus.FOUND);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmpById(@PathVariable int id) {
		catService.deleteCategoryById(id);
		return new ResponseEntity<String>("Category Deleted Successfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/allJoin")
	public List<String> getJoinInformationWithQuery(){
		return catService.getAllJoinInformation();
	}

	@PutMapping("/update")
	public ResponseEntity<Category> updateEmpById(@RequestBody Category category) {
		Category categorySaved = catService.updateCategory(category);
		return new ResponseEntity<Category>(categorySaved, HttpStatus.CREATED);
	}

	@GetMapping("/getInfo")
	public List<String> getJoinInformation() {
		return catService.getJoinInformation();
	}
	

	
}

package com.xoriant.ecart.controller;

import java.util.List;
import java.util.Optional;

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

import com.xoriant.ecart.model.Category;
import com.xoriant.ecart.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/save")
	public ResponseEntity<String> addNewCategory(@RequestBody Category category) {

		String newCategory = categoryService.addNewCategory(category);
		return new ResponseEntity<String>(newCategory, HttpStatus.OK);
	}

	@PostMapping("/saveAll")
	public ResponseEntity<String> addNewListCategory(@RequestBody List<Category> catLists) {
		String newCategory = categoryService.addListOfCategory(catLists);
		return new ResponseEntity<String>(newCategory, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateCategory(@RequestBody Category category) {
		String updateCategory = categoryService.updateCategory(category);
		return new ResponseEntity<String>(updateCategory, HttpStatus.OK);
	}

	@PutMapping("/updateAll")
	public ResponseEntity<List<Category>> updateListsOfCategory(@RequestBody List<Category> catLists) {
		List<Category> updateCategory = categoryService.updateListsOfCategory(catLists);
		return new ResponseEntity<List<Category>>(updateCategory, HttpStatus.OK);

	}

	@GetMapping("/{categoryName}")
	public ResponseEntity<Category> getByCategoryName(@PathVariable String categoryName) {
		Category category = categoryService.findByName(categoryName);
		if (category == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@GetMapping("/find/{categoryId}")
	public ResponseEntity<Optional<Category>> getById(@PathVariable int categoryId) {
		Optional<Category> existCat = categoryService.findByCategoryId(categoryId);
		return new ResponseEntity<Optional<Category>>(existCat, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
		categoryService.deleteCategoryId(categoryId);
		return new ResponseEntity<String>("Delete Category Succesfully", HttpStatus.OK);
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<List<Category>> fetchAll() {
		List<Category> catLists = categoryService.fetchAll();
		return new ResponseEntity<List<Category>>(catLists, HttpStatus.OK);
	}
}

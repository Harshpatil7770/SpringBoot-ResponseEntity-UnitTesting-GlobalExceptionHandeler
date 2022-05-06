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

import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.service.BrandService;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

	@Autowired
	BrandService brandService;

	@PostMapping("/save")
	public ResponseEntity<Brand> addNewBrand(@RequestBody Brand brand) {
		Brand newBrand = brandService.addNewBrand(brand);
		return new ResponseEntity<Brand>(newBrand, HttpStatus.OK);
	}

	@PostMapping("/saveAll")
	public ResponseEntity<List<Brand>> addListOfBrand(@RequestBody List<Brand> brandLists) {
		List<Brand> brandList = brandService.addListOfBrands(brandLists);
		return new ResponseEntity<List<Brand>>(brandList, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand) {
		Brand updatedBrand = brandService.updateBrand(brand);
		return new ResponseEntity<Brand>(updatedBrand, HttpStatus.OK);
	}

	@PutMapping("/updateAll")
	public ResponseEntity<List<Brand>> updateListOfBrands(@RequestBody List<Brand> brandLists) {
		List<Brand> updatedLists = brandService.updateListsOfBrand(brandLists);
		return new ResponseEntity<List<Brand>>(updatedLists, HttpStatus.OK);
	}

	@GetMapping("/find/{brandName}")
	public ResponseEntity<Optional<Brand>> findByBrandName(@PathVariable String brandName) {
		Optional<Brand> existBrand = brandService.findByBrandName(brandName);
		return new ResponseEntity<Optional<Brand>>(existBrand, HttpStatus.OK);
	}

	@GetMapping("/get/{brandId}")
	public ResponseEntity<Optional<Brand>> findById(@PathVariable int brandId) {
		Optional<Brand> exitBrand = brandService.findById(brandId);
		return new ResponseEntity<Optional<Brand>>(exitBrand, HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Brand>> findAll() {
		List<Brand> brandLists = brandService.findAll();
		return new ResponseEntity<List<Brand>>(brandLists, HttpStatus.OK);
	}

	@DeleteMapping("delete/{brandId}")
	public ResponseEntity<String> deleteBrand(@PathVariable int brandId) {
		brandService.deleteBrand(brandId);
		return new ResponseEntity<String>("Deleted brand Succesfully", HttpStatus.OK);
	}
}

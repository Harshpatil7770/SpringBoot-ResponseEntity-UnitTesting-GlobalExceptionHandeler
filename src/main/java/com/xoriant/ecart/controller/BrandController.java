package com.xoriant.ecart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}

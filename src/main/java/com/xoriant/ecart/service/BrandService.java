package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import com.xoriant.ecart.model.Brand;

public interface BrandService {

	Brand addNewBrand(Brand brand);


	List<Brand> addListOfBrands(List<Brand> listsBrand);

	Brand updateBrand(Brand brand);

//	List<Brand> updateListsOfBrand(List<Brand> brandLists);
//
//	Brand findByBrandName(String brandName);
//
//	List<Brand> findByCategory(String categoryName);
//
//	Optional<Brand> findById(int brandId);
//
//	List<Brand> findAll();
//
//	void deleteBrand(int brandId);
	
	
}

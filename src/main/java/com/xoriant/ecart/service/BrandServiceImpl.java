package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.ecart.dao.BrandDao;
import com.xoriant.ecart.globalexceptionhandeler.ElementNotFoundException;
import com.xoriant.ecart.globalexceptionhandeler.UserInputExpection;
import com.xoriant.ecart.model.Brand;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private CategoryService categoryService;

	@Override
	public Brand addNewBrand(Brand brand) {
		if (brand.getBrandName().isEmpty() || brand.getBrandName().length() == 0) {
			throw new UserInputExpection();
		}

		// int existCat = brand.getCategory().getCategoryId();
//		Optional<Category> checkPresent = categoryService.findByCategoryId(existCat);
//		if (!checkPresent.isPresent()) {
//			throw new ElementNotFoundException();
//		}

		return brandDao.save(brand);
	}

	@Override
	public List<Brand> addListOfBrands(List<Brand> listsBrand) {

		for (Brand eachBrand : listsBrand) {
			if (eachBrand.getBrandName().length() == 0 || eachBrand.getBrandName().isEmpty()) {
				throw new UserInputExpection();
			}
		}
		return brandDao.saveAll(listsBrand);
	}

	@Override
	public Brand updateBrand(Brand brand) {
		Optional<Brand> existbrand = brandDao.findById(brand.getBrandId());
		if (!existbrand.isPresent()) {
			throw new ElementNotFoundException();
		}
		Brand updateBrand = brandDao.findById(brand.getBrandId()).orElse(null);
		updateBrand.setBrandId(brand.getBrandId());
		if (brand.getBrandName().length() == 0 || brand.getBrandName().isEmpty()) {
			throw new UserInputExpection();
		}
		return brandDao.save(brand);
	}

}

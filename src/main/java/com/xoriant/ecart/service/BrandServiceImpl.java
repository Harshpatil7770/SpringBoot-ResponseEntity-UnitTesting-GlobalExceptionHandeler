package com.xoriant.ecart.service;

import java.util.ArrayList;
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

	@Override
	public List<Brand> updateListsOfBrand(List<Brand> brandLists) {
		List<Brand> updatedLists = new ArrayList<Brand>();
		for (Brand checkExist : brandLists) {
			Optional<Brand> existBrand = brandDao.findById(checkExist.getBrandId());
			if (!existBrand.isPresent()) {
				throw new ElementNotFoundException();
			}

			Brand brand = brandDao.findById(checkExist.getBrandId()).orElse(null);
			brand.setBrandId(checkExist.getBrandId());
			if (checkExist.getBrandName().isEmpty() || checkExist.getBrandName().length() == 0) {
				throw new UserInputExpection();
			}
			brand.setBrandName(checkExist.getBrandName());
			brandDao.save(checkExist);
			updatedLists.add(checkExist);
		}
		return updatedLists;
	}

	@Override
	public Optional<Brand> findByBrandName(String brandName) {
		Optional<Brand> exitBrand = brandDao.findByBrandName(brandName);
		if (!exitBrand.isPresent()) {
			throw new ElementNotFoundException();
		}
		return brandDao.findByBrandName(brandName);
	}

	@Override
	public Optional<Brand> findById(int brandId) {
		Optional<Brand> existingBrand = brandDao.findById(brandId);
		if (!existingBrand.isPresent()) {
			throw new ElementNotFoundException();
		}
		return brandDao.findById(brandId);
	}

	@Override
	public List<Brand> findAll() {
		List<Brand> brandLists = brandDao.findAll();
		if (brandLists.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return brandLists;
	}

	@Override
	public void deleteBrand(int brandId) {

		Optional<Brand> existBrand = brandDao.findById(brandId);
		if (!existBrand.isPresent()) {
			throw new ElementNotFoundException();
		}
		brandDao.deleteById(brandId);
	}

}

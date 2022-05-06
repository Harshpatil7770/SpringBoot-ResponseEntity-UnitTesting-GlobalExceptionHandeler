package com.xoriant.ecart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.ecart.dao.CategoryDao;
import com.xoriant.ecart.globalexceptionhandeler.ElementNotFoundException;
import com.xoriant.ecart.globalexceptionhandeler.UserInputExpection;
import com.xoriant.ecart.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public String addNewCategory(Category category) {
		if (category.getCategoryName().isEmpty() || category.getCategoryName().length() == 0) {
			throw new UserInputExpection();
		}
		categoryDao.save(category);

		return "New Category Added";
	}

	@Override
	public String addListOfCategory(List<Category> catLists) {
		for (Category eachCat : catLists) {
			if (eachCat.getCategoryName().length() == 0 || eachCat.getCategoryName().isEmpty()) {
				throw new UserInputExpection();
			}
		}
		categoryDao.saveAll(catLists);
		return "New List Of Category Added";
	}

	@Override
	public String updateCategory(Category category) {
		Optional<Category> existCat = categoryDao.findById(category.getCategoryId());
		if (!existCat.isPresent()) {
			throw new ElementNotFoundException();
		}
		Category exitCategory = categoryDao.findById(category.getCategoryId()).orElse(null);
		exitCategory.setCategoryId(category.getCategoryId());
		if (category.getCategoryName().isEmpty() || category.getCategoryName().length() == 0) {
			throw new UserInputExpection();
		}
		exitCategory.setCategoryName(category.getCategoryName());
		categoryDao.save(category);
		return "Update Category Succesfully";
	}

	@Override
	public List<Category> updateListsOfCategory(List<Category> catLists) {

		List<Category> updatedCats = new ArrayList<Category>();
		for (Category eachCats : catLists) {
			Optional<Category> existCat = categoryDao.findById(eachCats.getCategoryId());
			if (!existCat.isPresent()) {
				throw new ElementNotFoundException();
			}

			Category existingcat = categoryDao.findById(eachCats.getCategoryId()).orElse(null);
			existingcat.setCategoryId(eachCats.getCategoryId());
			if (eachCats.getCategoryName().length() == 0 || eachCats.getCategoryName().isEmpty()) {
				throw new UserInputExpection();
			}
			existingcat.setCategoryName(eachCats.getCategoryName());
			categoryDao.save(eachCats);
			updatedCats.add(eachCats);
		}

		return updatedCats;

	}

	@Override
	public Category findByName(String categoryName) {

		Category cat = categoryDao.findByCategoryName(categoryName);
		if (cat == null) {
			throw new ElementNotFoundException();
		}
		return cat;

	}

	@Override
	public Optional<Category> findByCategoryId(int categoryId) {

		Optional<Category> existCat = categoryDao.findById(categoryId);

		if (!existCat.isPresent()) {
			throw new ElementNotFoundException();
		}

		return existCat;
	}

	@Override
	public void deleteCategoryId(int categoryId) {

		Optional<Category> existCat = categoryDao.findById(categoryId);
		if (!existCat.isPresent()) {
			throw new ElementNotFoundException();
		}
	}

	@Override
	public List<Category> fetchAll() {
		List<Category> catLists = categoryDao.findAll();

		List<Category> sortedLists = catLists.stream()
				.sorted((o1, o2) -> o1.getCategoryName().compareTo(o2.getCategoryName())).collect(Collectors.toList());

		if (catLists.isEmpty()) {
			throw new ElementNotFoundException();
		}
		return sortedLists;
	}

}

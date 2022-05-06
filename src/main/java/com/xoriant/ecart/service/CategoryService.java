package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import com.xoriant.ecart.model.Category;

public interface CategoryService {

	String addNewCategory(Category category);

	String addListOfCategory(List<Category> catLists);

	String updateCategory(Category category);

	List<Category> updateListsOfCategory(List<Category> catLists);

	Category findByName(String categoryName);

	Optional<Category> findByCategoryId(int categoryId);

	void deleteCategoryId(int categoryId);

	List<Category> fetchAll();
}

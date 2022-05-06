package com.xoriant.ecart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.ecart.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

	Category findByCategoryName(String categoryName);

}

package com.xoriant.ecart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.xoriant.ecart.dao.CategoryDao;
import com.xoriant.ecart.model.Category;
import com.xoriant.ecart.service.CategoryServiceImpl;

@SpringBootTest
public class CategoryTests {

	@Mock
	CategoryDao categoryDao;

	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;

	private static Category category1;
	private static Category category2;

	@BeforeAll
	public static void beforeAll() {
		category1 = new Category();
		category1.setCategoryId(101);
		category1.setCategoryName("SmartPhones");
		category2 = new Category();
		category2.setCategoryId(102);
		category2.setCategoryName("Laptops");
	}

	@Test
	public void addNewCategory() {

		when(categoryDao.save(category1)).thenReturn(category1);
		assertEquals("New Category Added", categoryServiceImpl.addNewCategory(category1));
	}

	@Test
	public void addListOfCategory() {
		List<Category> catLists = new ArrayList<Category>();
		catLists.add(category1);
		catLists.add(category2);
		when(categoryDao.saveAll(catLists)).thenReturn(catLists);
		assertEquals("New List Of Category Added", categoryServiceImpl.addListOfCategory(catLists));
	}

	@Test
	public void updateCategory() {
		Optional<Category> existCat = Optional.of(category1);
		when(categoryDao.findById(category1.getCategoryId())).thenReturn(existCat);
		category1.setCategoryName("I Pad");
		when(categoryDao.save(category1)).thenReturn(category1);
		// assertThat(categoryServiceImpl.updateCategory(category1)).isEqualTo(category1);
		assertEquals("Update Category Succesfully", categoryServiceImpl.updateCategory(category1));
	}

	@Test
	public void updateListsOfCategory() {
		List<Category> updateLists = new ArrayList<Category>();
		Optional<Category> existCat1 = Optional.of(category1);
		when(categoryDao.findById(category1.getCategoryId())).thenReturn(existCat1);
		category1.setCategoryName("Bike");
		updateLists.add(category1);
		Optional<Category> existCat2 = Optional.of(category2);
		when(categoryDao.findById(category2.getCategoryId())).thenReturn(existCat2);
		category2.setCategoryName("Car");
		updateLists.add(category2);

		when(categoryDao.saveAll(updateLists)).thenReturn(updateLists);
		assertEquals(updateLists, categoryServiceImpl.updateListsOfCategory(updateLists));

	}

	@Test
	public void findByName() {
		when(categoryDao.findByCategoryName(category1.getCategoryName())).thenReturn(category1);
		assertEquals(category1, categoryServiceImpl.findByName(category1.getCategoryName()));
	}

	@Test
	public void findByCategoryId() {
		Optional<Category> existCat = Optional.of(category1);
		when(categoryDao.findById(category1.getCategoryId())).thenReturn(existCat);
		assertEquals(existCat, categoryServiceImpl.findByCategoryId(category1.getCategoryId()));
	}

	@Test
	public void deleteCategoryId() {
		categoryDao.deleteById(category1.getCategoryId());
		assertThat(categoryDao.existsById(category1.getCategoryId())).isEqualTo(false);
	}

	@Test
	public void fetchAll() {
		List<Category> catLists = new ArrayList<Category>();
		catLists.add(category1);
		catLists.add(category2);
		when(categoryDao.findAll()).thenReturn(catLists);
		assertEquals(2, categoryServiceImpl.fetchAll().size());
	}
}

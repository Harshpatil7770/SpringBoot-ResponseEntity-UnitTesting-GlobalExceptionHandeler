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

import com.xoriant.ecart.dao.BrandDao;
import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.service.BrandServiceImpl;

@SpringBootTest
public class BrandTests {

	@Mock
	BrandDao brandDao;

	@InjectMocks
	BrandServiceImpl brandServiceImpl;

	private static Brand brand1;

	private static Brand brand2;

	@BeforeAll
	public static void beforeAll() {
		brand1 = new Brand(101, "Samsung");
		brand2 = new Brand(102, "Lenovo");
	}

	@Test
	public void addNewBrand() {
		when(brandDao.save(brand1)).thenReturn(brand1);
		assertEquals(brand1, brandServiceImpl.addNewBrand(brand1));
	}

	@Test
	public void addListOfBrands() {
		List<Brand> brandLists = new ArrayList<Brand>();
		brandLists.add(brand1);
		brandLists.add(brand2);
		when(brandDao.saveAll(brandLists)).thenReturn(brandLists);
		assertEquals(brandLists, brandServiceImpl.addListOfBrands(brandLists));
	}

	@Test
	public void updateBrand() {

		Optional<Brand> existingBrand = Optional.of(brand1);

		when(brandDao.findById(brand1.getBrandId())).thenReturn(existingBrand);
		brand1.setBrandName("I Phone");
		when(brandDao.save(brand1)).thenReturn(brand1);
		assertEquals(brand1, brandServiceImpl.updateBrand(brand1));
	}

	@Test
	public void updateListsOfBrand() {
		List<Brand> updatedLists = new ArrayList<Brand>();

		Optional<Brand> checkBrand1 = Optional.of(brand1);
		when(brandDao.findById(brand1.getBrandId())).thenReturn(checkBrand1);
		brand1.setBrandName("I Phone");
		Optional<Brand> checkBrand2 = Optional.of(brand2);
		when(brandDao.findById(brand2.getBrandId())).thenReturn(checkBrand2);
		brand2.setBrandName("LG");
		updatedLists.add(brand1);
		updatedLists.add(brand2);
		when(brandDao.saveAll(updatedLists)).thenReturn(updatedLists);
		assertEquals(updatedLists, brandServiceImpl.updateListsOfBrand(updatedLists));
	}

	@Test
	public void findByBrandName() {
		Optional<Brand> exitingBrand = Optional.of(brand1);

		when(brandDao.findByBrandName(brand1.getBrandName())).thenReturn(exitingBrand);
		assertEquals(exitingBrand, brandServiceImpl.findByBrandName(brand1.getBrandName()));
	}

//	public void findByCategory() {
//		
//	}

	@Test
	public void findById() {
		Optional<Brand> existingBrand = Optional.of(brand1);
		when(brandDao.findById(brand1.getBrandId())).thenReturn(existingBrand);
		assertEquals(existingBrand, brandServiceImpl.findById(brand1.getBrandId()));
	}

	@Test
	public void findAll() {
		List<Brand> brandLists = new ArrayList<Brand>();
		brandLists.add(brand1);
		brandLists.add(brand2);
		when(brandDao.findAll()).thenReturn(brandLists);
		assertEquals(2, brandServiceImpl.findAll().size());
	}

	@Test
	public void deleteBrand() {
		brandDao.deleteById(brand1.getBrandId());
		assertThat(brandDao.existsById(brand1.getBrandId())).isEqualTo(false);
	}
}

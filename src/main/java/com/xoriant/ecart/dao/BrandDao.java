package com.xoriant.ecart.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.ecart.model.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer> {

	Optional<Brand> findByBrandName(String brandName);

}

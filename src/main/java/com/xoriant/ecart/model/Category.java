package com.xoriant.ecart.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="categories_brands",joinColumns={@JoinColumn(name="CAT_ID")},inverseJoinColumns= {@JoinColumn(name="BRAND_ID")})
	private List<Brand> listBrand = new ArrayList<Brand>();

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int categoryId, String categoryName, List<Brand> listBrand) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.listBrand = listBrand;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Brand> getListBrand() {
		return listBrand;
	}

	public void setListBrand(List<Brand> listBrand) {
		this.listBrand = listBrand;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", listBrand=" + listBrand
				+ "]";
	}

}

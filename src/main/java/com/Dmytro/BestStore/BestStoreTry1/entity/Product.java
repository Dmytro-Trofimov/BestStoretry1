package com.Dmytro.BestStore.BestStoreTry1.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String brand;
	private String category;
	private Date created_at;
	@Column(columnDefinition = "TEXT")
	private String description;
	private String image_file_name;
	private int price;
	

	public String getImage_file_name() {
		return image_file_name;
	}
	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", category=" + category + ", created_at="
				+ created_at + ", description=" + description + ", image_file_name=" + image_file_name + ", price="
				+ price + "]";
	}
	public Product(String name, String brand, String category, Date created_at, String description,
			String image_file_name, int price) {
		super();
		this.name = name;
		this.brand = brand;
		this.category = category;
		this.created_at = created_at;
		this.description = description;
		this.image_file_name = image_file_name;
		this.price = price;
	}
	public Product() {
	}
	
	
}
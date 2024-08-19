package com.Dmytro.BestStore.BestStoreTry1.entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ProductDTO {
	@Size(min = 4, message = "the name must include more than 4 characters")
	@Size(max = 100, message = "the name  less include more than 100 characters")
	private String name;	

	@NotEmpty(message = "The brand can`t be empty")
	private String brand;
	
	@NotEmpty(message = "The category can`t be empty")
	private String category;

	@Min(0)
	private int price;
	
	@NotNull
	@Size(min = 4, message = "the description must include more than 4 characters")
	@Size(max = 100, message = "the description less include more than 100 characters")
	private String description;
	
	private MultipartFile image_file_name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getImage_file_name() {
		return image_file_name;
	}

	public void setImage_file_name(MultipartFile image_file_name) {
		this.image_file_name = image_file_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDTO [name=" + name + ", brand=" + brand + ", category=" + category + ", description="
				+ description + ", image_file_name=" + image_file_name + ", price=" + price + "]";
	}


	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

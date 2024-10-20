package com.Amozen.model;

public class Category {
    private int categoryId;         // Primary key
    private String name;            // Category name
    private double price;           // Price for the products in the category
    private double rating;          // Rating for the category
    private String description;     // Description of the category
    private String imagePath; 
    private int productId;

    
    public Category() {
		// TODO Auto-generated constructor stub
	}


	public Category(int categoryId, String name, double price, double rating, String description, String imagePath,
			int productId) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.description = description;
		this.imagePath = imagePath;
		this.productId = productId;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", price=" + price + ", rating=" + rating
				+ ", description=" + description + ", imagePath=" + imagePath + ", productId=" + productId + "]";
	}
    
      
}

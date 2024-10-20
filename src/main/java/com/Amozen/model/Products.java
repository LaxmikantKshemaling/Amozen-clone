package com.Amozen.model;

public class Products implements Comparable<Products> {
    private int productId;
    private String name;
    private double price;
    private int categoryId;
    private String imagePath;

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", categoryId=" + categoryId
                + ", imagePath=" + imagePath + "]";
    }

    @Override
    public int compareTo(Products p2) {
        // First, compare by name
        int nameComparison = this.name.compareTo(p2.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        // If names are the same, compare by price
        return Double.compare(this.price, p2.price);
    }
}

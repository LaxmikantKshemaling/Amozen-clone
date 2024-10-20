package com.Amozen.Dao;

import com.Amozen.model.Category;

import java.util.List;

public interface CategoryDAO {
	void addCategory(Category category); // Method to add a new category

	Category getCategory(int categoryId); // Method to get a category by ID

	void updateCategory(Category category); // Method to update an existing category

	void deleteCategory(int categoryId); // Method to delete a category by ID

	List<Category> getAllCategoryByProduct(int productId);// Method to get a list of all categories
}

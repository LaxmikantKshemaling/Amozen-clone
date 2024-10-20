package com.Amozen.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Amozen.Dao.CategoryDAO;
import com.Amozen.model.Category;
import com.Amozen.util.DBConnectionUtil;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
    public void addCategory(Category category) {
        String query = "INSERT INTO category (name, price, rating, description, imagePath, productId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, category.getName());
            ps.setDouble(2, category.getPrice());
            ps.setDouble(3, category.getRating());
            ps.setString(4, category.getDescription());
            ps.setString(5, category.getImagePath());
            ps.setInt(6, category.getProductId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category getCategory(int categoryId) {
        Category category = null;
        String query = "SELECT * FROM category WHERE categoryId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setName(rs.getString("name"));
                category.setPrice(rs.getDouble("price"));
                category.setRating(rs.getDouble("rating"));
                category.setDescription(rs.getString("description"));
                category.setImagePath(rs.getString("imagePath"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        String query = "UPDATE category SET name = ?, price = ?, rating = ?, description = ?, imagePath = ? WHERE categoryId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, category.getName());
            ps.setDouble(2, category.getPrice());
            ps.setDouble(3, category.getRating());
            ps.setString(4, category.getDescription());
            ps.setString(5, category.getImagePath());
            ps.setInt(6, category.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(int categoryId) {
        String query = "DELETE FROM category WHERE categoryId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Category> getAllCategoryByProduct(int productId) {
    	
    	
    	String sql = "SELECT * FROM category WHERE productId = ?";
    	
    	
    	
        // Adjust the SQL query according to your database schema.
      //  String sql = "SELECT c.* FROM categories c " +
                    // "JOIN product_categories pc ON c.categoryId = pc.categoryId " +
                //     "WHERE pc.productId = ?";

        List<Category> categoryList = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, productId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Category category = extractCategoryFromResultSet(rs);
                    categoryList.add(category);  // Fixed this line
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryList;  // Ensure the return variable matches the method's expected type
    }

    private Category extractCategoryFromResultSet(ResultSet rs) throws SQLException {
        Category categoryList = new Category();
        categoryList.setCategoryId(rs.getInt("categoryId"));
        categoryList.setName(rs.getString("name"));
        categoryList.setPrice(rs.getDouble("price"));
        categoryList.setRating(rs.getDouble("rating"));
        categoryList.setDescription(rs.getString("description"));
        categoryList.setImagePath(rs.getString("imagePath"));
        categoryList.setProductId(rs.getInt("productId"));
        return categoryList;
    }

}
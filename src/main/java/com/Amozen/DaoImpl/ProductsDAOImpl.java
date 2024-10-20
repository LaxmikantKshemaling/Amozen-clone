package com.Amozen.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Amozen.Dao.ProductDAO;
import com.Amozen.model.Products;
import com.Amozen.util.DBConnectionUtil;

public class ProductsDAOImpl implements ProductDAO {

    @Override
    public void addProduct(Products product) {
        String sql = "INSERT INTO products (name, price, categoryId, imagePath) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getCategoryId());
            statement.setString(4, product.getImagePath());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging instead
        }
    }

    @Override
    public Products getProduct(int productId) {
        String sql = "SELECT * FROM products WHERE productId = ?";
        Products product = null;
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = extractProductFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging instead
        }
        return product;
    }

    @Override
    public void updateProduct(Products product) {
        String sql = "UPDATE products SET name = ?, price = ?, categoryId = ?, imagePath = ? WHERE productId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getCategoryId());
            statement.setString(4, product.getImagePath());
            statement.setInt(5, product.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging instead
        }
    }

    @Override
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE productId = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging instead
        }
    }

    @Override
    public List<Products> getAllProducts() {
        List<Products> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet res = statement.executeQuery()) {

            while (res.next()) {
                Products product = extractProductFromResultSet(res);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging instead
        }
        return productList;
    }
    @Override
    public List<Products> searchProductsByproductName(String name) {
        String sql = "SELECT * FROM products WHERE name LIKE ?";
        List<Products> products = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%"); // Use '%' to match any characters before and after the search term
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Products product = extractProductFromResultSet(res);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging instead
        }

        return products;
    }

    private Products extractProductFromResultSet(ResultSet res) throws SQLException {
        Products product = new Products();
        product.setProductId(res.getInt("productId"));
        product.setName(res.getString("name"));
        product.setPrice(res.getDouble("price"));
        product.setCategoryId(res.getInt("categoryId"));
        product.setImagePath(res.getString("imagePath"));
        return product;
    }
}
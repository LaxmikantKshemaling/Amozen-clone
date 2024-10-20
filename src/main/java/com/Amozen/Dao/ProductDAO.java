package com.Amozen.Dao;

import java.util.List;
import com.Amozen.model.Products;

public interface ProductDAO {
    void addProduct(Products product);
    Products getProduct(int productId);
    void updateProduct(Products product);
    void deleteProduct(int productId);
    List<Products> getAllProducts();
    List<Products> searchProductsByproductName(String name);
}

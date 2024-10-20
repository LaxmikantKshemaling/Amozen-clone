package com.Amozen.controller;

import com.Amozen.Dao.ProductDAO;
import com.Amozen.DaoImpl.ProductsDAOImpl;
import com.Amozen.model.Products;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductsDAOImpl(); // Initialize DAO implementation
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String searchQuery = request.getParameter("searchQuery");

        List<Products> allProducts;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            allProducts = productDAO.searchProductsByproductName(searchQuery);
        } 
        else 
        {
            allProducts = productDAO.getAllProducts();
        }

        request.setAttribute("allProducts", allProducts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Product.jsp");
        dispatcher.forward(request, response);
    }
}

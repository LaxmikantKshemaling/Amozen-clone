package com.Amozen.controller;

import com.Amozen.Dao.CategoryDAO;
import com.Amozen.DaoImpl.CategoryDAOImpl;
import com.Amozen.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        categoryDAO = new CategoryDAOImpl(); // Initialize the CategoryDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productIdParam = request.getParameter("productId");

        if (productIdParam == null) {
            productIdParam = (String) session.getAttribute("productId"); // Use session if request param is not present
        }

        // Validate if productId is available and valid
        if (productIdParam != null && !productIdParam.isEmpty()) {
            try {
                int productId = Integer.parseInt(productIdParam);

                // Check if productId is valid
                if (productId > 0) {
                    // Fetch categories based on productId
                    List<Category> categoryList = categoryDAO.getAllCategoryByProduct(productId);

                    if ( categoryList == null ||  categoryList.isEmpty()) {
                        request.setAttribute("message", "No categories found for this product.");
                    } else {
                        request.setAttribute("categoryList",  categoryList);
                    }

                    // Save the productId in session for future use (like when returning to the cart)
                    session.setAttribute("productId", productIdParam);

                    // Forward to category list view (categoryList.jsp)
                    request.getRequestDispatcher("categoryList.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID format.");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving categories.");
                e.printStackTrace(); // Log the error for debugging
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required.");
        }
    }
}

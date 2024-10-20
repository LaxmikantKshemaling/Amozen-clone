package com.Amozen.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Amozen.DaoImpl.CategoryDAOImpl;
import com.Amozen.model.Cart;
import com.Amozen.model.CartItem;
import com.Amozen.model.Category;
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Cart cart = (Cart) session.getAttribute("cart");

    	if (cart == null) {
    	    // Initialize a new Cart if one doesn't exist
    	    cart = new Cart();
    	    session.setAttribute("cart", cart); // Ensure we store a Cart, not a HashMap
    	}
    	
    	
    	// System.out.print(cart+" Succesfully");


        String action = request.getParameter("action");

        if (action != null) 
        {
            if (action.equals("add")) 
            {
                addItemToCart(request, cart);
            } 
            else if (action.equals("update"))
            {
                updateCartItem(request, cart);
            } 
            else if (action.equals("remove")) {
                removeItemFromCart(request, cart);
            }
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("Carts.jsp");
    }

    private void addItemToCart(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CategoryDAOImpl categoryDao = new CategoryDAOImpl();
        Category categoryItem = categoryDao.getCategory(itemId); // Fetch category item

        if (categoryItem != null) {
            CartItem item = new CartItem(
                categoryItem.getCategoryId(),
                categoryItem.getProductId(),
                categoryItem.getName(),
                quantity,
                categoryItem.getPrice(),
                categoryItem.getImagePath()
                );
            
            cart.addItem(item);
            
            
        }
    }

    private void updateCartItem(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cart.updateItem(itemId, quantity);
    }

    private void removeItemFromCart(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        cart.removeItem(itemId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Carts.jsp").forward(request, response);
    }
}

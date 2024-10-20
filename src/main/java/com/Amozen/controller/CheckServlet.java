package com.Amozen.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Amozen.DaoImpl.OrderDAOImpl;
import com.Amozen.model.Cart;
import com.Amozen.model.CartItem;
import com.Amozen.model.Order;
import com.Amozen.model.User;

@WebServlet("/checkout")
public class CheckServlet extends HttpServlet {

    private OrderDAOImpl orderDAOImpl;

    @Override
    public void init() {
        orderDAOImpl = new OrderDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loggedInUser");

        // Handle missing cart
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("categoryList.jsp");
            return;
        }

        String paymentMethod = request.getParameter("paymentMethod");
        String phoneNo = request.getParameter("phoneNo");
        String address = request.getParameter("address");

        // Validate required fields
        if (paymentMethod == null || paymentMethod.isEmpty() || phoneNo == null || phoneNo.isEmpty() || address == null || address.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("Checkout.jsp").forward(request, response);
            return;
        }

        try {
            // Check if userId is available in the session
            if (user == null) {
                response.sendRedirect("Login.jsp");
                return;
            }

            // Process each cart item and create order
            Order order = new Order();
            order.setUserId(user.getUserId()); // Set the userId here

            order.setModeOfPayment(paymentMethod);
            order.setAddress(address);
            order.setPhoneNo(phoneNo);
            order.setOrderDate(new Date()); // Set current date and time
            order.setStatus("Pending"); // Set an initial status

            double totalAmount = 0;
            for (CartItem item : cart.getItems().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }
            order.setTotalAmount(totalAmount);

            int generatedOrderId = orderDAOImpl.addOrder(order);
            order.setOrderId(generatedOrderId);

            System.out.print(order);

            // Clear the cart
            session.removeAttribute("cart");
            session.setAttribute("orderList", order); // Store the created order in the session

            response.sendRedirect("confirmation.jsp");
        } catch (Exception e) {
            // Log error and redirect to an error page
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your order. Please try again.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}
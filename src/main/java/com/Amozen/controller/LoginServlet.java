package com.Amozen.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Amozen.DaoImpl.UserDAOImpl;
import com.Amozen.model.User;

@WebServlet("/Sign")
public class LoginServlet extends HttpServlet {
    private UserDAOImpl userDAOImpl;

    @Override
    public void init() {
        userDAOImpl = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // Get user by username and password
        User user = userDAOImpl.getUserByUsernameAndPassword(name, password);

        if (user != null) {
            HttpSession session = request.getSession();

            // Set the user object in the session
            session.setAttribute("loggedInUser", user);

            // Optionally, store userId in session if needed

            // Debug output to check user object

            // Redirect to products page
            response.sendRedirect("products"); // Adjust the redirect target as needed

        } else {
            // Invalid login attempt
            request.setAttribute("errorMessage", "Invalid name or password");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}

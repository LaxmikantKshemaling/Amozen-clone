package com.Amozen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Amozen.DaoImpl.UserDAOImpl;
import com.Amozen.model.User;

@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {

    private UserDAOImpl userDAOImpl;

    @Override
    public void init() {
        userDAOImpl = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("number");

        User user = new User(name, email, phone, password);
        
        // Add user to the database
        userDAOImpl.addUserSignup(user);
        
        // Redirect to Login.jsp instead of SignUp.jsp
        response.sendRedirect("Login.jsp?success=true");
    }
}


package com.users;

import com.users.User;
import com.users.UserDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/electricity_bill_calculator_app?useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "Thusitha@123";
        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String username = request.getParameter("username");
        String accountNumber = request.getParameter("account_number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int userType = Integer.parseInt(request.getParameter("usertype"));
        int userInfo = Integer.parseInt(request.getParameter("userinfo"));


        User newUser = new User(fname, lname, username, accountNumber, email, password, userType, userInfo);

        try {
            userDAO.connect();
            userDAO.addUser(newUser);
            userDAO.disconnect();
            response.sendRedirect("superAdmin.jsp");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

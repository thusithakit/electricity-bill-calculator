package com.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/electricity_bill_calculator_app?useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "Thusitha@123";
        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("userId"));
        System.out.println(id);
        try {
            userDAO.deleteUser(id);
            response.sendRedirect("superAdmin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}

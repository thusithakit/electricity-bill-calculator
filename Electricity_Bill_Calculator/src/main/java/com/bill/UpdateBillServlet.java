package com.bill;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.users.UserDAO;
import com.bill.MonthlyUsageDAO;
import com.users.User;
import com.bill.UpdateBill;

@WebServlet("/updateBill")
public class UpdateBillServlet extends HttpServlet {

    private UserDAO userDAO;
    private MonthlyUsageDAO monthlyUsageDAO;


    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/electricity_bill_calculator_app?useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "Thusitha@123";
        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("name");
        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String accountNumber = request.getParameter("customer");
        int currentMonth = Integer.parseInt(request.getParameter("current_month"));

        try {
            UpdateBill updateBill = MonthlyUsageDAO.getUserByAccountNumber(accountNumber);
            int lastMonth = updateBill.getCurrentMonth();
//                UpdateBill updateMonthlyUsage = monthlyUsageDAO.updateMonthlyUsage(accountNumber,lastMonth,currentMonth);
//                monthlyUsages.add(updateMonthlyUsage);

            monthlyUsageDAO = new MonthlyUsageDAO();
            monthlyUsageDAO.updateMonthlyUsage(accountNumber,lastMonth,currentMonth);
            response.sendRedirect("billReader.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

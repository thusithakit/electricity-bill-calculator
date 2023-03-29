package com.register;

import com.users.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String username = request.getParameter("username");
        String account_num = request.getParameter("account_num");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Integer user_type = Integer.parseInt(request.getParameter("usertype"));
        Integer user_info = Integer.parseInt(request.getParameter("userinfo"));
        RequestDispatcher dispatcher = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricity_bill_calculator_app?useSSL=false","root","Thusitha@123");
            PreparedStatement pst = con.prepareStatement("insert into users(fname,lname,username,account_number,email,password,usertype,user_info) values(?,?,?,?,?,?,?,?) ");
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, username);
            pst.setString(4, account_num);
            pst.setString(5, email);
            pst.setString(6, password);
            pst.setInt(7, user_type);
            pst.setInt(8, user_info);

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                User user = new User(fname, lname, username, account_num, email, password, user_type);
                request.setAttribute("user", user);
                request.setAttribute("status", "success");
                dispatcher = request.getRequestDispatcher("login.jsp");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        dispatcher.forward(request, response);
    }
}

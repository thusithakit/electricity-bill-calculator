package com.register;
import com.users.User;

import com.users.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("username");
        String user_password = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricity_bill_calculator_app?useSSL=false", "root", "Thusitha@123");
            PreparedStatement pst = con.prepareStatement("select * from users where username = ? and password = ?");
            pst.setString(1, user_name);
            pst.setString(2, user_password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int user_type = rs.getInt("usertype");
                String accountNumber = rs.getString("account_number");
                session.setAttribute("name", rs.getString("username"));
                List<User> users = new ArrayList<>();
                PreparedStatement pst2 = con.prepareStatement("SELECT * FROM users");
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    int userType = rs2.getInt("usertype");
                    String fname = rs2.getString("fname");
                    String lname = rs2.getString("lname");
                    String accountnumber = rs2.getString("account_number");
                    String email = rs2.getString("email");
                    String username = rs2.getString("username");
                    String password = rs2.getString("password");
                    int id = rs2.getInt("id");
                    User user = new User(id, fname, lname, username, accountnumber, email, password, userType);
                    users.add(user);
                }
                session.setAttribute("users", users);

                if (user_type == 0) {
                    dispatcher = request.getRequestDispatcher("superAdmin.jsp");
                    System.out.println("super admin");
                } else if (user_type == 1) {
                    dispatcher = request.getRequestDispatcher("billReader.jsp");
                    System.out.println("bill reader");
                } else {
                    PreparedStatement pst3 = con.prepareStatement("SELECT * FROM monthly_usage WHERE account_number = ?");
                    pst3.setString(1, accountNumber);
                    ResultSet rs3 = pst3.executeQuery();
                    if (rs3.next()) {
                        session.setAttribute("last_month", rs3.getInt("last_month"));
                        session.setAttribute("current_month", rs3.getInt("current_month"));
                    }
                    int user_info = rs.getInt("user_info");
                    if (rs.wasNull()) {
                        user_info = 5; // set a default value
                    }
                    session.setAttribute("user_info", user_info);
                    System.out.println(session.getAttribute("user_info").getClass().getName());

                    dispatcher = request.getRequestDispatcher("index.jsp");
                    System.out.println("normal");
                    System.out.println("user_info = " + user_info);
                }
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
                System.out.println("failed");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

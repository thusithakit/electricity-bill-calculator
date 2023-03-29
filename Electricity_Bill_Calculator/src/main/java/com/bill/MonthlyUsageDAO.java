package com.bill;

import java.sql.*;

public class MonthlyUsageDAO {

    private Connection connection;

    public MonthlyUsageDAO() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/electricity_bill_calculator_app?useSSL=false";
        String username = "root";
        String password = "Thusitha@123";

        this.connection = DriverManager.getConnection(url, username, password);
    }

    public void updateMonthlyUsage(String accountNumber, int lastMonth, int currentMonth) throws SQLException {
        String query = "UPDATE monthly_usage SET last_month = ?, current_month = ? WHERE account_number = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, lastMonth);
            statement.setInt(2, currentMonth);
            statement.setString(3, accountNumber);

            statement.executeUpdate();
        }
    }
    public static UpdateBill getUserByAccountNumber(String accountNumber) throws SQLException {
        UpdateBill updateBill = null;
        String sql = "SELECT * FROM monthly_usage WHERE account_number = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricity_bill_calculator_app?useSSL=false", "root", "Thusitha@123");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int last_month = rs.getInt("last_month");
                    int current_month = rs.getInt("current_month");
                    updateBill = new UpdateBill(accountNumber,last_month,current_month);
                }
            }
        }
        return updateBill;
    }
}

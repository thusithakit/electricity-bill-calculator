package com.units;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnitPriceDAO {
    private Connection connection;
    private final String SELECT_ALL_UNIT_PRICES_QUERY = "SELECT * FROM unit_prices";
    private static final String SELECT_UNIT_PRICE_QUERY = "SELECT `0-30`, `31-60`, `61-90`, `91-120`, `121-180`, `180+` " +
            "FROM unit_prices " +
            "WHERE user_info = ?";

    public UnitPriceDAO() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/electricity_bill_calculator_app?useSSL=false";
        String username = "root";
        String password = "Thusitha@123";

        this.connection = DriverManager.getConnection(url, username, password);
    }

    public List<UnitPrice> getAllUnitPrices() throws SQLException {
        List<UnitPrice> unitPrices = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_UNIT_PRICES_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int user_info = resultSet.getInt("user_info");
                int range1 = resultSet.getInt("0-30");
                int range2 = resultSet.getInt("31-60");
                int range3 = resultSet.getInt("61-90");
                int range4 = resultSet.getInt("91-120");
                int range5 = resultSet.getInt("121-180");
                int range6 = resultSet.getInt("180+");
                UnitPrice unitPrice = new UnitPrice(user_info, range1, range2, range3, range4, range5, range6);
                unitPrices.add(unitPrice);
                System.out.println(unitPrice);

            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return unitPrices;
    }

    public UnitPrice getUnitPrice(int user_info) throws SQLException {
        UnitPrice unitPrice = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_UNIT_PRICE_QUERY);
            preparedStatement.setInt(1, user_info);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int range1 = resultSet.getInt("0-30");
                int range2 = resultSet.getInt("31-60");
                int range3 = resultSet.getInt("61-90");
                int range4 = resultSet.getInt("91-120");
                int range5 = resultSet.getInt("121-180");
                int range6 = resultSet.getInt("180+");
                unitPrice = new UnitPrice(user_info, range1, range2, range3, range4, range5, range6);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return unitPrice;
    }
    public static synchronized double calculateBillAmount(int user_info, int lastMonthReading, int currentMonthReading) throws SQLException {
        double billAmount =0;
        int monthlyReading = currentMonthReading - lastMonthReading;
        UnitPriceDAO dao = new UnitPriceDAO();
        UnitPrice unitPrice = dao.getUnitPrice(user_info);
        if (unitPrice != null) {
            if (monthlyReading <= 30) {
                billAmount = monthlyReading * unitPrice.getRange1();
            } else if (monthlyReading <= 60) {
                int diff = monthlyReading - 30;
                billAmount = 30 * unitPrice.getRange1() + diff * unitPrice.getRange2();
            } else if (monthlyReading <= 90) {
                int diff = monthlyReading - 60;
                billAmount = 30 * unitPrice.getRange1() + 30 * unitPrice.getRange2() + diff * unitPrice.getRange3();
            } else if (monthlyReading <= 120) {
                int diff = monthlyReading - 90;
                billAmount = 30 * unitPrice.getRange1() + 30 * unitPrice.getRange2() + 30 * unitPrice.getRange3() + diff * unitPrice.getRange4();

            } else if (monthlyReading <= 180) {
                int diff = monthlyReading - 120;
                billAmount = 30 * unitPrice.getRange1() + 30 * unitPrice.getRange2() + 30 * unitPrice.getRange3() + 30 * unitPrice.getRange4() + diff * unitPrice.getRange5();

            } else {
                int diff = monthlyReading - 180;
                billAmount = 30 * unitPrice.getRange1() + 30 * unitPrice.getRange2() + 30 * unitPrice.getRange3() + 30 * unitPrice.getRange4() + 30 * unitPrice.getRange5() + diff * unitPrice.getRange6();
            }
        }
        System.out.println(unitPrice);
        System.out.println(billAmount);
        return billAmount;
    }

}
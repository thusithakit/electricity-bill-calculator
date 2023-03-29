package com.users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        connect();
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            String username = resultSet.getString("username");
            String accountNumber = resultSet.getString("account_number");
            Optional<String> accountNumberOptional = accountNumber != null ? Optional.of(accountNumber) : Optional.empty();
            String email = resultSet.getString("email");
            Optional<String> emailOptional = email != null ? Optional.of(email) : Optional.empty();
            String password = resultSet.getString("password");
            int userType = resultSet.getInt("usertype");
            User user = new User(id, fname, lname, username, accountNumber, email, password, userType);
            userList.add(user);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return userList;
    }
//    public static void main(String[] args) throws SQLException {
//        List<User> userList = listAllUsers();
//        for (User user : userList) {
//            System.out.println(user);
//        }
//    }
        public boolean addUser(User user) throws SQLException {
            String sql = "INSERT INTO users (fname, lname, username, account_number, email, password, usertype) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, user.getFname());
            statement.setString(2, user.getLname());
            statement.setString(3, user.getUsername());
            if (user.getAccountNumber() != null) {
                statement.setString(4, user.getAccountNumber());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }

            if (user.getEmail() != null) {
                statement.setString(5, user.getEmail());
            } else {
                statement.setNull(5, Types.VARCHAR);
            }
            statement.setString(6, user.getPassword());
            statement.setInt(7, user.getUserType());
            boolean rowInserted = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
            return rowInserted;
        }
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        String sql = "DELETE FROM users WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

}

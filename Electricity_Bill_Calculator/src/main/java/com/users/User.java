package com.users;

public class User {
    private int id;
    private String fname;
    private String lname;
    private String username;
    private String accountNumber;
    private String email;
    private String password;
    private int userType;
    private int userInfo;


    private String lastMonthReadings;
    private String thisMonthReadings;

    public User(int id, String username, int userType, int userInfo) {
        this.id = id;
        this.username = username;
        this.userType = userType;
        this.userInfo = userInfo;
    }

    public User(String accountNumber, String lastMonthReadings, String thisMonthReadings) {
        this.accountNumber = accountNumber;
        this.lastMonthReadings = lastMonthReadings;
        this.thisMonthReadings = thisMonthReadings;
    }

    public User(int id, String fname, String lname, String username, String accountNumber, String email, String password, int userType) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.accountNumber = accountNumber;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User(String fname, String lname, String username, String accountNumber, String email, String password, int userType) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.accountNumber = accountNumber;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User(String fname, String lname, String username, String accountNumber, String email, String password, int userType, int userInfo) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.accountNumber = accountNumber;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userInfo = userInfo;
    }
    public String getLastMonthReadings() {
        return lastMonthReadings;
    }

    public int getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(int userInfo) {
        this.userInfo = userInfo;
    }

    public void setLastMonthReadings(String lastMonthReadings) {
        this.lastMonthReadings = lastMonthReadings;
    }

    public String getThisMonthReadings() {
        return thisMonthReadings;
    }

    public void setThisMonthReadings(String thisMonthReadings) {
        this.thisMonthReadings = thisMonthReadings;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


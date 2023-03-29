package com.bill;

public class UpdateBill {
    private int id;
    private String accountNumber;
    private int lastMonth;

    public UpdateBill(String accountNumber, int lastMonth, int currentMonth) {
        this.accountNumber = accountNumber;
        this.lastMonth = lastMonth;
        this.currentMonth = currentMonth;
    }

    private int currentMonth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(int lastMonth) {
        this.lastMonth = lastMonth;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }
}


package com.zachpoly.expensetrackerapp.model;

public class Expenses {
    private int expenseid;
    private String date;
    private String category;
    private Double amount;
    private String expensename;

    public Expenses(int id, String date, String category, Double amount, String expensename) {
        this.expenseid = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.expensename = expensename;
    }

    public int getId() {
        return expenseid;
    }

    public void setId(int id) {
        this.expenseid = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getExpensename() {
        return expensename;
    }

    public void setExpensename(String expensename) {
        this.expensename = expensename;
    }
}



package com.zachpoly.expensetrackerapp;

import com.zachpoly.expensetrackerapp.util.DatabaseConnection;

import java.sql.Connection;

class DatabaseConnectionTest {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();

        Connection connection = dbConnection.getConnection();

        if (connection != null) {
            System.out.println("Connection to the database was successful!");
        } else {
            System.out.println("Failed to connect to the databse");
        }
    }
}
package com.zachpoly.expensetrackerapp.util;

import com.zachpoly.expensetrackerapp.model.Expenses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {
    public static Connection databaseLink = null;

    public static Connection getConnection() {
        String databaseUser = "ExpenseTrack";
        String databasePassword = "123456";
        String url = "jdbc:mysql://localhost:3306/expensetrackerapp";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }

    public static ObservableList<Expenses> getDataExpenses() {
        Connection conn = getConnection();
        ObservableList<Expenses> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from expenses");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Fetched Row: " + rs.getInt("expenseid"));
                list.add(new Expenses(
                        rs.getInt("expenseid"),
                        rs.getString("date"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("expensename")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

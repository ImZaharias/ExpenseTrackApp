module com.zachpoly.expensetrackerapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;
    //requires mysql.connector.j;


    opens com.zachpoly.expensetrackerapp to javafx.fxml;
    exports com.zachpoly.expensetrackerapp;
    exports com.zachpoly.expensetrackerapp.util;
    opens com.zachpoly.expensetrackerapp.util to javafx.fxml;
    exports com.zachpoly.expensetrackerapp.model;
    opens com.zachpoly.expensetrackerapp.model to javafx.fxml;
    exports com.zachpoly.expensetrackerapp.controller;
    opens com.zachpoly.expensetrackerapp.controller to javafx.fxml;
}
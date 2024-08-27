package com.zachpoly.expensetrackerapp.controller;

import com.zachpoly.expensetrackerapp.model.Expenses;
import com.zachpoly.expensetrackerapp.util.DatabaseConnection;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ExpenseTrackerAppController implements Initializable {
    @FXML
    private Button addExpenseButton;
    @FXML
    private TableColumn<Expenses, Double> amountColumn;
    @FXML
    private Button cancelButton;
    @FXML
    private TableColumn<Expenses, String> categoryColumn;
    @FXML
    private TableColumn<Expenses, String> dateColumn;
    @FXML
    private Button deleteExpenseButton;
    @FXML
    private TableColumn<Expenses, String> expensenameColumn;
    @FXML
    private TableColumn<Expenses, Integer> idColumn;
    @FXML
    private TableView<Expenses> tableView;
    @FXML
    private Button updateExpenseButton;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField dateTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField filterTextField;
    @FXML
    private TextField expensenameTextField;

    ObservableList<Expenses> listM;
    ObservableList<Expenses> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void AddExpenses() {
        conn = DatabaseConnection.getConnection();
        String sql = "insert into expenses (date, category, amount, expensename) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, dateTextField.getText());
            pst.setString(2, categoryTextField.getText());
            pst.setDouble(3, Double.parseDouble(amountTextField.getText()));
            pst.setString(4, expensenameTextField.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Expense Added successfully");
            updateTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void getSelected(MouseEvent event) {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        idTextField.setText(idColumn.getCellData(index).toString());
        categoryTextField.setText(categoryColumn.getCellData(index).toString());
        amountTextField.setText(amountColumn.getCellData(index).toString());
        dateTextField.setText(dateColumn.getCellData(index).toString());
        expensenameTextField.setText(expensenameColumn.getCellData(index).toString());
    }

    public void Edit() {

        try {
            conn = DatabaseConnection.getConnection();
            String value1 = idTextField.getText();
            String value2 = categoryTextField.getText();
            String value3 = amountTextField.getText();
            String value4 = dateTextField.getText();
            String value5 = expensenameTextField.getText();

            String sql = "update expenses set expenseid= '"+value1+"',category= '"+value2+"',amount= '"+
                    value3+"',date= '"+value4+"',expensename= '"+value5+"' where expenseid='"+value1+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            updateTable();
            searchUser();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    public void Delete() {
        conn = DatabaseConnection.getConnection();
        String sql = "delete from expenses where expenseid = ?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, idTextField.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "delete");
                updateTable();
                searchUser();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
    }

    public void updateTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Expenses, Integer>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("category"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Expenses, Double>("amount"));
        expensenameColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("expensename"));

        listM = DatabaseConnection.getDataExpenses();
        tableView.setItems(listM);
    }

    @FXML
    public void searchUser() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Expenses, Integer>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("category"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Expenses, Double>("amount"));
        expensenameColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("expensename"));

        dataList = DatabaseConnection.getDataExpenses();
        tableView.setItems(dataList);

        FilteredList<Expenses> filteredData = new FilteredList<>(dataList, b -> true);

        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(expense -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (expense.getCategory().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (expense.getExpensename().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (String.valueOf(expense.getDate()).indexOf(lowerCaseFilter) != -1)
                    return true;
                else if (String.valueOf(expense.getAmount()).indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false;

            });
        });

        SortedList<Expenses> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        searchUser();
    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}


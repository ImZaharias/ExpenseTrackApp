package com.zachpoly.expensetrackerapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    @FXML
    private ImageView brandingImageView;
    @FXML
    private Button cancelButton;
    @FXML
    private Button getStartedButton;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/resources/images/anime-character.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }

    public void getStartedButtonOnAction() {
        createExpenseTrackerApp();
        Stage stage = (Stage) getStartedButton.getScene().getWindow();
        stage.close();
    }

    public void signupButtonOnAction() {
        createSignup();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction() {
        createLogin();
    }

    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void createExpenseTrackerApp() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/zachpoly/expensetrackerapp/expensetrackerapp.fxml"));
            Stage expenseTrackAppStage = new Stage();
            expenseTrackAppStage.initStyle(StageStyle.UNDECORATED);
            expenseTrackAppStage.setScene(new Scene(root, 733, 588 ));
            expenseTrackAppStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createSignup() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/zachpoly/expensetrackerapp/signup.fxml"));
            Stage signupStage = new Stage();
            signupStage.initStyle(StageStyle.UNDECORATED);
            signupStage.setScene(new Scene(root, 614, 618 ));
            signupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/zachpoly/expensetrackerapp/login.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root, 300, 328 ));
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}

package com.example.wokrpls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class forgotPasswordUsernameScene {
    @FXML
    TextField usernameTextField;
    @FXML
    TextField confirmUsernameTextField;
    @FXML
    Hyperlink backToLoginLink;
    @FXML
    Button confirmUsernameButton;

    public void confirmUsername(ActionEvent event) throws IOException {
        String username=usernameTextField.getText();
        String username2=confirmUsernameTextField.getText();

        if(!username.equals(username2)){
            Alert a=new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Username Mismatch");
            a.setContentText("The usernames do not match!");
            a.show();
        }
        else if(username.isBlank() || username2.isBlank()){
            Alert a=new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Mandatory Fields Empty!");
            a.setContentText("Please fill both username fields!");
            a.show();
        }
        else{
            serverBridge sb=new serverBridge();
            sb.getSecuirtyQuestion(username);
            LoginScreen ls=new LoginScreen();
            ls.saveUserName(username);
            Parent part = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("forgotPasswordQuestionScene.fxml")));
            Stage stage1 = new Stage();
            Scene scene = new Scene(part);
            stage1.setScene(scene);
            stage1.show();
            Stage stage = (Stage)backToLoginLink.getScene().getWindow();
            // do what you have to do
            stage.close();

        }

    }



    public void backToLogin() throws IOException {
        Parent part = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginScreen.fxml")));
        Stage stage1 = new Stage();
        Scene scene = new Scene(part);
        stage1.setScene(scene);
        stage1.show();
        Stage stage = (Stage)backToLoginLink.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}

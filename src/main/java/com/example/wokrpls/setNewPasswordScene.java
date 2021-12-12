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

public class setNewPasswordScene {
    @FXML
    Button confirmPasswordButton;
    @FXML
    TextField newPasswordTextField;
    @FXML
    TextField confirmNewPasswordTextField;
    @FXML
    Hyperlink backToLoginLink;

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
    public void confirmPassword(ActionEvent action){
        String password1=newPasswordTextField.getText();
        String password2=confirmNewPasswordTextField.getText();

        if(! password1.equals(password2)){
            Alert a=new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("PASSWORD MISMATCH!");
            a.setContentText("The passwords dont match, please try again");
            a.show();
        }
        else{
            HelloController hc=new HelloController();
            String username=hc.readCurrentUser();
            serverBridge sb=new serverBridge();
            sb.updatePassword(username,password1);

        }
    }
}

package com.example.wokrpls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class createAccount {

    @FXML
    Hyperlink backToLoginLink;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;
    @FXML
    PasswordField confirmPasswordTextField;

    String q1="What is the name of my first pet?";
    String q2="What is the name of the street I grew up on?";
    String q3="What was the name of my middle school?";
    @FXML
    ChoiceBox securityQuestionChoiceBox;
    ObservableList<String> questions = FXCollections.observableArrayList(q1,q2,q3);

    @FXML
    TextField securityAnswerTextField;


    public void backToLogin() throws IOException {
        // function to handle when the back to login button is pressed
        // brings user back to the login page
        Parent part = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginScreen.fxml")));
        Stage stage1 = new Stage();
        Scene scene = new Scene(part);
        stage1.setScene(scene);
        stage1.show();
        Stage stage = (Stage)backToLoginLink.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void createNewAccount(){
        // function to create a new account
        String username=usernameTextField.getText();
        String password=passwordTextField.getText();
        String confirmPassword=confirmPasswordTextField.getText();
        String securityq=(String)this.securityQuestionChoiceBox.getSelectionModel().getSelectedItem();
        String securitya=securityAnswerTextField.getText();
        if(username.isBlank() || password.isBlank() || confirmPassword.isBlank() || securitya.isBlank()){
            // if any of the fields are blank, dont create account
            Alert a=new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Could not create Account");
            a.setContentText("Fill all Fields");
            a.show();
        }
        else{
            /// create the account
            serverBridge sb=new serverBridge();
            sb.createAccount(username,password,securityq,securitya);
        }
    }

    @FXML
    public void initialize(){
        // initialize the choicebox with the premade security questions
        securityQuestionChoiceBox.setItems(questions);
        securityQuestionChoiceBox.setValue(q1);
    }
}

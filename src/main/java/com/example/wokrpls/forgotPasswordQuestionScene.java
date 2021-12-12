package com.example.wokrpls;
// invoked when the user reaches the security question step of the forgot password process

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class forgotPasswordQuestionScene {
    @FXML
    Button confirmAnswerButton;
    @FXML
    Hyperlink backToLoginLink;
    @FXML
    Text securityQuestionText;
    @FXML
    TextField securityAnswerTextField;
    HelloController hc=new HelloController();
    String username=hc.readCurrentUser();


    @FXML
    void initialize(){
        // get the security question from the database for the associated username entered in the previous step

        serverBridge sb=new serverBridge();
        String question=sb.getSecuirtyQuestion(username);
        securityQuestionText.setText(question);
    }

    public void backToLogin() throws IOException {
        // function to handle when the backToLogin button has been pressed
        // aborts forgot password process
        Parent part = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginScreen.fxml")));
        Stage stage1 = new Stage();
        Scene scene = new Scene(part);
        stage1.setScene(scene);
        stage1.show();
        Stage stage = (Stage)backToLoginLink.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void confirmAnswer(ActionEvent event) throws IOException {
        // determine whether the inputted security answer matches the security answer stored in the database
        String answer=securityAnswerTextField.getText();
        serverBridge sb=new serverBridge();
        String serverAnswer=sb.getSecuirtyAnswer(username);

        if(answer.equals(serverAnswer)){
            Parent part = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("setNewPasswordScene.fxml")));
            Stage stage1 = new Stage();
            Scene scene = new Scene(part);
            stage1.setScene(scene);
            stage1.show();
            Stage stage = (Stage)backToLoginLink.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
        else{
            Alert a=new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("ANSWER MISMATCH");
            a.setContentText("Wrong Answer entered, please try again!");
            a.show();
        }
    }
}

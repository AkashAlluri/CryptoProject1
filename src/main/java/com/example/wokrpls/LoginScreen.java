package com.example.wokrpls;
// LoginScreen class

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class LoginScreen {
    @FXML
    Button login;
    @FXML
    TextField IdField;
    @FXML
    PasswordField passwordField;
    serverBridge sb=new serverBridge();

    public void checkLogin(ActionEvent event) throws IOException {
        // function to simulate user authentication
        // uses the checkLogin function in serverbridge to check to make sure that the
        // inputted username and password are a match in the database
        // if successful, the user is logged into the system.
        Stage stage = (Stage) login.getScene().getWindow();
        System.out.println("Passing: "+ IdField.getText() +"\n "+passwordField.getText());
        if(sb.checkLogin(IdField.getText(), passwordField.getText())) {
            saveUserName(IdField.getText());
            Parent part = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
            Stage stage1 = new Stage();
            Scene scene = new Scene(part);
            stage1.setScene(scene);
            stage1.show();
        }
        else{
            System.out.println("USER NOT FOUND!");
        }
        //stage.close();
    }
@FXML
Label logo;

    @FXML
    void initialize() {
        // initializes the javafx view
        ImageView img = new ImageView("esoflogo.png");
        img.setFitHeight(100);
        img.setFitWidth(100);
        logo.setGraphic(img);
    }

    void saveUserName(String name){
        // save the username to a file
        try {
            File myObj = new File("userName.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
                PrintWriter writer = new PrintWriter("userName.txt");
                writer.print("");
                writer.close();
            }
            FileWriter myWriter = new FileWriter("userName.txt");
            myWriter.write(name);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

@FXML Button createAccount;
    public void createAccount(ActionEvent event) throws IOException {
        // create account function
        // invoked when the createaccount button is pressed
        // brings the user to the createAccount scene
        Parent part = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createAccount.fxml")));
        Stage stage1 = new Stage();
        Scene scene = new Scene(part);
        stage1.setScene(scene);
        stage1.show();
        Stage stage = (Stage) createAccount.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
@FXML Hyperlink forgotPassword;
    // forgot password function
    // invoked when the forgotPassword hyperlink is pressed
    public void forgotPassword(ActionEvent event) throws IOException {
        Parent part = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("forgotPasswordUsernameScene.fxml")));
        Stage stage1 = new Stage();
        Scene scene = new Scene(part);
        stage1.setScene(scene);
        stage1.show();
        Stage stage = (Stage) forgotPassword.getScene().getWindow();
        // do what you have to do
        stage.close();

    }

}

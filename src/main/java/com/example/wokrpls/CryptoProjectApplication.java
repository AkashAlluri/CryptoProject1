package com.example.wokrpls;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class CryptoProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(5);
        FXMLLoader fxmlLoader = new FXMLLoader(CryptoProjectApplication.class.getResource("loginScreen.fxml"));
        System.out.println(5);
       // Scene scene = new Scene(fxmlLoader.load(), 900, 650);

        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.BLACK);
        stage.setTitle("SOFT ENG PROJ");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args){
        launch();
    }
}

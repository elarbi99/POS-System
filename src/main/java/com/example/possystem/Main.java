package com.example.possystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("POS-Login.fxml")));
            Scene scene1 = new Scene(root);
            stage.setTitle("Deve's POS Login");
            stage.getIcons().add(new Image("/logo1 tbg.png"));
            stage.setResizable(false);
            stage.setScene(scene1);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
package com.example.possystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.css.CssParser;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;
    APICommunication apiCommunication=new APICommunication();

    // Logs out of POS system and switches scene to login
    public void logout(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public ImageView freshPasty;
    Image fresh = new Image("PastyLogo.png");

    public void mouseClickedFresh(MouseEvent event) throws IOException{

        freshPasty.setImage(fresh);
        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public ImageView frozenPasty;
    Image frozen = new Image("PastyLogo.png");

    public void mouseClickedFrozen(MouseEvent event) throws IOException{

        frozenPasty.setImage(frozen);
        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public ImageView refrigeratedPasty;
    Image refrigerated = new Image("PastyLogo.png");

    public void mouseClickedRefrigerated(MouseEvent event) throws IOException{

        refrigeratedPasty.setImage(refrigerated);
        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public ImageView otherPasty;
    Image other = new Image("PastyLogo.png");

    public void mouseClickedOther(MouseEvent event) throws IOException{

        otherPasty.setImage(other);
        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public ImageView GroceryPasty;
    Image grocery = new Image("PastyLogo.png");

    public void mouseClickedGrocery(MouseEvent event) throws IOException{

        GroceryPasty.setImage(grocery);
        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            apiCommunication.displayProducts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

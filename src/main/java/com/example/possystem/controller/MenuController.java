package com.example.possystem.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MenuController implements Initializable
{
    private Parent root;
    private Stage stage;
    private Scene scene;
    private static List<String> lst= new ArrayList();
    private ListTest listTest=new ListTest();
    @FXML
    Label lblPrice1;
    @FXML
    Label lblPrice2;
    @FXML
    Label lblPrice3;
    @FXML
    Label lblPrice4;
    @FXML
    Label lblPrice5;
    @FXML
    Label lblPrice6;
    @FXML
    Label lblPrice7;
    @FXML
    Label lblItem1;
    @FXML
    Label lblItem2;
    @FXML
    Label lblItem3;
    @FXML
    Label lblItem4;
    @FXML
    Label lblItem5;
    @FXML
    Label lblItem6;
    @FXML
    Label lblItem7;
    @FXML
    Label lblAdded1;
    @FXML
    Label lblAdded2;
    @FXML
    Label lblAdded3;
    @FXML
    Label lblAdded4;
    @FXML
    Label lblAdded5;
    @FXML
    Label lblAdded6;
    @FXML
    Label lblAdded7;


    APICommunication apiCommunication=new APICommunication();

    // Switches scene to check out scene
    public void checkoutScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Checkout.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(lst);
    }

    // LINES 88 - 200 handle adding items to the cart
    public void addItem(ActionEvent event)
    {
        listTest.setItem(apiCommunication.getItemList().get(0));
        listTest.setPrice(Double.parseDouble(String.valueOf(apiCommunication.getPriceList().get(0))));
        listTest.setAddProdId(apiCommunication.getProductIdList().get(0));
        listTest.setProdIdAddList();
        listTest.setItemList();
        listTest.setPriceLst();
        listTest.setLst(lst);

        // Show added when button is clicked
        lblAdded1.setText("Added");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> lblAdded1.setText(null));
        pause.play();
    }

    public void addItem2(ActionEvent event)
    {
        listTest.setItem(apiCommunication.getItemList().get(1));
        listTest.setPrice(Double.parseDouble(String.valueOf(apiCommunication.getPriceList().get(1))));
        listTest.setAddProdId(apiCommunication.getProductIdList().get(1));
        listTest.setProdIdAddList();
        listTest.setItemList();
        listTest.setPriceLst();
        listTest.setLst(lst);

        // Show added when button is clicked
        lblAdded2.setText("Added");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> lblAdded2.setText(null));
        pause.play();
    }

    public void addItem3(ActionEvent event) {
        listTest.setItem(apiCommunication.getItemList().get(2));
        listTest.setPrice(Double.parseDouble(String.valueOf(apiCommunication.getPriceList().get(2))));
        listTest.setAddProdId(apiCommunication.getProductIdList().get(2));
        listTest.setProdIdAddList();
        listTest.setItemList();
        listTest.setPriceLst();
        listTest.setLst(lst);

        // Show added when button is clicked
        lblAdded3.setText("Added");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> lblAdded3.setText(null));
        pause.play();
    }

    public void addItem4(ActionEvent event)
    {
        listTest.setItem(apiCommunication.getItemList().get(3));
        listTest.setPrice(Double.parseDouble(String.valueOf(apiCommunication.getPriceList().get(3))));
        listTest.setAddProdId(apiCommunication.getProductIdList().get(3));
        listTest.setProdIdAddList();
        listTest.setItemList();
        listTest.setPriceLst();
        listTest.setLst(lst);

        // Show added when button is clicked
        lblAdded4.setText("Added");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> lblAdded4.setText(null));
        pause.play();
    }

    public void addItem5(ActionEvent event) {
        listTest.setItem(apiCommunication.getItemList().get(4));
        listTest.setPrice(Double.parseDouble(String.valueOf(apiCommunication.getPriceList().get(4))));
        listTest.setAddProdId(apiCommunication.getProductIdList().get(4));
        listTest.setProdIdAddList();
        listTest.setItemList();
        listTest.setPriceLst();
        listTest.setLst(lst);

        // Show added when button is clicked
        lblAdded5.setText("Added");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> lblAdded5.setText(null));
        pause.play();
    }

    public void addItem6(ActionEvent event) {
        listTest.setItem(apiCommunication.getItemList().get(5));
        listTest.setPrice(Double.parseDouble(String.valueOf(apiCommunication.getPriceList().get(5))));
        listTest.setAddProdId(apiCommunication.getProductIdList().get(5));
        listTest.setProdIdAddList();
        listTest.setItemList();
        listTest.setPriceLst();
        listTest.setLst(lst);

        // Show added when button is clicked
        lblAdded6.setText("Added");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> lblAdded6.setText(null));
        pause.play();
    }

    public void addItem7(ActionEvent event) {
        listTest.setItem(apiCommunication.getItemList().get(6));
        listTest.setPrice(Double.parseDouble(String.valueOf(apiCommunication.getPriceList().get(6))));
        listTest.setAddProdId(apiCommunication.getProductIdList().get(6));
        listTest.setProdIdAddList();
        listTest.setItemList();
        listTest.setPriceLst();
        listTest.setLst(lst);

        // Show added when button is clicked
        lblAdded7.setText("Added");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> lblAdded7.setText(null));
        pause.play();
    }

    // Switches scene to login and clears list changes
    public void loginScene(ActionEvent event) throws IOException {
        listTest.getPriceLst().clear();
        listTest.getItemList().clear();
        listTest.getPriceTest().clear();
        listTest.getPriceTestList().clear();
        listTest.getItemsQuantitys().clear();
        listTest.getItemPrices().clear();
        listTest.getNewProdId().clear();
        listTest.getResult().clear();
        listTest.getLst().clear();
        listTest.getQuantitySize().clear();
        listTest.getProdIdAddList().clear();
        listTest.getNewList().clear();
        listTest.getNewItemList().clear();
        listTest.getNewProdId().clear();
        listTest.getFreqMap3().clear();

        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Switches scene to category menu
    public void CategoryMenu(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("CategoryMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Formats the money values to two decimals
    public String twoDecimals(double a)
    {
        String numStr= String.valueOf(a);
        String[] test=numStr.split(Pattern.quote("."));
        if(test[1].length()<2)
        {
            numStr+="0";
        }
        return numStr;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            apiCommunication.displayProducts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(twoDecimals(apiCommunication.getPriceList().get(0)));
        lblPrice1.setText("$"+twoDecimals(apiCommunication.getPriceList().get(0)));
        lblPrice2.setText("$"+twoDecimals(apiCommunication.getPriceList().get(1)));
        lblPrice3.setText("$"+twoDecimals(apiCommunication.getPriceList().get(2)));
        lblPrice4.setText("$"+twoDecimals(apiCommunication.getPriceList().get(3)));
        lblPrice5.setText("$"+twoDecimals(apiCommunication.getPriceList().get(4)));
        lblPrice6.setText("$"+twoDecimals(apiCommunication.getPriceList().get(5)));
        lblPrice7.setText("$"+twoDecimals(apiCommunication.getPriceList().get(6)));
        lblItem1.setText(apiCommunication.getItemList().get(0));
        lblItem2.setText(apiCommunication.getItemList().get(1));
        lblItem3.setText(apiCommunication.getItemList().get(2));
        lblItem4.setText(apiCommunication.getItemList().get(3));
        lblItem5.setText(apiCommunication.getItemList().get(4));
        lblItem6.setText(apiCommunication.getItemList().get(5));
        lblItem7.setText(apiCommunication.getItemList().get(6));
    }

} // end class

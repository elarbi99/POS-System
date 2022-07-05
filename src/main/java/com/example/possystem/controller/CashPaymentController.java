package com.example.possystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class CashPaymentController implements Initializable {
    ListTest listTest=new ListTest();
    private Parent root;
    private Stage stage;
    private Scene scene;
    private static double owed;
    private static double payAmount;
    private APICommunication apiCommunication=new APICommunication();
    private static double payAmountTotal;
    private static List<Double> amountList=new ArrayList<>();
    @FXML
    TextField txtOwed;
    @FXML
    TextField txtAmount;
    @FXML
    Label lblProcessMessage;

    public void checkout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Checkout.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void processPayment(ActionEvent event) throws IOException, InterruptedException {

        owed= listTest.getPaySceneGrandTotal()-payAmountTotal;
        payAmount= Double.parseDouble(txtAmount.getText());
        owed=owed-payAmount;
        owed =  Math.round(owed * 100.0) / 100.0;
        amountList.add(payAmount);
        payAmountTotal=0;
        for(double sum:amountList)
        {
            payAmountTotal+=sum ;
        }
        System.out.println(owed);
        txtOwed.setText("$"+twoDecimals(owed));
        listTest.setPaySceneGrandTotal(owed);
        txtAmount.clear();

        if(owed<=0) {
            System.out.println("payment complete");
                lblProcessMessage.setTextFill(Color.GREEN);
                lblProcessMessage.setVisible(true);
                lblProcessMessage.setText("Payment Complete");
            listTest.setOwed(String.valueOf(owed));
            apiCommunication.addOrder();
                listTest.getPriceLst().clear();
                listTest.getItemList().clear();
                listTest.getPriceTest().clear();
                listTest.getPriceTestList().clear();
                listTest.getItemsQuantitys().clear();
                listTest.getItemPrices().clear();
                listTest.getNewProdId().clear();
                listTest.getResult().clear();
                listTest.getLst().clear();
                listTest.getProdIdAddList().clear();
                listTest.getQuantitySize().clear();
                listTest.getNewList().clear();
                listTest.getNewItemList().clear();
                listTest.getNewProdId().clear();
                listTest.getFreqMap3().clear();
                listTest.setPaySceneGrandTotal(0);
                payAmountTotal=0;
                txtOwed.clear();
                root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Menu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        } else {
            lblProcessMessage.setTextFill(Color.RED);
            lblProcessMessage.setVisible(true);
            lblProcessMessage.setText("Payment Incomplete");
        }
    }

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
        System.out.println(payAmountTotal);
        txtOwed.setText("$"+(listTest.getPaySceneGrandTotal()-payAmountTotal));
    }
}

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class CheckoutController implements Initializable
{
    private Parent root;
    private Stage stage;
    private Scene scene;
    private static double discountPrice=0;
    private static double discount=0;
    private static List<Double>test=new ArrayList<>();

    @FXML
    private ListView lstItems;
    @FXML
    private TextField txtSubtotal;
    @FXML
    private TextField txtTax;
    @FXML
    private TextField txtGrand;
    @FXML
    private TextField txtQuantity;
    @FXML
    private Label lblError;
    @FXML
    private Label lblPayBtnMessage;
    @FXML
    RadioButton rdCash;
    @FXML
    RadioButton rd10Off;
    @FXML
    RadioButton rd20Off;
    @FXML
    RadioButton rd15Off;
    @FXML
    RadioButton rd25Off;
    @FXML
    RadioButton rd30Off;
    @FXML
    RadioButton rd40Off;
    @FXML
    RadioButton rd50Off;

    APICommunication apiCommunication=new APICommunication();
    private static List<String> lst = new ArrayList();
    ListTest listTest=new ListTest();

    public static double getDiscount() {
        return discount;
    }

    public static void setDiscount(double discount) {
        CheckoutController.discount = discount;
    }

    public void mainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        test.clear();
        lstItems.getItems().removeAll();
    }

    public void logout(ActionEvent event) throws IOException {
        listTest.getPriceLst().clear();
        listTest.getItemList().clear();
        listTest.getPriceTest().clear();
        listTest.getPriceTestList().clear();
        listTest.getItemsQuantitys().clear();
        listTest.getItemPrices().clear();
        listTest.getQuantitySize().clear();
        listTest.getNewProdId().clear();
        listTest.getResult().clear();
        listTest.getLst().clear();
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
        test.clear();
    }

    // Handles removal of items in checkout
    public void remove(ActionEvent event)
    {
        try {

            listTest.getLst().remove(lstItems.getSelectionModel().getSelectedIndex());
            listTest.getPriceLst().remove(lstItems.getSelectionModel().getSelectedIndex());
            listTest.getItemList().remove(lstItems.getSelectionModel().getSelectedIndex());;
            lstItems.getItems().remove( lstItems.getSelectionModel().getSelectedIndex());
            System.out.println(listTest.getPriceLst());
            listTest.getItemPrices().clear();
            listTest.getItemsQuantitys().clear();
            listTest.getPriceTestList().clear();
            listTest.getQuantitySize().clear();
            listTest.getResult().clear();
            listTest.getPriceTest().clear();
            listTest.calc();
            txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
            txtTax.setText("$"+ twoDecimals(listTest.getTax()));
            txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
            listTest.detectDuplicates();
            lblError.setText("");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            lblError.setText("An Item must be Selected!");
        }catch (NullPointerException e) {
            System.out.println(e);

        }

    }

    // Lets money values to be two decimals
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
        for(int i=0;i<listTest.getPriceLst().size();i++)
        {
            test.add(listTest.getPriceLst().get(i));
        }
        System.out.println("Hello");
        lstItems.getItems().addAll(listTest.getLst());
        listTest.detectDuplicates();
        listTest.calc();
        txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
        txtTax.setText("$"+ twoDecimals(listTest.getTax()));
        txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
        System.out.println(listTest.getNewItemList());


    }

    public void apply(ActionEvent actionEvent)
    {
        try {

            if(Integer.parseInt(txtQuantity.getText())<1)
            {
                lblError.setText("Quantity can't be less then 1!");
                return;
            }
            double newPrice=0.00;

            newPrice= Double.parseDouble(String.valueOf(test.get(lstItems.getSelectionModel().getSelectedIndex())))*Integer.parseInt(txtQuantity.getText());
            listTest.getPriceLst().set(lstItems.getSelectionModel().getSelectedIndex(),newPrice);
            System.out.println(test);
            listTest.getLst().set(lstItems.getSelectionModel().getSelectedIndex(),txtQuantity.getText()+" "+listTest.getItemList().get(lstItems.getSelectionModel().getSelectedIndex())+" : $"+listTest.getPriceLst().set(lstItems.getSelectionModel().getSelectedIndex(),newPrice));
            listTest.getItemsQuantitys().clear();
            listTest.getResult().clear();
            listTest.getPriceTestList().clear();
            listTest.getPriceTest().clear();
            listTest.getProdIdAddList().clear();
            listTest.getFreqMap3().clear();
            listTest.detectDuplicates();
            listTest.calc();
            lstItems.getItems().set(lstItems.getSelectionModel().getSelectedIndex(),listTest.getLst().get(lstItems.getSelectionModel().getSelectedIndex()));
            txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
            txtTax.setText("$"+ twoDecimals(listTest.getTax()));
            txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
            lblError.setText("");
        } catch (IndexOutOfBoundsException e ) {
            System.out.println(e);
            lblError.setText("");
            lblError.setText("You must Select an Item!");
        }
         catch (NumberFormatException e1 ) {
            System.out.println(e1);
            lblError.setText("");
            lblError.setText("Quantity must be an Integer and cannot be empty!");
        } catch (NullPointerException e) {
            System.out.println(e);

        }

    }

    public void pay(ActionEvent event) throws IOException {
        if(rdCash.isSelected())
        {
            lblPayBtnMessage.setVisible(false);
            root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Cash.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(!rdCash.isSelected()){
            lblPayBtnMessage.setTextFill(Color.RED);
            lblPayBtnMessage.setVisible(true);
            lblPayBtnMessage.setText("Select payment method");
        }
    }

    public void applyDiscount(ActionEvent event)
    {
        if(rd10Off.isSelected())
        {
            discountPrice=0;
            discount=0.1;
            for(double discount: listTest.getPriceLst())
            {
                discountPrice+=discount*0.1;
            }
            discountPrice=Math.round(discountPrice * 100.0) / 100.0;
            listTest.setDiscountPrice(discountPrice);
            listTest.calc();
            txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
            txtTax.setText("$"+ twoDecimals(listTest.getTax()));
            txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
        }
        else if(rd15Off.isSelected())
        {
            discountPrice=0;
            discount=0.15;
            for(double discount: listTest.getPriceLst())
            {
                discountPrice+=discount*0.15;
            }
            listTest.setDiscountPrice(discountPrice);
            listTest.calc();
            txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
            txtTax.setText("$"+ twoDecimals(listTest.getTax()));
            txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
        }
        else if(rd20Off.isSelected())
        {
            discountPrice=0;
            discount=0.2;
            for(double discount: listTest.getPriceLst())
            {
                discountPrice+=discount*0.2;
            }
            listTest.setDiscountPrice(discountPrice);
            listTest.calc();
            txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
            txtTax.setText("$"+ twoDecimals(listTest.getTax()));
            txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
        }
        else if(rd25Off.isSelected())
        {
            discountPrice=0;
            discount=0.25;
            for(double discount: listTest.getPriceLst())
            {
                discountPrice+=discount*0.25;
            }
            listTest.setDiscountPrice(discountPrice);
            listTest.calc();
            txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
            txtTax.setText("$"+ twoDecimals(listTest.getTax()));
            txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
        }
        else if(rd30Off.isSelected())
        {
            discountPrice=0;
            discount=0.3;
            for(double discount: listTest.getPriceLst())
            {
                discountPrice+=discount*0.3;
            }
            listTest.setDiscountPrice(discountPrice);
            listTest.calc();
            txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
            txtTax.setText("$"+ twoDecimals(listTest.getTax()));
            txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
        }
        else if(rd40Off.isSelected())
        {
            discountPrice=0;
            for(double discount: listTest.getPriceLst())
            {
                discountPrice+=discount*0.4;
            }
            listTest.setDiscountPrice(discountPrice);
            listTest.calc();
            txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
            txtTax.setText("$"+ twoDecimals(listTest.getTax()));
            txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
        }
        else if(rd50Off.isSelected())
        {
            discountPrice=0;
            discount=0.5;
            for(double discount: listTest.getPriceLst())
            {
                discountPrice+=discount*0.5;
            }
            listTest.setDiscountPrice(discountPrice);
            listTest.calc();
            txtSubtotal.setText("$"+twoDecimals(listTest.getSubTotal()));
            txtTax.setText("$"+ twoDecimals(listTest.getTax()));
            txtGrand.setText("$"+twoDecimals(listTest.getGrandTotal()));
        }
    }
    public Double getDiscountPrice(){return discountPrice;}
}

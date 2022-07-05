package com.example.possystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LoginController{
    @FXML
    private TextField txtFld_EmployeeID;
    @FXML
    private PasswordField txtFld_Password;
    @FXML
    private Button btn_Login;
    @FXML
    private Label lbl_LoginErrorMessage;

    private Parent root;
    private Stage stage;
    private Scene scene;

    public LoginController() {

    }

    // When login button is clicked make connection to database and log user in
    public void btnLoginOnClick(ActionEvent event) throws IOException {
        System.out.println("Button Clicked");
        APICommunication apiCommunication=new APICommunication();
        try {
            String id= txtFld_EmployeeID.getText();
            String password=txtFld_Password.getText();
            apiCommunication.setId(id);
            apiCommunication.setPassword(password);
            if (!isEmpty(txtFld_EmployeeID, txtFld_Password)) {
                System.out.println("Action needed - username and password is required");
                return;
            }
            apiCommunication.login();
            if (event.getSource() == btn_Login) {
                if (Objects.equals(apiCommunication.getTempMessage(), "login successful")) {
                    menuScene(event);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            if(!Objects.equals(apiCommunication.getTempMessage(), "login successful"))
            {
                showError("Employee ID or Password is Invalid");
            }

        }

    }

    public void menuScene(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("POS-Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private boolean isEmpty(TextField txtFld_EmployeeID, TextField txtFld_Password) {
        if (txtFld_EmployeeID.getText().isEmpty() && txtFld_Password.getText().isEmpty()) {
            showError("Employee ID and Password is empty");
            txtFld_EmployeeID.requestFocus();
            return false;
        }
        else if (txtFld_EmployeeID.getText().isEmpty()) {
            showError("Employee ID is required");
            txtFld_EmployeeID.requestFocus();
            return false;
        }
        else if (txtFld_Password.getText().isEmpty()) {
            showError("Password is required");
            txtFld_Password.requestFocus();
            return false;
        }
        return true;
    }

    private void showError(String errMessage) {
        lbl_LoginErrorMessage.setText("");
        lbl_LoginErrorMessage.setText(errMessage);
        lbl_LoginErrorMessage.setWrapText(true);
    }



}

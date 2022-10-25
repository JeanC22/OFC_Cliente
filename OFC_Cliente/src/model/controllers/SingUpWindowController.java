/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class SingUpWindowController implements Initializable {

    @FXML
    private TextField userNameTxTF;
    @FXML
    private PasswordField passwdTxPF;
    @FXML
    private TextField fullNameTxTF;
    @FXML
    private TextField eMailTxTF;
    @FXML
    private Button signUpBtn;
    @FXML
    private ImageView passwdTT;
    @FXML
    private ImageView eMailTT;
    @FXML
    private ImageView userNameTT;
    @FXML
    private ImageView fullNameTT;
    @FXML
    private Button spBtn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

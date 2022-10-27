/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class SingUpWindowController implements Initializable {

    private static String regex = "^[a-zA-Z1-9]*$";
    private static String regexEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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

        userNameTxTF.setTooltip(new Tooltip("Max characters 15"));
        passwdTxPF.setTooltip(new Tooltip("min 6 and max 12 characters"));
        fullNameTxTF.setTooltip(new Tooltip("Max 40 characters"));
        eMailTxTF.setTooltip(new Tooltip("example@example.com"));

        Tooltip.install(userNameTT, new Tooltip("Max characters 15"));
        Tooltip.install(passwdTT, new Tooltip("min 6 and max 12 characters"));
        Tooltip.install(fullNameTT, new Tooltip("Max 40 characters"));
        Tooltip.install(eMailTT, new Tooltip("example@example.com"));

    }

    @FXML
    private void signUp(ActionEvent event) {
        
        User usu = new User();
        IntefaceFactory fac = new InterfaceFactory();
        DaoInteface dao = fac.getInterface();
        
        
        
        validations();
        
        usu.setLogin(this.userNameTxTF.getText());
        usu.setPassword(this.passwdTxPF.getText());
        usu.setfullName(this.fullNameTxTF.getText());
        usu.setEmail(this.eMailTxTF.getText());
        
        dao.signUp(usu);
        
        
    }

    private void validations() {
        if (this.userNameTxTF.getText().trim().equalsIgnoreCase("")
                || this.passwdTxPF.getText().trim().equalsIgnoreCase("")
                || this.eMailTxTF.getText().trim().equalsIgnoreCase("")
                || this.fullNameTxTF.getText().trim().equalsIgnoreCase("")) {

            errorMessage("Uno de los campos no esta informado");

        } else if (!this.userNameTxTF.getText().matches(regex)
                || this.userNameTxTF.getText().toString().length() > 15) {

            errorMessage("El campo UserName tiene caracteres "
                    + "especiales o te has pasado de el limite de "
                    + "caracteres permitidos(max 15)");

        } else if (this.passwdTxPF.getText().toString().length() < 6
                || this.passwdTxPF.getText().toString().length() > 12) {
            errorMessage("El campo Password tiene que ser de minimo 6 "
                    + "caracteres y maximo de 12 caracteres");
        } else if (!this.fullNameTxTF.getText().matches(regex)
                || this.fullNameTxTF.getText().toString().length() > 40) {

            errorMessage("El campo Fullname tiene "
                    + "caracteres especiales o te has pasado de el limite de "
                    + "caracteres permitidos(max 40)");

        } else if (!this.eMailTxTF.getText().matches(regexEmail)) {

            errorMessage("El campo email notiene el formato adecuado"
                    + "(example@example.com)");
        }
    }

    private void errorMessage(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.OK);
        alert.showAndWait();
    }

}

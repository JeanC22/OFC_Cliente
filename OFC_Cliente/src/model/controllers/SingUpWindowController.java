/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class SingUpWindowController implements Initializable{

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
    
    private Stage stage = new Stage();

    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
         
        
        userNameTxTF.setTooltip(new Tooltip("Max characters 15"));
        passwdTxPF.setTooltip(new Tooltip("min 6 and max 12 characters"));
        fullNameTxTF.setTooltip(new Tooltip("Max 40 characters"));
        eMailTxTF.setTooltip(new Tooltip("example@example.com"));

        Tooltip.install(userNameTT, new Tooltip("Max characters 15"));
        Tooltip.install(passwdTT, new Tooltip("min 6 and max 12 characters"));
        Tooltip.install(fullNameTT, new Tooltip("Max 40 characters"));
        Tooltip.install(eMailTT, new Tooltip("example@example.com"));
        
        signUpBtn.setOnAction(this::signUp);
        backBtn.setOnAction(this::backBtn);
        stage.setOnCloseRequest(this::cerrarVentana);

    }

     
    @FXML
    private void signUp(ActionEvent event) {
        try{
        //User usu = new User();
        //IntefaceFactory fac = new InterfaceFactory();
        //DaoInteface dao = fac.getInterface();
        
        if (this.userNameTxTF.getText().trim().equalsIgnoreCase("")
                || this.passwdTxPF.getText().trim().equalsIgnoreCase("")
                || this.eMailTxTF.getText().trim().equalsIgnoreCase("")
                || this.fullNameTxTF.getText().trim().equalsIgnoreCase("")){
       
            throw new Exception("Uno de los campos no esta informado");
            
        } else if (!this.userNameTxTF.getText().matches(regex)
                || this.userNameTxTF.getText().toString().length() > 15) {

            throw new Exception("El campo UserName tiene caracteres "
                    + "especiales o te has pasado de el limite de "
                    + "caracteres permitidos(max 15)");
            

        } else if (this.passwdTxPF.getText().toString().length() < 6
                || this.passwdTxPF.getText().toString().length() > 12) {
            
            throw new Exception("El campo Password tiene que ser de minimo 6 "
                    + "caracteres y maximo de 12 caracteres");
           
        } else if (!this.fullNameTxTF.getText().matches(regex)
                || this.fullNameTxTF.getText().toString().length() > 40) {

            throw new Exception("El campo Fullname tiene "
                    + "caracteres especiales o te has pasado de el limite de "
                    + "caracteres permitidos(max 40)");
           

        } else if (!this.eMailTxTF.getText().matches(regexEmail)) {

            throw new Exception("El campo email notiene el formato adecuado"
                    + "(example@example.com)");
            
        }else{           
       /* usu.setLogin(this.userNameTxTF.getText());
        usu.setPassword(this.passwdTxPF.getText());
        usu.setfullName(this.fullNameTxTF.getText());
        usu.setEmail(this.eMailTxTF.getText());
        
        dao.signUp(usu);*/
        }
        }catch(Exception e){
             Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
        
    }
    
    @FXML
    private void backBtn(ActionEvent event){
        
        try {
            Stage primaryStage = new Stage();
            
            URL a = getClass().getResource("/model/views/SignInWindow.fxml");
            System.out.println(a);
            FXMLLoader loader = new FXMLLoader(a);
            Parent root = (Parent) loader.load();
            SingInWindowController mainStageController
                    = ((SingInWindowController) loader.getController());
            mainStageController.setStage(primaryStage);
            mainStageController.initStage(root);
            
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.close();
            //inicializar la scena
        } catch (IOException ex) {
            Logger.getLogger(SingUpWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
        
    }

    
    @FXML
    public void cerrarVentana(WindowEvent event){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Quiere salir de la aplicacion?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Platform.exit();
        }else {
            event.consume();
        }
    }
    
}

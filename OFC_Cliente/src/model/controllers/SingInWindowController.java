/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package model.controllers;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author jp22
 */
public class SingInWindowController implements Initializable{
    
    private Stage stage;
    @FXML
    private ImageView imageView;
    @FXML
    private Button singInBtn;
    @FXML
    private TextField userNameTxTF;
     @FXML
    private PasswordField passwdTxTF;
    @FXML
    private ImageView passwrdTT;
    @FXML
    private ImageView usernameTT;
    @FXML
    private Hyperlink signUpLink;
    
 public void setStage(Stage stage) {
        this.stage = stage;
    }
 
    public void initStage(Parent root) {
        //Create a scene associated to the node graph root.
        Scene scene = new Scene(root);
       
        //Associate scene to primaryStage(Window)
        stage.setScene(scene);
        //Set window properties
        stage.setTitle("OFC SING IN");
        stage.setResizable(false);
        stage.setOnShowing(this::windowShowing);
        userNameTxTF.textProperty().addListener((observable) -> this.fieldsLength((ObservableValue) observable));
        passwdTxTF.textProperty().addListener((observable) -> this.fieldsLength((ObservableValue) observable));
        //Show window
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
      private void windowShowing(WindowEvent event){
        userNameTxTF.requestFocus();
        //Tooltip en los campos de usuario, contraseña y el link
        userNameTxTF.setTooltip(new Tooltip("max 15 characters"));
        passwdTxTF.setTooltip(new Tooltip("min 6 max 12 characters"));
        signUpLink.setTooltip(new Tooltip("Click para abrir la ventana de registro"));
      }
    @FXML
    private void signUpWindow(ActionEvent event) {
        try {
            //Crea una escena a partir del Parent
            Parent root = FXMLLoader.load(getClass().getResource("views/SignUpWindow.fxml"));
            //Establece la escena del escenario(Stage) y la muestra
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SingInWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void signIn(ActionEvent event) {
            if (userNameTxTF.getText().isEmpty() || passwdTxTF.getText().isEmpty()) {
                new Alert(AlertType.ERROR, "Todos los campos no estan informados").showAndWait();
                
            }else{
                userNameTxTF.textProperty().addListener((observable) -> this.fieldsLength((ObservableValue) observable));
                passwdTxTF.textProperty().addListener((observable) -> this.fieldsLength((ObservableValue) observable));
            }
        
    }

    private void fieldsLength(ObservableValue observable){
            if (userNameTxTF.getText().length() > 15) {
               new Alert(AlertType.ERROR, "La longitud del campo usuario supera los 15 caracteres").showAndWait();
            }else if(passwdTxTF.getText().length() < 6 || passwdTxTF.getText().length() > 12){
                new Alert(AlertType.ERROR, "La longitud del campo contraseña es minimo de 6 o mayor que 12").showAndWait();
            }  
        
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package model.controllers;


import javafx.event.ActionEvent;
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
public class SingInWindowController{
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
        userNameTxTF.textProperty().addListener((observable) -> this.userNameLength((ObservableValue) observable));
        passwdTxTF.textProperty().addListener((observable) -> this.passwordLength((ObservableValue) observable));
        //Show window
        this.stage.show();
    }
    /**
     * Window event method when start the Sign In Window
     * 
     * @param event The window event
     */
    private void windowShowing(WindowEvent event) {
        userNameTxTF.requestFocus();
        //Tooltip en los campos de usuario, contraseña y el link
        userNameTxTF.setTooltip(new Tooltip("max 15 characters"));
        Tooltip.install(usernameTT, new Tooltip("max 15 characters"));
        passwdTxTF.setTooltip(new Tooltip("min 6 max 12 characters"));
        Tooltip.install(passwrdTT, new Tooltip("min 6 max 12 characters"));
        signUpLink.setTooltip(new Tooltip("Click para abrir la ventana de registro"));
    }
    
    /**
     * Action event for Hyper link to open the window Sign Up.
     * @param event Action event
     */
    @FXML
    private void singUpWindow(ActionEvent event) {
        try {
            //Crea una escena a partir del Parent
            Parent root = FXMLLoader.load(getClass().getResource("/model/views/SignUpWindow.fxml"));
            //Establece la escena del escenario(Stage) y la muestra
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("OFC SIGN UP");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SingInWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * User name validation length. If field is too long than 15 characters, show error message.
     * Also checks that it does not contain special characters
     * @param observable The value being observed.
     */
    private void userNameLength(ObservableValue observable) {
        String regex = "^[a-zA-Z1-9]*$";
        if (userNameTxTF.getText().length() > 15) {
            new Alert(AlertType.ERROR, "La longitud del campo usuario supera los 15 caracteres").showAndWait();
        }else if(!userNameTxTF.getText().matches(regex)){
             new Alert(AlertType.ERROR, "El campo contiene caracteres especiales").showAndWait();
        }

    }
    /**
     * Password validation length. If field is less than 6 or long than 12 characters
     * @param observable The value being observed.
     */
    private void passwordLength(ObservableValue observable) {
        if (passwdTxTF.getText().length() == 6) {
            new Alert(AlertType.ERROR, "La longitud minima del campo contraseña son 6 caracteres").showAndWait();
        }else if(passwdTxTF.getText().length() > 12){
            new Alert(AlertType.ERROR, "La longitud del campo contraseña supera el maximo de 12 caracteres").showAndWait();
        }

    }
    /**
     * Action event for button SignIn. If fields user name and password is filled, show error message.
     * Otherwise, open the window Loged.
     * @param event 
     */
    @FXML
    private void signIn(ActionEvent event) {
        if (userNameTxTF.getText().isEmpty() || passwdTxTF.getText().isEmpty()) {
            new Alert(AlertType.ERROR, "Todos los campos no estan informados").showAndWait();

        } else {
                try {
                    Stage loginStage = new Stage();
                    
                    URL viewLink = getClass().getResource("/model/views/LogedWindow.fxml");
                    
                    FXMLLoader loader = new FXMLLoader(viewLink);
                    Parent root = (Parent) loader.load();
                    LogedWindowController logedStageController
                            = ((LogedWindowController) loader.getController());
                    logedStageController.setStage(loginStage);
                    logedStageController.initStage(root);
                    try {
                        //close the actually View
                        this.stage.close();
                    } catch (Exception e) {

                    }
                } catch (IOException ex) {
                    Logger.getLogger(LogedWindowController.class.getName())
                            .log(Level.SEVERE, null, ex);
                }

            }

        }
    }





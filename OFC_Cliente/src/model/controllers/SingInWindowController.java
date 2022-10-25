/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package model.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
        //Show window
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }


}

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class LogedWindowController implements Initializable {
    private Stage stage;
    @FXML
    private Label profileLabel;
    @FXML
    private Button welcomeString;
    @FXML
    private Label logoutLabel;
    @FXML
    private ImageView profileImage;
    @FXML
    private ImageView logoutImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setStage(Stage stage) {
        
          this.stage=stage;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("OFC SIGN IN");
        stage.show();
    }

}

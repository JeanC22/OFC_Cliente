/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class LogedWindowController implements Initializable {

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
    
}

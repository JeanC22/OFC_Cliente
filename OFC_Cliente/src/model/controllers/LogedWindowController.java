/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Jp
 */
public class LogedWindowController {

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

    public void setStage(Stage stage) {

        this.stage = stage;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("OFC SIGN IN");
        stage.show();
    }

    /**
     * FXML Controller class
     * @author Jp 
     * this method will be close this view and open the SingInWindow
     */
    @FXML
    public void logout() {
        //We will confir if the user rly want to logout 
        Integer reply = JOptionPane.showConfirmDialog(null,
                "Confirmar para salir", "confirmar",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                //Gonna initialition a new Stage
                Stage mainStage = new Stage();
                // we gonna create a URL for get the fxml view
                URL viewLink = getClass().getResource(
                        "/model/views/SignInWindow.fxml");
                // initialition loader
                FXMLLoader loader = new FXMLLoader(viewLink);
                //make the root with the loader
                Parent root = (Parent) loader.load();
                //Get the controller
                SingInWindowController mainStageController
                        = ((SingInWindowController) loader.getController());
                //set the stage
                mainStageController.setStage(mainStage);
                //start the stage
                mainStageController.initStage(root);
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

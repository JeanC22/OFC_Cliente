/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import model.controllers.SingInWindowController;

/**
 *  
 * @author Jp
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
             //Create a URL for get the fxml view
            URL viewLink = getClass().getResource("views/SignInWindow.fxml");
            //Initialition loader
            FXMLLoader loader = new FXMLLoader(viewLink);
            //Make the root with the loader
            Parent root = (Parent) loader.load();
            //Get the controller
            SingInWindowController mainStageController
                    = ((SingInWindowController) loader.getController());
             //Set the stage
            mainStageController.setStage(primaryStage);
            //Start the stage
            mainStageController.initStage(root);
   
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

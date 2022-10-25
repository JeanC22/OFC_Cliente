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
 * @author 2dam
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
           
            URL viewLink = getClass().getResource("views/SignInWindow.fxml");
            FXMLLoader loader = new FXMLLoader(viewLink);
            Parent root = (Parent) loader.load();
            SingInWindowController mainStageController
                    = ((SingInWindowController) loader.getController());
            mainStageController.setStage(primaryStage);
            mainStageController.initStage(root);
            
             /*
             Stage loginStage = new Stage();
             URL viewLink = getClass().getResource("views/LogedWindow.fxml");

            FXMLLoader loader = new FXMLLoader(viewLink);
            Parent root = (Parent) loader.load();
            LogedWindowController logedStageController
                    = ((LogedWindowController) loader.getController());
            logedStageController.setStage(loginStage);
            logedStageController.initStage(root);*/
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

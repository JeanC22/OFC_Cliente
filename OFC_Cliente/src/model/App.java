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
import model.controllers.SignInWindowController;

/**
 *
 * @author Jp
 */
public class App extends Application {

    private static final Logger LOGGER = Logger.getLogger("model.App");

    /**
     * This method will be start the Window
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("Starting SingInWindow");
        try {
            //link to get the FXML file
            URL viewLink = getClass().getResource("views/SignInWindow.fxml");
            //initialization the loader witk the FXML file
            FXMLLoader loader = new FXMLLoader(viewLink);
            //initialization the root (Parent) with the FXML Loader.load
            Parent root = (Parent) loader.load();
            //initialization the singInController
            SignInWindowController mainStageController
                    = ((SignInWindowController) loader.getController());
            //set the Stage to the controll
            mainStageController.setStage(primaryStage);
            //Start the Stage
            mainStageController.initStage(root);
            LOGGER.info("Started SingInWindow");

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

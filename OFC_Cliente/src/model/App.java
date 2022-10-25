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

            /*            Parent root = FXMLLoader.load(
                    getClass().getResource("views/SignInWindow.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.show();*/
            URL a = getClass().getResource("views/SignInWindow.fxml");
            System.out.println(a);
            FXMLLoader loader = new FXMLLoader(a);
            Parent root = (Parent) loader.load();
            SingInWindowController mainStageController
                    = ((SingInWindowController) loader.getController());
            mainStageController.setStage(primaryStage);
            mainStageController.initStage(root);
            //inicializar la scena
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

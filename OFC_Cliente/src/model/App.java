/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controllers.LogedWindowController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("view/LogedWindow.fxml"));
            System.out.println("aka");
            Parent root = (Parent)loader.load();
            //Conceguir el controlador grafico y setear la referencia para la stage
            
            LogedWindowController MainStagecontroller =
                    ((LogedWindowController)loader.getController());
            //Set la referencia al stage
            MainStagecontroller.setStage(primaryStage);
            //inicializar la scena
           MainStagecontroller.initStage(root);
           
                
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

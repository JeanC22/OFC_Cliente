/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import userPackage.User;

/**
 * FXML Controller class
 *
 * @author Jp
 */
public class LogedWindowController {

    private User user;
    private Stage stage;
    @FXML
    private Label profileLabel;
    @FXML
    private Label welcomeString;
    @FXML
    private Label logoutLabel;
    @FXML
    private ImageView profileImage;
    @FXML
    private ImageView logoutImage;
    private static final Logger LOGGER = Logger.getLogger("model.controllers.LogedWindowController");

    /**
     * setStage
     *
     * @param stage
     */
    public void setStage(Stage stage) {

        this.stage = stage;
    }

    /**
     * this Method will start the stage
     *
     * @author Jp
     * @param root
     */
    public void initStage(Parent root) {
        LOGGER.info("Starting Stage");
        //init the scene with the root you got from singInController
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("OFC SIGN IN");
        //get the user from the singInController and init on the local User

        stage.setOnCloseRequest(this::cerrarVentana);
        logoutImage.setOnMouseClicked(this::logout);
        logoutLabel.setOnMouseClicked(this::logout);
        setWelcomeMessage(user.getUsername());
        stage.show();
        LOGGER.info("Stage Started");
    }

    /**
     * this Method will be close LogedWindow and start the SingInWindow
     *
     * @author Jp
     */
    @FXML
    public void logout(MouseEvent event) {
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
            //close the actually View
            this.stage.close();
        } catch (IOException ex) {
            Logger.getLogger(LogedWindowController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This Method confirm if the user want to close the window
     *
     * @author Iker
     * @param event
     */
    @FXML
    public void cerrarVentana(WindowEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Quiere salir de la aplicacion?");
        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/model/views/dialog.css").toExternalForm());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            event.consume();
        }
    }

    public User getUser(User userLogin) {
        this.user = userLogin;
        return user;
    }

    public void setWelcomeMessage(String name) {
        welcomeString.setText(" Welcome    " + name);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package model.controllers;

import exceptions.LoginPasswordException;
import exceptions.LoginUsernameAndPasswordException;
import exceptions.LoginUsernameException;
import exceptions.ServerConnectionException;
import interfacePackage.mainInterface;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.IntefaceFactory;
import userPackage.User;

/**
 * FXML Controller class
 *
 * @author Elias
 */
public class SingInWindowController {

    private static String regex = "^[a-zA-Z1-9]*$";
    private Stage stage;
    @FXML
    private ImageView imageView;
    @FXML
    private Button singInBtn;
    @FXML
    private TextField userNameTxTF;
    @FXML
    private PasswordField passwdTxPF;
    @FXML
    private ImageView passwrdTT;
    @FXML
    private ImageView usernameTT;
    @FXML
    private Hyperlink signUpLink;
    private User userLoged;

    private static final Logger LOGGER = Logger.getLogger("model.controllers.SingInWindowController");

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
        LOGGER.info("Incializando la ventana de Sign In");
        //Create a scene associated to the node graph root.
        Scene scene = new Scene(root);

        //Associate scene to primaryStage(Window)
        stage.setScene(scene);
        //Set window properties
        stage.setTitle("OFC SING IN");
        stage.setResizable(false);
        stage.setOnShowing(this::windowShowing);
        singInBtn.setOnAction(this::signIn);
        stage.setOnCloseRequest(this::cerrarVentana);
        //Show window
        stage.show();

    }

    /**
     * Window event method when start the Sign In Window
     *
     * @author Elias
     * @param event The window event
     */
    private void windowShowing(WindowEvent event) {
        userNameTxTF.requestFocus();
        //Tooltip en los campos de usuario, contraseña y el link
        userNameTxTF.setTooltip(new Tooltip("max 15 characters"));
        Tooltip.install(usernameTT, new Tooltip("max 15 characters"));
        passwdTxPF.setTooltip(new Tooltip("min 6 max 12 characters"));
        Tooltip.install(passwrdTT, new Tooltip("min 6 max 12 characters"));
        signUpLink.setTooltip(new Tooltip("Click para abrir la ventana de registro"));
    }

    /**
     * Action event for Hyper link to open the window Sign Up.
     *
     * @author Jp
     * @param event Action event
     */
    @FXML
    private void singUpWindow(ActionEvent event) {
        try {
            Stage mainStage = new Stage();
            URL viewLink = getClass().getResource(
                    "/model/views/SignUpWindow.fxml");
            // initialition loader
            FXMLLoader loader = new FXMLLoader(viewLink);
            //make the root with the loader
            Parent root = (Parent) loader.load();
            //Get the controller
            SingUpWindowController mainStageController
                    = ((SingUpWindowController) loader.getController());
            //set the stage
            mainStageController.setStage(mainStage);
            //start the stage
            mainStageController.initStage(root);

            this.stage.close();
        } catch (IOException ex) {
            Logger.getLogger(SingInWindowController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Action event for button SignIn. If fields user name and password is
     * filled, show error message. Otherwise, open the window Loged.
     *
     * @collaborators Elias, Iker and Jp
     * @param event
     */
    private void signIn(ActionEvent event) {
        try {
            if (userNameTxTF.getText().isEmpty() || passwdTxPF.getText().isEmpty()) {
                throw new Exception("Todos los campos no estan informados");
            }

            if (userNameTxTF.getText().length() > 15) {
                throw new Exception("La longitud del "
                        + "campo user supera los 15 caracteres");
            }

            if (!userNameTxTF.getText().matches(regex)) {
                throw new Exception(
                        "El campo contiene"
                        + " caracteres especiales");
            }

            if (passwdTxPF.getText().length() < 6 || passwdTxPF.getText().length() > 12) {
                throw new Exception(
                        "El campo password es minimo "
                        + "de 6 caracteres o maximo de 12");
            }

            IntefaceFactory facInterface = new IntefaceFactory();
            mainInterface interFace = (mainInterface) facInterface.getInterface();
            User user = new User();

            user.setUsername(userNameTxTF.getText());
            user.setPassword(passwdTxPF.getText());
            try {
                this.userLoged = interFace.signIn(user);

                Stage loginStage = new Stage();

                URL viewLink = getClass().getResource("/model/views/LogedWindow.fxml");

                FXMLLoader loader = new FXMLLoader(viewLink);
                Parent root = (Parent) loader.load();
                LogedWindowController logedStageController
                        = ((LogedWindowController) loader.getController());
                //send te user
                logedStageController.getUser(userLoged);
                logedStageController.setStage(loginStage);
                logedStageController.initStage(root);
                //close the actually View
                this.stage.close();

            } catch (ServerConnectionException | LoginUsernameException
                    | LoginPasswordException | LoginUsernameAndPasswordException ex) {
                throw new Exception(ex.getMessage());

            }
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.getDialogPane().getStylesheets().add(
                    getClass().getResource("/model/views/dialog.css").toExternalForm());

            alert.showAndWait();
            Logger.getLogger(SingInWindowController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @author Elias
     * @param event
     */
    @FXML
    public void cerrarVentana(WindowEvent event) {
        Logger.getLogger(SingInWindowController.class.getName())
                .log(Level.SEVERE, null, "se ha cerrado la ventana");

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

}

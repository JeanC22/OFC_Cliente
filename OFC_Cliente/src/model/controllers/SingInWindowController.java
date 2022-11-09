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
import javafx.scene.layout.Pane;
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
    @FXML
    private Pane OFC_SIGN_IN;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * This method will start the window
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
        //title of the window: OFC SIGN IN.
        stage.setTitle("OFC SING IN");
        stage.setResizable(false);
        stage.setOnShowing(this::windowShowing);
        singInBtn.setOnAction(this::signIn);
        signUpLink.setOnAction(this::signUpWindow);
        stage.setOnCloseRequest(this::cerrarVentana);
        //Show window
        stage.show();
        LOGGER.info("La ventana de Sign In esta iniciada");

    }

    /**
     * This method will be set all the Tooltips
     *
     * @author Elias
     * @param event
     */
    private void windowShowing(WindowEvent event) {
        LOGGER.info("Method windowShowing is starting ");

        //The field (userNameTxTF) has the focus
        userNameTxTF.requestFocus();
        //The field (userNameTxTF) will be shown with a ToolTip the message “max 15 characters”. 
        userNameTxTF.setTooltip(new Tooltip("max 15 characters"));
        //The field (usernameTT) will be shown with a ToolTip the message “max 15 characters”. 
        Tooltip.install(usernameTT, new Tooltip("max 15 characters"));
        //The field (passwdTxPF) will be shown with a ToolTip the message “min 6 max 12 characters”
        passwdTxPF.setTooltip(new Tooltip("min 6 max 12 characters"));
        //The field (passwrdTT) will be shown with a ToolTip the message “min 6 max 12 characters”
        Tooltip.install(passwrdTT, new Tooltip("min 6 max 12 characters"));
        //The HyperLink (signUpLink) will be shown with a ToolTip the message “Click para abrir la ventana de registro”. 
        signUpLink.setTooltip(new Tooltip("Click para abrir la ventana de registro"));
        LOGGER.info("Method windowShowing is finished");

    }

    /**
     * This method will be start the SignUpWindow and close the SignInWindow
     *
     *
     * @author Jp
     * @param event
     */
    @FXML
    private void signUpWindow(ActionEvent event) {
        LOGGER.info("Method signUpWindow is starting");

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
            LOGGER.info("Method signUpWindow is finished");

        } catch (IOException ex) {
            Logger.getLogger(SingInWindowController.class.getName())
                    .log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    /**
     * This method will be Sign In if the User exist and all fields are correct
     * will be start the LogedWindow.
     *
     * @author Elias, Iker and Jp
     * @param event
     */
    private void signIn(ActionEvent event) {
        LOGGER.info("Method signIn is starting");

        try {
            //Validar que el campo userName (userNameTxTF) y el campo password (passwdTxTF) están informados:
            if (userNameTxTF.getText().isEmpty() || passwdTxPF.getText().isEmpty()) {
                throw new Exception("Todos los campos no estan informados");
            }
            //Validar la longitud userName (userNameTxTF) máximo 15 caracteres:

            if (userNameTxTF.getText().length() > 15) {
                throw new Exception("La longitud del "
                        + "campo user supera los 15 caracteres");
            }
            //Validar que el userName (userNameTxTF) no cuenta con caracteres especiales:
            if (!userNameTxTF.getText().matches(regex)) {
                throw new Exception(
                        "El campo contiene"
                        + " caracteres especiales");
            }
            //Validar que la longitud del password (passwdTxTF) es mínimo de 6 caracteres
            //Validar que la longitud del password(passwdTxTF) es máximo 12 caracteres

            if (passwdTxPF.getText().length() < 6 || passwdTxPF.getText().length() > 12) {
                throw new Exception(
                        "El campo password es minimo "
                        + "de 6 caracteres o maximo de 12");
            }
            //Si todos los campos han sido validados correctamente se 
            //recogen los datos y se crea un objeto (Usuario), 
            //se llamará al método singIn(Usuario) de la interfaceClient 
            //enviando el objeto (Usuario) 

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
                LOGGER.info("Method signIn is finished");

            } catch (ServerConnectionException | LoginUsernameException
                    | LoginPasswordException | LoginUsernameAndPasswordException ex) {
                throw new Exception(ex.getMessage());

            }
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.getDialogPane().getStylesheets().add(
                    getClass().getResource("/model/views/dialog.css").toExternalForm());

            alert.showAndWait();
            setEmptyAllField();
            Logger.getLogger(SingInWindowController.class.getName())
                    .log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * This method will question you before close the windown if is he want to
     * close
     *
     * @author Elias
     * @param event
     */
    public void cerrarVentana(WindowEvent event) {
        LOGGER.info("Method cerrarVentana is starting");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Quiere salir de la aplicacion?");
        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/model/views/dialog.css").toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            LOGGER.info("Method cerrarVentana is finished");

        } else {
            event.consume();
        }
    }

    /**
     * this method will be clear all fields
     */
    public void setEmptyAllField() {
        LOGGER.info("Method setEmptyAllField is starting");
        userNameTxTF.setText("");
        passwdTxPF.setText("");
        LOGGER.info("Method setEmptyAllField is finished");

    }

}

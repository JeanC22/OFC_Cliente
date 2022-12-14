/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import exceptions.ServerConnectionException;
import exceptions.SignUpEmailAndUsernameException;
import exceptions.SignUpEmailException;
import exceptions.SignUpUsernameException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import userPackage.User;
import interfacePackage.mainInterface;
import javafx.scene.layout.Pane;
import model.IntefaceFactory;
import static userPackage.UserPrivilege.USER;
import static userPackage.UserStatus.DISABLED;

/**
 * FXML Controller class
 * This class will be controller all in the SignUpWindow 
 * @author iker
 * 
 */
public class SignUpWindowController {

    /**
     * these variables are the regular expressions that we will use to validate
     * the user data
     */
    private static String regexUser = "^[a-zA-Z1-9]*$";
    private static String regex = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$";
    private static String regexEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @FXML
    private TextField userNameTxTF;
    @FXML
    private PasswordField passwdTxPF;
    @FXML
    private TextField fullNameTxTF;
    @FXML
    private TextField eMailTxTF;
    @FXML
    private Button signUpBtn;
    @FXML
    private ImageView passwdTT;
    @FXML
    private ImageView eMailTT;
    @FXML
    private ImageView userNameTT;
    @FXML
    private ImageView fullNameTT;
    @FXML
    private Button spBtn;
    @FXML
    private Button backBtn;
    private Stage stage;
    private Boolean typeShow = true;
    @FXML
    private TextField pass_text;
    private static final Logger LOGGER = Logger.getLogger("model.controllers.SignUpWindowController");
    @FXML
    private Pane OFC_SIGN_UP;

    /**
     * we create a stage with which we take from the parameter
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initializes the controller class.
     *
     * @param root
     * @author iker
     */
    public void initStage(Parent root) {
        LOGGER.info("starting initStage(Signup)");

        Scene scene = new Scene(root);

        //Associate scene to primaryStage(Window)
        stage.setScene(scene);
        //Set window properties
        stage.setTitle("OFC SING UP");
        stage.setResizable(false);
        userNameTxTF.setTooltip(new Tooltip("Max characters 15"));
        passwdTxPF.setTooltip(new Tooltip("min 6 and max 12 characters"));
        pass_text.setTooltip(new Tooltip("min 6 and max 12 characters"));
        fullNameTxTF.setTooltip(new Tooltip("Max 255 characters"));
        eMailTxTF.setTooltip(new Tooltip("example@example.com"));

        Tooltip.install(userNameTT, new Tooltip("Max characters 15"));
        Tooltip.install(passwdTT, new Tooltip("min 6 and max 12 characters"));
        Tooltip.install(fullNameTT, new Tooltip("Max 255 characters"));
        Tooltip.install(eMailTT, new Tooltip("example@example.com"));

        //these are the actions of the window with the method references
        spBtn.setOnAction(this::showPasswd);
        signUpBtn.setOnAction(this::signUp);
        backBtn.setOnAction(this::backBtn);
        stage.setOnCloseRequest(this::cerrarVentana);
        //Show window
        this.stage.show();
        LOGGER.info("finished initStage(Signup)");

    }

    /**
     * this method validates that the data is correct and then sends a user to
     * register.if there is any failure it will show an alert with the failure
     * information and if there is no failure it will take us to the signin
     * window.
     *
     *
     * @param event
     * @author iker
     */
    private void signUp(ActionEvent event) {
        try {
            User user = new User();
            IntefaceFactory fac = new IntefaceFactory();
            mainInterface interFace = (mainInterface) fac.getInterface();

            if (!typeShow) {
                passwdTxPF.setText(pass_text.getText());
                passwdTxPF.setVisible(true);
                pass_text.setVisible(false);
                typeShow = true;
            }

            //Validate that the userName ,password ,fullName and eMail fields
            //are filled in. If they are not informed, an error message is
            //displayed.
            if (this.userNameTxTF.getText().trim().equalsIgnoreCase("")
                    || this.passwdTxPF.getText().trim().equalsIgnoreCase("")
                    || this.eMailTxTF.getText().trim().equalsIgnoreCase("")
                    || this.fullNameTxTF.getText().trim().equalsIgnoreCase("")) {
                throw new Exception("Uno de los campos no esta informado");

            }
            /*
              Validate userName length max. 15 characters and no special
              characters if it is longer than 15 characters or has special
              characters, an error is displayed.
             */
            if (!this.userNameTxTF.getText().matches(regexUser)
                    || this.userNameTxTF.getText().length() > 15) {
                userNameTxTF.requestFocus();
                throw new Exception("El campo UserName tiene caracteres "
                        + "especiales o te has pasado de el limite de "
                        + "caracteres permitidos(max 15)");

            }
            /*
              Validate that the password length is a minimum of 6 characters
              and a maximum of 12 characters,if it is less than 6 characters an
              error message will be displayed.
             */
            if (this.passwdTxPF.getText().length() < 6
                    || this.passwdTxPF.getText().length() > 12) {
                passwdTxPF.requestFocus();
                throw new Exception("El campo Password tiene que ser de minimo 6 "
                        + "caracteres y maximo de 12 caracteres");

            }

            //Validate that the length of the fullname is more than 1 character
            if (this.fullNameTxTF.getText().length() <= 1) {
                fullNameTxTF.requestFocus();
                throw new Exception("El campo no puede tener solo un Caracter");
            }

            /*
              Validate that the length of the fullname is 255 characters
              maximum,If it is longer than 255 characters an error message will
              be displayed.
             */
            if (!this.fullNameTxTF.getText().matches(regex)
                    || this.fullNameTxTF.getText().length() > 255) {
                fullNameTxTF.requestFocus();
                throw new Exception("El campo Fullname tiene "
                        + "caracteres especiales o te has pasado de el limite de "
                        + "caracteres permitidos(max 255)");

            }

            /*
              Validate that the format of the eMail(eMailTxTF) by means of an
              Email pattern, if it does not have the correct format an error
              message will be displayed.
             */
            if (!this.eMailTxTF.getText().matches(regexEmail)
                    || this.eMailTxTF.getText().length() >= 255) {
                eMailTxTF.requestFocus();
                throw new Exception("El campo email notiene el formato adecuado"
                        + "(example@example.com) o cuenta con mas de 255 caracteres");
            }
            //make user object
            user.setUsername(this.userNameTxTF.getText());
            user.setPassword(this.passwdTxPF.getText());
            user.setFullname(this.fullNameTxTF.getText());
            user.setEmail(this.eMailTxTF.getText());
            user.setPrivileges(USER);
            user.setStatus(DISABLED);
            try {
                interFace.signUp(user);

                Stage primaryStage = new Stage();

                //link to get the FXML file
                URL viewLink = getClass().getResource("/model/views/SignInWindow.fxml");
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

                //close the actually View
                this.stage.close();
                event.consume();

            } catch (ServerConnectionException | SignUpUsernameException | SignUpEmailException | SignUpEmailAndUsernameException ex) {
                LOGGER.severe(ex.getMessage());
                throw new Exception(ex.getMessage());

            }

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.getDialogPane().getStylesheets().add(
                    getClass().getResource("/model/views/dialog.css").toExternalForm());

            alert.showAndWait();
            Logger.getLogger(SignUpWindowController.class.getName())
                    .log(Level.SEVERE, ex.getMessage());

        }

    }

    /**
     * This method is to send us to the previous window when we click on the
     * arrow.
     *
     * @author iker
     * @param event
     */
    private void backBtn(ActionEvent event) {

        try {
            Stage primaryStage = new Stage();

            URL a = getClass().getResource("/model/views/SignInWindow.fxml");
            System.out.println(a);
            FXMLLoader loader = new FXMLLoader(a);
            Parent root = (Parent) loader.load();
            SignInWindowController mainStageController
                    = ((SignInWindowController) loader.getController());
            mainStageController.setStage(primaryStage);
            mainStageController.initStage(root);
            this.stage.close();
            event.consume();
            //inicializar la scena
        } catch (IOException ex) {
            Logger.getLogger(SignUpWindowController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    /**
     * This method when we want to exit the application will ask us for
     * confirmation to exit.
     *
     * @param event
     * @author iker
     */
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

    /**
     * when typeShow is true this will be set Visible passwordField when
     * typeShow is false the passwd will ve set visible
     *
     * @param event
     * @author Jp,iker
     * @return typeShow (boolean)
     */
    public Boolean showPasswd(ActionEvent event) {

        if (typeShow) {
            pass_text.setText(passwdTxPF.getText());
            pass_text.setVisible(true);
            passwdTxPF.setVisible(false);

            return typeShow = false;
        }
        passwdTxPF.setText(pass_text.getText());
        passwdTxPF.setVisible(true);
        pass_text.setVisible(false);
        return typeShow = true;
    }
}

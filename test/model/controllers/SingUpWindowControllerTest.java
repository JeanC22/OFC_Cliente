/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import static com.sun.deploy.perf.DeployPerfUtil.clear;
import com.sun.glass.ui.Application;
import com.sun.javafx.font.freetype.FTFactory;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.App;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.ButtonMatchers.isDefaultButton;

/**
 *
 * @author iker
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SingUpWindowControllerTest extends ApplicationTest {

    private String textoLargo ="1234567891234567891234567891234567891234112345678912345678912345678912345678912341111";

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(App.class);
        
       
    }

    /*
    Metodo para limpiar solo cuando haya que borrar los 4 campos de manera normal
    */
    public void limpiarTodo() {
        clickOn("#userNameTxTF");
        clickOn("#userNameTxTF");
        write(" ");
        clickOn("#fullNameTxTF");
        clickOn("#fullNameTxTF");
        write(" ");
        clickOn("#eMailTxTF");
        clickOn("#eMailTxTF");
        write(" ");
        eraseText(6);
        clickOn("#passwdTxPF");
        clickOn("#passwdTxPF");
        write(" ");
    }
    
    /*Para el funcionamiento individual de los test se deberá poner @Ignore
    en los test que no se quieran probar y descomentar el clickOn("#signUpLink");
    del test que se quiere probar
    
    Para hacer todos de una con descomentar el clickOn("#signUpLink"); 
    del primer test vale*/

    /**
     * This test clicks on the signUp link in the signIn window.
     *
     */
    @Test
    
    public void test00_signUp() {
        clickOn("#signUpLink");
       
       verifyThat("#OFC_SIGN_UP", isVisible());
    }

    /**
     * The test Checks that when the username field is empty we get a alert
     */
    @Test
    @Ignore
    public void test01_UsuarioVacio() {
         //clickOn("#signUpLink");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#fullNameTxTF");
        write("muchasmascositas");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#signUpBtn");
        verifyThat("Uno de los campos no esta informado", isVisible());
        clickOn("Aceptar");
        clickOn("#passwdTxPF");
        clickOn("#passwdTxPF");
        write(" ");
        clickOn("#fullNameTxTF");
        clickOn("#fullNameTxTF");
        write(" ");
        clickOn("#eMailTxTF");
        clickOn("#eMailTxTF");
        write(" ");
        eraseText(6);

    }

    /**
     * The test checks that when the password field is empty we get an alert.
     */
    @Test
    @Ignore
    public void test02_PasswordVacia() {
         //clickOn("#signUpLink");
        clickOn("#userNameTxTF");
        write("iker");
        clickOn("#fullNameTxTF");
        write("muchasmascositas");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#signUpBtn");
        verifyThat("Uno de los campos no esta informado", isVisible());
        clickOn("Aceptar");
        clickOn("#userNameTxTF");
        clickOn("#userNameTxTF");
        write(" ");
        clickOn("#fullNameTxTF");
        clickOn("#fullNameTxTF");
        write(" ");
        clickOn("#eMailTxTF");
        clickOn("#eMailTxTF");
        write(" ");
        eraseText(6);

    }

    /**
     * The test checks that when the fullname field is empty we get an alert.
     */
    @Test
    @Ignore
    public void test03_FullNameVacio() {
         //clickOn("#signUpLink");

        clickOn("#userNameTxTF");
        write("iker");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#signUpBtn");
        verifyThat("Uno de los campos no esta informado", isVisible());
        clickOn("Aceptar");
        clickOn("#userNameTxTF");
        clickOn("#userNameTxTF");
        write(" ");
        clickOn("#passwdTxPF");
        clickOn("#passwdTxPF");
        write(" ");
        clickOn("#eMailTxTF");
        clickOn("#eMailTxTF");
        write(" ");
        eraseText(6);
    }

    /**
     * The test checks that when the email field is empty we get an alert.
     */
    @Test
    @Ignore
    public void test04_EmailVacio() {
        // clickOn("#signUpLink");
        clickOn("#userNameTxTF");
        write("iker");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#signUpBtn");
        verifyThat("Uno de los campos no esta informado", isVisible());
        clickOn("Aceptar");
        clickOn("#userNameTxTF");
        clickOn("#userNameTxTF");
        write(" ");
        clickOn("#fullNameTxTF");
        clickOn("#fullNameTxTF");
        write(" ");
        clickOn("#eMailTxTF");
        clickOn("#eMailTxTF");
        write(" ");
        eraseText(6);

    }

    /**
     * The test checks that when the username field is longer than 15 characters
     * we get an alert.
     */
    @Test
    @Ignore
    public void test05_UsernameMax15() {
       // clickOn("#signUpLink");
        

        clickOn("#userNameTxTF");
        write("123456789abcdefg");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#signUpBtn");
        verifyThat("El campo UserName tiene caracteres "
                + "especiales o te has pasado de el limite de "
                + "caracteres permitidos(max 15)", isVisible());
        clickOn("Aceptar");
        limpiarTodo();

    }

    /**
     * The test checks that when the username field has special characters we
     * get an alert.
     *
     */
    @Test
    @Ignore
    public void test06_UsernameCaracterEspecial() {
         //clickOn("#signUpLink");
        clickOn("#userNameTxTF");
        write("abcd_1234");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#passwdTxPF");
        write("abcd_1234");

        clickOn("#signUpBtn");
        verifyThat("El campo UserName tiene caracteres "
                + "especiales o te has pasado de el limite de "
                + "caracteres permitidos(max 15)", isVisible());
        clickOn("Aceptar");

        clickOn("#userNameTxTF");
        clickOn("#userNameTxTF");
        write(" ");
        eraseText(6);
        clickOn("#fullNameTxTF");
        clickOn("#fullNameTxTF");
        write(" ");
        clickOn("#eMailTxTF");
        clickOn("#eMailTxTF");
        write(" ");
        eraseText(6);
        clickOn("#passwdTxPF");
        clickOn("#passwdTxPF");
        write(" ");

    }

    /**
     * The test checks that when the password field is less than 6 characters we
     * get an alert.
     */
    @Test
    @Ignore
    public void test07_PasswordCorta() {
         //clickOn("#signUpLink");
        clickOn("#userNameTxTF");
        write("iker");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#passwdTxPF");
        write("a");
        clickOn("#signUpBtn");
        verifyThat("El campo Password tiene que ser de minimo 6 "
                + "caracteres y maximo de 12 caracteres", isVisible());
        clickOn("Aceptar");

        limpiarTodo();

    }

    /**
     * The test checks that when the username field is longer than 12 characters
     * we get an alert.
     */
    @Test
    @Ignore
    public void test08_PasswordLarga() {
         //clickOn("#signUpLink");
        clickOn("#userNameTxTF");
        write("iker");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#passwdTxPF");
        write("abcd_12345678");
        clickOn("#signUpBtn");
        verifyThat("El campo Password tiene que ser de minimo 6 "
                + "caracteres y maximo de 12 caracteres", isVisible());
        clickOn("Aceptar");

        limpiarTodo();
    }

    /**
     * The test checks that when the fullname field is longer than 40 characters
     * we get an alert.
     */
    @Test
    
    public void test09_FullNameLarga() {
         //clickOn("#signUpLink");
        clickOn("#userNameTxTF");
        write("iker");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#fullNameTxTF");
        write(textoLargo);
        clickOn("#signUpBtn");

        verifyThat("El campo Fullname tiene "
                + "caracteres especiales o te has pasado de el limite de "
                + "caracteres permitidos(max 255)", isVisible());
        clickOn("Aceptar");

       clickOn("#userNameTxTF");
        clickOn("#userNameTxTF");
        write(" ");
        clickOn("#fullNameTxTF");
        clickOn("#fullNameTxTF");
        write(" ");
        eraseText(255);
        clickOn("#eMailTxTF");
        clickOn("#eMailTxTF");
        write(" ");
        eraseText(6);
        clickOn("#passwdTxPF");
        clickOn("#passwdTxPF");
        write(" ");

    }

    /**
     * The test checks that when the email field is not in the correct format,
     * an alert is displayed
     *
     */
    @Test
    @Ignore
    public void test10_EmailFormatoInadecuado() {
         //clickOn("#signUpLink");
        clickOn("#userNameTxTF");
        write("iker");
        clickOn("#eMailTxTF");
        write("cositas");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#passwdTxPF");
        write("abcd_1234");

        clickOn("#signUpBtn");
        verifyThat("El campo email notiene el formato adecuado"
                + "(example@example.com) o cuenta con mas de 255 caracteres", isVisible());
        clickOn("Aceptar");

        limpiarTodo();

    }

    @Test
    @Ignore
    public void test11_EmailDemasiadoLargo() {
         //clickOn("#signUpLink");
        
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#userNameTxTF");
        write("iker");
        clickOn("#eMailTxTF");
        write("GRAVE: El campo UserName tiene caracteres especiales o te has pasado de el limite de caracteres permitidos(max 15)"
                + "nov 10, 2022 12:18:41 PM model.controllers.SignUpWindowController signUp"
                + "GRAVE: El campo Password tiene que ser de minimo 6 caracteres y maximo de 12 caracteres"
                + "nov 10, 2022 12:18:43 PM model.controllers.SignUpWindowController signUp"
                + "GRAVE: El campo Password tiene que ser de minimo 6 caracteres y maximo de 12 caracteres"
                + "nov 10, 2022 12:18:45 PM model.controllers.SignUpWindowController signUp"
                + "GRAVE: El campo Fullname tiene caracteres especiales o te has pasado de el limite de caracteres permitidos(max 40)@gmail.com");
        clickOn("#signUpBtn");
        verifyThat("El campo email notiene el formato adecuado"
                + "(example@example.com) o cuenta con mas de 255 caracteres", isVisible());
        clickOn("Aceptar");
        clickOn("#userNameTxTF");
        clickOn("#userNameTxTF");
        write(" ");
        clickOn("#fullNameTxTF");
        clickOn("#fullNameTxTF");
        write(" ");
        clickOn("#eMailTxTF");
        clickOn("#eMailTxTF");
        write(" ");
        eraseText(255);
        clickOn("#passwdTxPF");
        clickOn("#passwdTxPF");
        write(" ");

    }

    /**
     * The test checks that when we click on the eye icon, the password is
     * displayed.
     */
    @Test
    @Ignore
    public void test12_MostrarContraseña() {
        // clickOn("#signUpLink");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#spBtn");
        verifyThat("#pass_text", isVisible());
        clickOn("#spBtn");
        verifyThat("#passwdTxPF", isVisible());
        clickOn("#passwdTxPF");
        write(" ");

    }

    /**
     * The test checks if the username already exists in the database and shows
     * an alert.
     */
    @Test
    @Ignore
    public void test13_ExceptionUsername() {
        // clickOn("#signUpLink");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#userNameTxTF");
        write("iker");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#signUpBtn");
        verifyThat("Username ya existe.", isVisible());
        clickOn("Aceptar");
        limpiarTodo();
    }

    /**
     * The test checks if the email already exists in the database and shows an
     * alert.
     */
    @Test
    @Ignore
    public void test14_ExceptionEmail() {
        // clickOn("#signUpLink");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#userNameTxTF");
        write("iker2");
        clickOn("#eMailTxTF");
        write("iker@iker.com");
        clickOn("#signUpBtn");
        verifyThat("Email ya existe.", isVisible());
        clickOn("Aceptar");
        limpiarTodo();

    }

    /**
     * The test checks that if the connection to the server fails it displays an
     * alert.
     */
    @Test
    @Ignore
    public void test15_ExceptionConnection() {
        // clickOn("#signUpLink");
        clickOn("#userNameTxTF");
        write("Iker");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#eMailTxTF");
        write("iker@gmail.com");
        clickOn("#signUpBtn");
        verifyThat("Servidor se encuentra fuera de servició.", isVisible());
        limpiarTodo();
    }

    /**
     * The test checks that when the user's registration is correct, the sign up
     * window is displayed.
     */
    @Test
    @Ignore
    public void test16_SignUpCorrect() {
         //clickOn("#signUpLink");
        clickOn("#userNameTxTF");
        write("Iker4");
        clickOn("#passwdTxPF");
        write("abcd_1234");
        clickOn("#fullNameTxTF");
        write("cositas");
        clickOn("#eMailTxTF");
        write("iker4@gmail.com");
        clickOn("#signUpBtn");
        verifyThat("#OFC_SIGN_IN", isVisible());

    }

    /**
     * The method checks that when we click on the arrow it shows us the signIn
     * window.
     */
    @Test
    public void test17_salirFlecha() {
        //clickOn("#signUpLink");
        clickOn("#backBtn");
        verifyThat("#OFC_SIGN_IN", isVisible());
    }
}

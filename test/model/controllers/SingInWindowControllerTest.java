/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model.controllers;

import static com.sun.deploy.perf.DeployPerfUtil.clear;
import java.util.concurrent.TimeoutException;
import model.App;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

/**
 *
 * @author jp22
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SingInWindowControllerTest extends ApplicationTest {

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(App.class);
    }

    /**
     * This method will be verify all fields aren't emptys
     *
     * @verifyThat the alert has the text "Todos los campos no estan informados"
     */
    @Test
    public void test1_allAlert1() {
        clickOn("#singInBtn");
        verifyThat("Todos los campos no estan informados", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    /**
     * This method will be verify the usernameField can't had more than 15
     * characters for get a alert
     *
     * @verifyThat the alert has the text "La longitud del campo user supera los
     * 15 caracteres".
     */
    @Test
    public void test1_allAlert2() {
        write("1111111111111111");
        clickOn("#passwdTxPF");
        write("123123");
        clickOn("#singInBtn");
        verifyThat("La longitud del campo user supera los 15 caracteres", NodeMatchers.isVisible());
        clickOn("Aceptar");
        clear();

    }

    /**
     * This method will be verify the passwordField can't had minus than 6
     * characters for get a alert
     *
     * @verifyThat the alert has the text "El campo password es minimo de 6
     * caracteres o maximo de 12".
     */
    @Test
    public void test1_allAlert3() {

        write("testman");
        clickOn("#passwdTxPF");
        eraseText(6);
        write("123");
        clickOn("#singInBtn");
        verifyThat("El campo password es minimo "
                + "de 6 caracteres o maximo de 12", NodeMatchers.isVisible());
        clickOn("Aceptar");
        eraseText(3);

    }

    /**
     * This method will be verify the passwordField is more than 6 characters
     * for get a alert
     *
     * @verifyThat the alert has the text "El campo password es minimo de 6
     * caracteres o maximo de 12".
     */
    @Test
    public void test1_allAlert4() {

        clickOn("#passwdTxPF");
        write("123123123123123");
        clickOn("#singInBtn");
        verifyThat("El campo password es minimo "
                + "de 6 caracteres o maximo de 12", NodeMatchers.isVisible());
        clickOn("Aceptar");
        eraseText(1);

    }

    /**
     * This method will be verify the usernameField can't had special Characters
     *
     * @verifyThat the alert has the text "El campo password es minimo de 6
     * caracteres o maximo de 12".
     */
    @Test
    public void test1_allAlert5() {

        clickOn("#userNameTxTF");
        eraseText(8);
        write("¿?¿?¿?¿?¿?¿?");
        clickOn("#passwdTxPF");
        write("1234567");
        clickOn("#singInBtn");
        verifyThat("El campo contiene caracteres especiales", NodeMatchers.isVisible());
        clickOn("Aceptar");
        eraseText(1);

    }

    /**
     * This method will be test all the usernameAlerts will be click on the
     * button(#singInBtn) for get a alert
     *
     * @verifyThat the alert has the text "Todos los campos no estan informados"
     * will introduce a not exist User and password for get a another alert
     * @verifyThat the alert has the text "Username no valido."
     */
    @Test
    public void test2_usernameAlerts() {

        clickOn("#userNameTxTF");
        write("testman2");
        clickOn("#passwdTxPF");
        eraseText(6);
        clickOn("#passwdTxPF");
        eraseText(1);
        write("testman2");
        clickOn("#singInBtn");
        verifyThat("Username no valido.", NodeMatchers.isVisible());
        clickOn("Aceptar");
        eraseText(1);

    }

    /**
     * This method will be test a passwordAlert will be introduce a bad Password
     * with a existed user
     *
     * @verifyThat the alert has the text "Contraseña no valida."
     */
    @Test
    public void test3_passwordAlert() {
        clickOn("#userNameTxTF");
        eraseText(4);
        clickOn("#userNameTxTF");
        eraseText(4);
        write("testman");
        clickOn("#passwdTxPF");
        eraseText(6);
        clickOn("#passwdTxPF");
        eraseText(2);
        write("testman2");
        clickOn("#singInBtn");
        verifyThat("Contraseña no valida.", NodeMatchers.isVisible());
        clickOn("Aceptar");
        eraseText(1);

    }

    /**
     * This method will be test you can open the SignUpWindow whit the hyperLink
     *
     * @verifyThat the SignUpWindow with the pane fx:id="OFC_SIGN_UP" isVisible
     * After will be open the SignInWindow
     * @verifyThat the SignInWindow with the pane fx:id="OFC_SIGN_IN" isVisible
     */
    @Test
    public void test4_signUp() {
        clickOn("#signUpLink");
        verifyThat("#OFC_SIGN_UP", NodeMatchers.isVisible());
        clickOn("#backBtn");
        verifyThat("#OFC_SIGN_IN", NodeMatchers.isVisible());

    }

    /**
     * This method will be test the signIn with a account for test verify the
     * user is login
     *
     * @verifyThat the LogedWindow with the pane fx:id="OFC_LOGED" isVisible
     * @verifyThat the SignInWindow with the pane fx:id="OFC_SIGN_IN" isVisible
     */
    @Test
    public void test5_signIn() {
        write("testman");
        clickOn("#passwdTxPF");
        write("testman");
        clickOn("#singInBtn");
        verifyThat("#OFC_LOGED", NodeMatchers.isVisible());
        clickOn("#logoutLabel");
        verifyThat("#OFC_SIGN_IN", NodeMatchers.isVisible());

    }

    /**
     * Test serverConnection This test will be work when the server is
     * disconnected this test will be work when the pool Connection had < 10
     * connections @ignore
     *
     */
    @Test
    @Ignore
    public void test6_serverConnection() {
        clickOn("#userNameTxTF");
        write("testman");
        clickOn("#passwdTxPF");
        write("testman");
        clickOn("#singInBtn");
        verifyThat("Servidor se encuentra fuera de servició", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

}

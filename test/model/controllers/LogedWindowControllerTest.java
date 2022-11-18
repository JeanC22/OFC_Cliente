/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.util.logging.Logger;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.App;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogedWindowControllerTest extends ApplicationTest{
    private Label welcomeString;
    private ImageView profileImage;
    private static final Logger LOGGER = Logger.getLogger("model.controllers.LogedWindowControllerIT");
    
    /**
     * Start Loged Window and lookup for nodes to be used in testing 
     * @param stage
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        LOGGER.info("Start Loged Window");
        new App().start(stage);
        welcomeString = lookup("#welcomeString").query();
        profileImage = lookup("#profileImage").query();
    }
    
    @Test
    public void test_SignIn(){
        clickOn("#userNameTxTF");
        write("testman");
        clickOn("#passwdTxPF");
        write("testman");
        clickOn("#singInBtn");
    }

    
    
    /**
     * Test of initStage method, of class LogedWindowController.
     */
    @Test
   
    public void testInitStage(){
        LOGGER.info("Welcome User");
        verifyThat("#OFC_LOGED", isVisible());
        clickOn(profileImage);
        
         
    }
    /**
     * Test of logout method, of class LogedWindowController
     */
    @Test
    public void testLogout(){
        LOGGER.info("Logout"); 
       
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enumPackcage.ActionType;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import messagePackage.Message;
import userPackage.User;
import enumPackcage.ExceptionType;
import exceptions.LoginPasswordException;
import exceptions.LoginUsernameAndPasswordException;
import exceptions.LoginUsernameException;
import exceptions.ServerConnectionException;
import exceptions.SignUpEmailAndUsernameException;
import exceptions.SignUpEmailException;
import exceptions.SignUpUsernameException;
import interfacePackage.mainInterface;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * 
 * @author jp22
 */
public class socketCliente implements mainInterface {

    static final String HOST = "LOCALHOST";
    static final int PUERTO = 9999;
    private User user;
    private Message message;
    private static final Logger LOGGER = Logger.getLogger("model.socketCliente");

    public User socketCliente(Message message) {
        LOGGER.info("comienzando socket ");
        Socket skCliente = null;
        try {
            //log waiting for a connnection
            skCliente = new Socket(HOST, PUERTO);
            //log connected
            //Initialization the ObjectOutputStream we gonna print the object(User)
            ObjectOutputStream setObject = new ObjectOutputStream(skCliente.getOutputStream());
            setObject.writeObject(message);
            //Initialization the ObjectInputStream we gonna get the Object(User)
            ObjectInputStream getObject
                    = new ObjectInputStream(skCliente.getInputStream());
            //we create the object
            this.message = (Message) getObject.readObject();
            //retunr the Message with the object User

            user = this.message.getUser();

        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return user;
    }

    @Override
    public User signIn(User user) throws ServerConnectionException, LoginUsernameException, LoginPasswordException, LoginUsernameAndPasswordException {

        Message message = new Message();
        message.setAcType(ActionType.SIGNIN);
        message.setUser(user);
        message.setExType(null);

        socketCliente(message);

        if (message.getExType().equals(ExceptionType.SERVERCONECTIONEXCEPTION)) {
            throw new ServerConnectionException("Servidor fuera de servició.");
        }

        if (message.getExType().equals(ExceptionType.LOGINUSERNAMEANDPASSWORDEXCEPTION)) {
            throw new LoginUsernameAndPasswordException("Username y la password son incorrectas.");
        }

        if (message.getExType().equals(ExceptionType.LOGINUSERNAMEEXCEPTION)) {
            throw new LoginUsernameException("Username no valido.");
        }

        if (message.getExType().equals(ExceptionType.LOGINPASSWORDEXCEPTION)) {
            throw new LoginPasswordException("Contraseña no valida.");
        }

        return user;

    }

    @Override
    public void signUp(User user) throws SignUpUsernameException, SignUpEmailException, SignUpEmailAndUsernameException, ServerConnectionException {
        LOGGER.info("singUp starting");
        Message message = new Message();
        message.setAcType(ActionType.SIGNUP);
        message.setUser(user);
        socketCliente(message);
        if (message.getExType().equals(ExceptionType.SIGNUPEMAILANDUSERNAMEEXCEPTION)) {
            throw new SignUpEmailAndUsernameException("Username y Email ya existe.");
        }

        if (message.getExType().equals(ExceptionType.SIGNUPUSERNAMEEXCEPTION)) {
            throw new SignUpUsernameException("Username ya existe.");
        }

        if (message.getExType().equals(ExceptionType.SIGNUPEMAILEXCEPTION)) {
            throw new SignUpEmailException("Email ya existe.");
        }
    }

    @Override
    public void logOut() {
       
    }

}

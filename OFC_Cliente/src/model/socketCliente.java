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
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * This is the socket class
 * @author jp22
 */
public class socketCliente implements mainInterface {

    static final String HOST = ResourceBundle.getBundle("model.PropertiesFile").getString("host");
    static final Integer PUERTO = (Integer) ResourceBundle.getBundle("model.PropertiesFile").getObject("port");
    private Message message;
    private static final Logger LOGGER = Logger.getLogger("model.socketCliente");
    Socket skCliente;

    public socketCliente() {
        this.skCliente = null;
    }

    /**
     *The method will connect to the server socket to read and write messages, 
     * there will be a 4 second delay to check if there is any failure with the
     * connection which will result in a serverConnection exception.
     * @author jp
     * @param mensaje
     * @return message
     * @throws ServerConnectionException
     */
    public Message SocketCliente(Message mensaje) throws ServerConnectionException {

        try {
            LOGGER.info("comienzando socket ");

            //log waiting for a connnection
            skCliente = new Socket();
            SocketAddress socektAddress = new InetSocketAddress(HOST, PUERTO);

            //socketAddress is (HOST,PORT)
            //connect(HOST+PORT, TIMEOUT)
            skCliente.connect(socektAddress, 4000);
        } catch (IOException ex) {
            Logger.getLogger(socketCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerConnectionException(" El Servidor se encuentra fuera de servició.");
        }
        try {
            //log connected
            //Initialization the ObjectOutputStream we gonna print the object(User)
            ObjectOutputStream setObject = new ObjectOutputStream(skCliente.getOutputStream());
            setObject.writeObject(mensaje);
            //Initialization the ObjectInputStream we gonna get the Object(User)
            ObjectInputStream getObject
                    = new ObjectInputStream(skCliente.getInputStream());
            //we create the object
            message = (Message) getObject.readObject();
            //return the Message with the object User

        } catch (SocketException ex) {
            throw new ServerConnectionException("Servidor se encuentra fuera de servició.");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(socketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return message;
    }

    /**
     * The method creates a message object with the action(SignIn,SignUp) to
     * perform and the user that we will send to the client socket method if 
     * there is any failure it returns a message object with the exception and
     * depending on the exception received it will send us a message or another
     * and if everything is correct it returns us a user.
     * 
     * @author jp
     * @param user
     * @return User
     * @throws ServerConnectionException
     * @throws LoginUsernameException
     * @throws LoginPasswordException
     * @throws LoginUsernameAndPasswordException 
     */
    @Override
    public User signIn(User user) throws ServerConnectionException,
            LoginUsernameException, LoginPasswordException, LoginUsernameAndPasswordException {

        Message message = new Message();

        message.setAcType(ActionType.SIGNIN);
        message.setUser(user);
        message.setExType(null);

        Message messageReceived = SocketCliente(message);

        if (messageReceived.getExType() != null) {
            ExceptionType exc = messageReceived.getExType();
            switch (exc) {
                case LOGINUSERNAMEANDPASSWORDEXCEPTION:
                    throw new LoginUsernameAndPasswordException("Username y la password son incorrectas.");
                case LOGINUSERNAMEEXCEPTION:
                    throw new LoginUsernameException("Username no valido.");
                case LOGINPASSWORDEXCEPTION:
                    throw new LoginPasswordException("Contraseña no valida.");
            }
        }
        return messageReceived.getUser();
    }

    /**
     * The method creates a message object with the action(SignIn,SignUp) to
     * perform and the user that we will send to the client socket method if
     * there is any failure it returns a message object with the exception and
     * depending on the exception received it will send us a message or another
     * @author jp,iker
     * @param user
     * @throws SignUpUsernameException
     * @throws SignUpEmailException
     * @throws SignUpEmailAndUsernameException
     * @throws ServerConnectionException 
     */
    @Override
    public void signUp(User user) throws SignUpUsernameException, SignUpEmailException, SignUpEmailAndUsernameException, ServerConnectionException {
        LOGGER.info("singUp starting");
        Message message = new Message();
        message.setUser(user);
        message.setAcType(ActionType.SIGNUP);
        message.setExType(null);
        Message messageReceived = SocketCliente(message);

        if (messageReceived.getExType() != null) {
            ExceptionType exc = messageReceived.getExType();
            switch (exc) {
                case SIGNUPEMAILANDUSERNAMEEXCEPTION:
                    throw new SignUpEmailAndUsernameException("Username y Email ya existe.");
                case SIGNUPUSERNAMEEXCEPTION:
                    throw new SignUpUsernameException("Username ya existe.");
                case SIGNUPEMAILEXCEPTION:
                    throw new SignUpEmailException("Email ya existe.");
            }
        }
    }

    @Override
    public void logOut() {

    }

}

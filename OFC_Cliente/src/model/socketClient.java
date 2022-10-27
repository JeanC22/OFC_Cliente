/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author jp22
 */
public class socketClient {

    static final String HOST = "LOCALHOST";
    static final int PUERTO = 9999;

    public socketClient() {
            User user = (User) getUser.readObject();
        Socket skCliente = null;
        try {
            //log waiting for a connnection
            skCliente = new Socket(HOST, PUERTO);
            //log connected
            //Initialization the ObjectOutputStream we gonna print the object(User)
            ObjectOutputStream setObject = new ObjectOutputStream(skCliente.getOutputStream());
            setObject.writeObject(user);
            //Initialization the ObjectInputStream we gonna get the Object(User)
            ObjectInputStream getUser
                    = new ObjectInputStream(skCliente.getInputStream());
            ObjectOutputStream setUser
                    = new ObjectOutputStream(skCliente.getOutputStream());
            //Initialization the objecet (User) with the ObjectInputStream

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                skCliente.close();
            } catch (Exception e) {
            }

        }
    }

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 * This is the class IntefaceFactory and make a socket
 * @author jp22
 */
public class IntefaceFactory {
    
        public IntefaceFactory(){
            
        }
        public SocketCliente getInterface(){
            
            SocketCliente socket = new SocketCliente();
            return socket;
        }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author jp22
 */
public class IntefaceFactory {
    
        public IntefaceFactory(){
            
        }
        public socketCliente getInterface(){
            
            socketCliente socket = new socketCliente();
            return socket;
        }
    
}
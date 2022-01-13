/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.psp.chattcp.interfazcliente;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import org.psp.chattcp.cliente.ClienteChat;

/**
 *
 * @author ruben
 */
public class Inicio {

    public static void main(String[] args) {

        //solicitar el nick del usuario
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick:");

        if (nombre == null || nombre.trim().length() == 0) {
            System.out.println("El nombre está vacío....");
            return;
        }

        try {
            ClienteChat cliente = new ClienteChat(nombre);
            cliente.conectar("localhost",44444);
            JFrameChat frameChat = new JFrameChat(nombre,cliente);
            frameChat.setVisible(true);
            new Thread(frameChat).start();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
                    "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
        }

    }
}

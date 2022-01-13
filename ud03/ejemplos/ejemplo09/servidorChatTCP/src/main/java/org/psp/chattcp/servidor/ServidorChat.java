package org.psp.chattcp.servidor;

import java.io.*;
import java.net.*;

/**
 * Proceso servidor de aplicación de Chat TCP
 * Escucha en el puerto 44444
 * @author ruben
 */
public class ServidorChat {

    private static final int MAXIMO = 5;//MAXIMO DE CONEXIONES PERMITIDAS	

    public static void main(String args[]) throws IOException {
        int puerto = 44444;

        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor iniciado, escuchando en el puerto " + puerto + "...");

        ComunHilos comun = new ComunHilos();

        while (comun.getConexiones() < MAXIMO) {
            Socket socket = servidor.accept();// esperando cliente

            comun.addSocket(socket);

            HiloClienteChat hilo = new HiloClienteChat(socket, comun);
            hilo.start();
        }
        
        servidor.close();
    }
}


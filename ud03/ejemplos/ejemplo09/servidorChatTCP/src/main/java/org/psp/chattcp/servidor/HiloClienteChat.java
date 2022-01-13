package org.psp.chattcp.servidor;

import java.io.*;
import java.net.*;

/**
 * Hilo que atiende a cada cliente conectado al chat
 *
 * @author ruben
 */
public class HiloClienteChat extends Thread {

    private Socket socket = null;
    private ComunHilos comun;

    public HiloClienteChat(Socket s, ComunHilos comun) {
        this.socket = s;
        this.comun = comun;
    }

    public void run() {
        try {
            // nuevo flujo entrada para leer los mensajes entrantes
            DataInputStream fentrada = new DataInputStream(socket.getInputStream());
            System.out.println("NUMERO DE CONEXIONES ACTUALES: " + comun.getConexiones());

            String cadena;
            do {
                cadena = fentrada.readUTF();
                if (!cadena.trim().equals("*")) {
                    comun.setMensajes(comun.getMensajes() + cadena + "\n");
                    EnviarMensajesaTodos(comun.getMensajes());
                }
            } while (!cadena.trim().equals("*"));

            //desconexión de cliente
            comun.removeSocket(socket);
            System.out.println("NUMERO DE CONEXIONES ACTUALES: " + comun.getConexiones());
            socket.close();

        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
        }

    }

    /**
     * Envía los mensajes del chat a todos los clientes
     * @param texto mensajes a enviar
     */
    private void EnviarMensajesaTodos(String texto) {
        int i;
        // recorremos tabla de sockets para enviarles los mensajes
        for (i = 0; i < comun.getConexiones(); i++) {
            Socket s1 = comun.getSocket(i);
            if (!s1.isClosed()) {
                try {
                    DataOutputStream fsalida = new DataOutputStream(s1.getOutputStream());
                    fsalida.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

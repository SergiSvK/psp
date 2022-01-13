package org.psp.chattcp.cliente;

import java.io.*;
import java.net.*;

/**
 * Aplicación cliente del chat TCP
 *
 * @author ruben
 */
public class ClienteChat {

    //socket de comunicación con el servidor
    private Socket socket = null;
    // streams
    private DataInputStream fentrada;
    private DataOutputStream fsalida;

    //nombre del usuario
    private String nombre;

    /**
     *
     * @param nombre nick del usuario
     */
    public ClienteChat(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Conexión con un servidor
     *
     * @param host ip o host del servidor
     * @param puerto número de puerto del servidor
     * @throws IOException
     */
    public void conectar(String host, int puerto) throws IOException {
        //creación del socket
        this.socket = new Socket(host, puerto);

        //creación de los flujos de entrada y salida
        fentrada = new DataInputStream(socket.getInputStream());
        fsalida = new DataOutputStream(socket.getOutputStream());

        //enviar el mensaje de entrada del usuario en el chat
        String texto = " > Entra en el Chat ... " + nombre;
        fsalida.writeUTF(texto);

    }

    /**
     * Envía el mensaje indicado al chat anteponiendo el nick del usuario
     *
     * @param mensaje
     * @throws IOException
     */
    public void enviarMensaje(String mensaje) throws IOException {
        String texto = nombre + "> " + mensaje;
        fsalida.writeUTF(texto);
    }

    /**
     * Espera la recepción de mensajes provinientes del servidor
     *
     * @return mensajes recibidos del servidor
     * @throws IOException
     */
    public String recibirMensajes() throws IOException {
        return fentrada.readUTF();
    }

    /**
     * Finaliza la sesión de chat
     *
     * @throws IOException
     */
    public void salir() throws IOException {
        //primero envía el mensaje de salida al chat para notificar a los demás
        String texto = " > Abandona el Chat ... " + nombre;
        fsalida.writeUTF(texto);
        fsalida.flush();
        fsalida.writeUTF("*");

        //cierre de flujos y de socket
        fentrada.close();
        fsalida.close();
        socket.close();
    }

}

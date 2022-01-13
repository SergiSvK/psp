package org.psp.chattcp.servidor;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ComunHilos {

    private List<Socket> sockets;// SOCKETS CONECTADOS
    private String mensajes; //MENSAJES DEL CHAT

    public ComunHilos() {
        this.sockets = new ArrayList<>();
        mensajes = "";
    }

    public synchronized int getConexiones() {
        return sockets.size();
    }

    public synchronized String getMensajes() {
        return mensajes;
    }

    public synchronized void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public synchronized void addSocket(Socket s) {
        sockets.add(s);
    }
    
    public synchronized void removeSocket(Socket s) {
        sockets.remove(s);
    }

    public synchronized Socket getSocket(int i) {
        return sockets.get(i);
    }

}

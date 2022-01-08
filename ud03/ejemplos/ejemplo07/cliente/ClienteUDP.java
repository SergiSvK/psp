import java.net.*;
import java.io.*;

/**
 * Cliente de aplicación cliente/servidor UDP en el que el servidor recibe en el puerto 6789 y
 * reenvía el mismo mensaje que le envían los diferentes clientes.
 * Cliente UDP , el primer argumento de línea de comandos es el mensaje y el segundo el nombre/ip del servidor
 * 
 */
public class ClienteUDP {
    // Los argumentos proporcionan el mensaje y el nombre del servidor
    public static void main(String args[]) {
        try {
            DatagramSocket socketUDP = new DatagramSocket();
            byte[] mensaje = args[0].getBytes();
            InetAddress hostServidor = InetAddress.getByName(args[1]);
            int puertoServidor = 6789;
            // Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket peticion = new DatagramPacket(mensaje, args[0].length(), hostServidor, puertoServidor);
            // Enviamos el datagrama
            socketUDP.send(peticion);
            
            // Construimos el DatagramPacket que contendrá la respuesta
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(respuesta);
            // Enviamos la respuesta del servidor a la salida estandar
            System.out.println("Respuesta: " + new String(respuesta.getData()));
            // Cerramos el socket
            socketUDP.close();
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
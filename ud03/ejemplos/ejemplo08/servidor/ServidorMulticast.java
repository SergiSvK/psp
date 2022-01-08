import java.io.*;
import java.net.*;

/**
 * Servidor multicast UDP
 * Crea un grupo multicast en la IP 225.0.0.1, solicita mensajes por teclado 
 * que son enviados al grupo de multidifusión hasta que se introduzca * que finalizará el proceso
 */
public class ServidorMulticast {
  public static void main(String args[]) throws Exception {
    // Se crea el socket de multidifusión para el grupo 225.0.0.1
    MulticastSocket ms = new MulticastSocket();
    int puerto = 12345;// Puerto multicast
    InetAddress grupo = InetAddress.getByName("225.0.0.1");// Grupo

    //solicitud de mensajes y envío por multidifusión
    String cadena = "";
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    while (!cadena.trim().equals("*")) {
      System.out.print("Datos a enviar al grupo: ");
      cadena = in.readLine();
      //enviamos el mensaje introducido por el usuario al grupo de multidifusión
      DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), grupo, puerto);
      ms.send(paquete);
    }
    
    //finalización
    ms.close();
    System.out.println("Socket cerrado...");
  }
}

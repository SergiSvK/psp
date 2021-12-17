import java.io.*;
import java.net.*;

/**
 * Ejemplo de envío y recepción de objetos mediante un socket TCP
 * Clase que implementa el cliente
 * Se conectará al servidor y esperará que éste le envíe un objeto Persona
 * Modificará el objeto Persona recibido y se lo enviará de vuelta al servidor
 */
public class TCPObjetoCliente {
  public static void main(String[] arg) throws IOException, ClassNotFoundException {

    // Conecta a un servidor en la máquina local y puerto 6000
    String Host = "localhost";
    int Puerto = 6000;// puerto remoto

    System.out.println("PROGRAMA CLIENTE INICIADO....");
    Socket socketCliente = new Socket(Host, Puerto);

    // Creamos un flujo de entrada para objetos
    ObjectInputStream streamEntrada = new ObjectInputStream(socketCliente.getInputStream());
    // Se recibe un objeto Persona
    Persona persona = (Persona) streamEntrada.readObject();// recibo objeto
    System.out.println("Recibo: " + persona);

    // Modifico el objeto recibido
    persona.setNombre("Juan Ramos");
    persona.setEdad(22);

    //Creamos un flujo DE salida para objetos
    ObjectOutputStream streamSalida = new ObjectOutputStream(socketCliente.getOutputStream());
    
    // Se envía el objeto modificado
    streamSalida.writeObject(persona);
    System.out.println("Envio: " + persona);

    // CERRAR STREAMS Y SOCKETS
    streamEntrada.close();
    streamSalida.close();
    socketCliente.close();
  }
}// ..

import java.io.*;
import java.net.*;

/**
 * Ejemplo de envío y recepción de objetos mediante un socket TCP
 * Clase que implementa el servidor
 */
public class TCPObjetoServidor {
  public static void main(String[] arg) throws IOException, ClassNotFoundException {

    // Configuramos un socket TCP para escuchar en el puerto 6000
    int numeroPuerto = 6000;// Puerto
    ServerSocket servidor = new ServerSocket(numeroPuerto);
    System.out.println("Esperando al cliente.....");
    Socket cliente = servidor.accept();

    // Ha llegado el cliente
    // Obtenemos un flujo de salida para objetos
    ObjectOutputStream streamSalida = new ObjectOutputStream(cliente.getOutputStream());
    // Se prepara un nuevo objeto Persona y se envía al cliente
    Persona persona = new Persona("Juan", 20);
    streamSalida.writeObject(persona); // enviando objeto
    System.out.println("Envio: " + persona);

    // el cliente nos va a devolver este objeto modificado
    // Obtenemos un flujo de entrada para leer objetos
    ObjectInputStream streamEntrada = new ObjectInputStream(cliente.getInputStream());
    Persona personaRecibida = (Persona) streamEntrada.readObject();
    System.out.println("Recibo: " + personaRecibida);

    // CERRAR STREAMS Y SOCKETS
    streamSalida.close();
    streamEntrada.close();
    cliente.close();
    servidor.close();
  }
}

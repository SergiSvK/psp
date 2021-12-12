import java.io.*;
import java.net.*;

/**
 * El servidor espera recibir las peticiones en el puerto 6789
 */
public class Servidor {
  public static void main(String argv[]) throws Exception {

    // Paso 1: Crear un objeto de la clase ServerSocket para escuchar peticiones en
    // el puerto asignado al servicio.
    ServerSocket socketPeticiones = new ServerSocket(6789);

    System.out.println("Servidor arrancado, escuchando en el puerto 6789");

    while (true) {
      // Paso 2: Esperar solicitudes de clientes.
      Socket socketCliente = socketPeticiones.accept();
      
      // Paso 3: Ha llegado una solicitud, recoger el socket y crear los streams de
      // entrada y salida
      BufferedReader entradaDelCliente = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
      DataOutputStream salidaAlCliente = new DataOutputStream(socketCliente.getOutputStream());
      
      // Paso 4: Realizar la comunicación con el cliente
      // leemos una frase de la entrada del cliente
      String fraseCliente = "";
      do {
        fraseCliente = entradaDelCliente.readLine();
        System.out.println("RECIBIDO DEL CLIENTE:" + fraseCliente);
        // se la devolvemos en mayúsculas
        String fraseMayusculas = fraseCliente.toUpperCase() + '\n';
        salidaAlCliente.writeBytes(fraseMayusculas);
      } while (fraseCliente.equals("*") == false);
      
      //Paso 5: Cerrar comunicación con el cliente
      entradaDelCliente.close();
      salidaAlCliente.close();
      socketCliente.close();
    }
  }
}
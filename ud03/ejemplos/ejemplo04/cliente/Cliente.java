import java.io.*;
import java.net.*;

/**
 * Este cliente recogerá una cadena de texto escrita por el usuario por el
 * teclado, se la enviará al servidor y terminará
 */
public class Cliente {

  public static void main(String argv[]) throws Exception {

    // Paso 1: abrir un socket al servidor que en este caso está escuchando en
    // localhost(127.0.0.1) y puerto 6789
    Socket clientSocket = new Socket("localhost", 6789);

    // Paso 2: Obtener las referencias al stream de entrada y de salida al socket.
    BufferedReader entradaDelServidor = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    DataOutputStream salidaAlServidor = new DataOutputStream(clientSocket.getOutputStream());

    // Paso 3: Leer/escribir en los streams para comunicarse con el servidor
    // Escribir lo que introduce el usuario en els stream de salida hasta que
    // introduzca un *
    BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));
    String frase = "";
    do {
      System.out.print("Introduce una frase: ");
      frase = entradaUsuario.readLine();
      salidaAlServidor.writeBytes(frase + '\n');
      // Leer la respuesta del servidor
      String modifiedSentence = entradaDelServidor.readLine();
      System.out.println("RESPUESTA DEL SERVIDOR: " + modifiedSentence);
    } while (frase.equals("*") == false);

    // Paso 4: Finalizar la comunicación
    entradaDelServidor.close();
    salidaAlServidor.close();
    clientSocket.close();

    System.out.println("Cliente finalizado");
  }
}
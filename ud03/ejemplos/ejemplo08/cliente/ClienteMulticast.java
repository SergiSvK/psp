import java.net.*;

/**
 * Cliente multidifusión UDP
 * Se une al grupo de multidifusión 225.0.0.1 y espera recibir mensajes de este
 * Al recibir el mensaje * finalizará el proceso
 */
public class ClienteMulticast {
  public static void main(String args[]) throws Exception {
    // Se crea el socket multicast
    int puerto = 12345;// Puerto multicast
    MulticastSocket ms = new MulticastSocket(puerto);
    
    // Nos unimos al grupo de multidifusión
    InetAddress grupo = InetAddress.getByName("225.0.0.1");
    
    ms.joinGroup(grupo);
    System.out.println("Unido al grupo de multidifusión " + grupo);
    System.out.println("Esperando mensajes de multidifusión...");

    //recepción de mensajes de multidifusión
    String msg = "";
    while (!msg.trim().equals("*")) {
      byte[] buf = new byte[1000];
      // Recibe el paquete del servidor multicast
      DatagramPacket paquete = new DatagramPacket(buf, buf.length);
      ms.receive(paquete);
      //extrae los datos del paquete recibido
      msg = new String(paquete.getData());
      System.out.println("Recibo: " + msg.trim());
    }

    //finalización
    System.out.println("Abandono el grupo de multidifusión " + grupo);
    ms.leaveGroup(grupo); // abandonamos grupo
    ms.close(); // cierra socket
    System.out.println("Socket cerrado...");
  }
}

import java.io.*;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.*;

public class ClienteTLS {
	public static void main(String[] args) throws Exception {
		String Host = "localhost"; //host remoto
		int puerto = 6000;// puerto remoto

		// claves de acceso a los almacenes
		String claveAlmacenConfianza = "SMX2019*";
		String claveAlmacen = "SMX2019*";

		// preparamos el acceso al almacén
		KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
		almacen.load(new FileInputStream("cliente.jks"), claveAlmacen.toCharArray());
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(almacen, claveAlmacen.toCharArray());

		// preparamos el acceso al almacén de confianza
		KeyStore almacenConfianza = KeyStore.getInstance(KeyStore.getDefaultType());
		almacenConfianza.load(new FileInputStream("confianzaCliente.jks"), claveAlmacenConfianza.toCharArray());
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(almacenConfianza);

		// Creación del contexto con soporte TLS
		SSLContext contextoSSL = SSLContext.getInstance("TLS");
		contextoSSL.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

		// Creación de socket TLS de cliente a partir del contexto
		SSLSocketFactory sfact = contextoSSL.getSocketFactory();
		SSLSocket Cliente = (SSLSocket) sfact.createSocket(Host, puerto);
		//Socket Cliente =  sfact.createSocket(Host, puerto); a partir de aquí puede usarse Socket

		System.out.println("PROGRAMA CLIENTE INICIADO....");

		// flujo de salida al servidor
		DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
		// envío de saludo al servidor
		flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");

		// flujo de entrada del servidor
		DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
		// espero un mensaje del servidor
		System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

		// cerrar streams y sockets
		flujoEntrada.close();
		flujoSalida.close();
		Cliente.close();
	}
}

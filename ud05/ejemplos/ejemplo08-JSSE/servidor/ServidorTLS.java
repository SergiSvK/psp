import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;

import javax.net.ssl.*;

public class ServidorTLS {
	public static void main(String[] arg) throws IOException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException, KeyManagementException {

		// puerto en el que se esperan las conexiones
		int puerto = 6000;
		// claves de acceso a los almacenes
		String claveAlmacen = "SMX2019*";
		String claveAlmacenConfianza = "SMX2019*";

		// preparamos el acceso al almacén
		KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
		almacen.load(new FileInputStream("servidor.jks"), claveAlmacen.toCharArray());
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(almacen, claveAlmacen.toCharArray());

		// preparamos el acceso al almacén de confianza
		KeyStore almacenConfianza = KeyStore.getInstance(KeyStore.getDefaultType());
		almacenConfianza.load(new FileInputStream("confianzaServidor.jks"), claveAlmacenConfianza.toCharArray());
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(almacenConfianza);

		// Creación del contexto con soporte TLS
		SSLContext contextoSSL = SSLContext.getInstance("TLS");
		contextoSSL.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

		// creación de server socket TLS para el servidor
		SSLServerSocketFactory socketFactory = contextoSSL.getServerSocketFactory();
		SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(puerto);

		// flujo de entrada del cliente
		DataInputStream flujoEntrada = null;
		// flujo de salida al cliente
		DataOutputStream flujoSalida = null;

		// el servidor esperará 4 clientes
		for (int i = 1; i < 5; i++) {
			System.out.println("Esperando al cliente " + i);

			SSLSocket clienteConectado = (SSLSocket) serverSocket.accept();

			flujoEntrada = new DataInputStream(clienteConectado.getInputStream());

			// Esperamos recibir algo del cliente
			System.out.println("Recibiendo del CLIENTE: " + i + " \n\t" + flujoEntrada.readUTF());

			// respondemos al cliente
			flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
			flujoSalida.writeUTF("Saludos al cliente del servidor");
		}

		// cerrar streams y sockets
		flujoEntrada.close();
		flujoSalida.close();
		serverSocket.close();
		
		System.out.println("Fin del proceso servidor");
	}
}
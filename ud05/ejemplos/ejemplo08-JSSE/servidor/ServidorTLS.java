import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

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
			//mostrar información del cliente conectado
			System.out.println("Información de sesión del cliente " + i);
			SSLSession session = clienteConectado.getSession();	 
			System.out.println("Host: "+session.getPeerHost());
			System.out.println("Cifrado: " + session.getCipherSuite());
			System.out.println("Protocolo: " + session.getProtocol());
			System.out.println("IDentificador:" + new BigInteger(session.getId()));
			System.out.println("Creación de la sesión: " + session.getCreationTime());
			X509Certificate certificate = (X509Certificate)session.getLocalCertificates()[0];
			System.out.println("Propietario:    "+certificate.getSubjectDN());
			System.out.println("Algoritmo:    "+certificate.getSigAlgName());
			System.out.println("Tipo:    "+certificate.getType());
			System.out.println("Emisor:    "+certificate.getIssuerDN());
			System.out.println("Número Serie: "+certificate.getSerialNumber());

			// Esperamos recibir algo del cliente
			flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
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
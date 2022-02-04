

import java.io.*;
import org.apache.commons.net.ftp.*;

public class DescargarFichero {
	public static void main(String[] args) {
		FTPClient ftpClient = new FTPClient();

		try {
			// conectar al servidor FTP
			String servidor = "localhost";
			System.out.println("Conectamos al servidor " + servidor + "...");
			ftpClient.connect(servidor);

			// Comprobamos si el intento de conexión ha sido exitoso antes de seguir
			int reply = ftpClient.getReplyCode();
			System.out.println("Código de respuesta del servidor: " + reply);
			System.out.println("Mensaje de respuesta del servidor: " + ftpClient.getReplyString());
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.err.println("El servidor FTP ha rechazado la conexión");
				System.exit(1);
			}

			ftpClient.enterLocalPassiveMode(); // modo pasivo

			// loguearnos con un usuario concreto
			String usuario = "pruebasftp";
			String clave = "SMX2019*";
			System.out.println("Nos logueamos con el usuario " + usuario + "...");
			boolean login = ftpClient.login(usuario, clave);
			if (login) {
				System.out.println("Login correcto: " + ftpClient.getReplyString());
			} else {
				System.out.println("Login Incorrecto: " + ftpClient.getReplyString());
				ftpClient.disconnect();
				System.exit(1);
			}

			// descarga de un fichero
			String directorioBase = "files";
			ftpClient.changeWorkingDirectory(directorioBase); // nos movemos al directorio files que es donde está el
																// fichero que queremos
			String ficheroServidor = "PSP_UD03_Apuntes_Programacion en red.pdf";
			String ficheroLocal = "testDescargaFTP.pdf";
			// hemos de crear un stream de salida para recibir el fichero descargado y darle
			// nombre
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ficheroLocal));
			if (ftpClient.retrieveFile(ficheroServidor, out)) {
				System.out.println("Fichero descargado correctamente: " + ftpClient.getReplyString());
			} else {
				System.out.println("No se ha podido descargar el fichero: " + ftpClient.getReplyString());
			}

			// cerrar el stream
			out.close();

			// cerrar sesión
			ftpClient.logout();

			// desconectar
			ftpClient.disconnect();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}// main

}// ..

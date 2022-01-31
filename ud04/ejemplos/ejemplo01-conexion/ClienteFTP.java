import java.io.*;
import org.apache.commons.net.ftp.*;

public class ClienteFTP {
	public static void main(String[] args) {
		FTPClient ftpClient = new FTPClient();
		String servFTP = "localhost";
		System.out.println("Nos conectamos a: " + servFTP);
		String usuario = "pruebasftp";
		String clave = "SMX2019*";
		try {
			// conectar al servidor FTP
			System.out.println("Conectamos al servidor...");
			ftpClient.connect(servFTP);
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

			//loguearnos con un usuario concreto
			System.out.println("Nos logueamos con un usuario...");
			boolean login = ftpClient.login(usuario, clave);
			if (login) {
				System.out.println("Login correcto: " + ftpClient.getReplyString());
			}
			else {
				System.out.println("Login Incorrecto: " + ftpClient.getReplyString());
				ftpClient.disconnect();
				System.exit(1);
			}

			// mostrar contenido del directorio inicial
			mostrarContenido(ftpClient);

			// cambiamos el directorio actual a files
			System.out.println("Nos movemos a otro directorio...");
			ftpClient.changeWorkingDirectory("files");
			mostrarContenido(ftpClient);

			// desconectar del servidor FTP
			boolean logout = ftpClient.logout();
			if (logout) {
				System.out.println("Logout del servidor FTP: " + ftpClient.getReplyString());
			} else {
				System.out.println("Error al hacer Logout: " + ftpClient.getReplyString());
			}
			ftpClient.disconnect();
			System.out.println("Desconectado...");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static void mostrarContenido(FTPClient ftpClient) throws IOException {
		System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());
			FTPFile[] files = ftpClient.listFiles();
			System.out.println("Ficheros en el directorio actual:" + files.length);
			// array para visualizar el tipo de fichero
			String tipos[] = { "Fichero", "Directorio", "Enlace simb." };
			for (int i = 0; i < files.length; i++) {
				System.out.println("\t" + files[i].getName() + " => "
						+ tipos[files[i].getType()]);
			}

	}
}

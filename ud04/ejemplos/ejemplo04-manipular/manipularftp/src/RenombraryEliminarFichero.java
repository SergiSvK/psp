import java.io.*;
import org.apache.commons.net.ftp.*;

public class RenombraryEliminarFichero {
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

			String directorioBase = "files";
			ftpClient.changeWorkingDirectory(directorioBase); // nos movemos al directorio files que es donde está el

			// renombrar un fichero
			String ficheroRenombrar = "PSP_UD01_Apuntes_Programacion multiproceso.pdf";
			String nuevoNombreFichero = "FicheroRenombrado.pdf";

			// Renombrar fichero
			if (ftpClient.rename(ficheroRenombrar, nuevoNombreFichero)) {
				System.out.println("Fichero renombrado: " + ftpClient.getReplyString());
			} else {
				System.out.println("No se ha podido renombar el Fichero: " + ftpClient.getReplyString());
			}

			System.out.println("\nDespues de renombrar el fichero: ");
			verFicheros(ftpClient);

			// eliminar un fichero
			String ficheroBorrar = "PSP_UD02_Apuntes_Programacion multihilo.pdf";
			if (ftpClient.deleteFile(ficheroBorrar)) {
				System.out.println("Fichero eliminado: " + ftpClient.getReplyString());
			} else {
				System.out.println("No se ha podido eliminar Fichero: " + ftpClient.getReplyString());
			}

			System.out.println("\nDespues de eliminar el fichero: ");
			verFicheros(ftpClient);

			// cerrar sesión
			ftpClient.logout();

			// desconectar
			ftpClient.disconnect();

		} catch (

		IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Muestra el contenido del directorio actual en la conexión FTP client indicada
	 * 
	 * @param client
	 * @throws IOException
	 */
	private static void verFicheros(FTPClient client) throws IOException {

		FTPFile[] files = client.listFiles(client.printWorkingDirectory());
		System.out.println("Ficheros en el directorio actual: " + files.length);

		String tipos[] = { "Fichero", "Directorio" };

		for (int i = 0; i < files.length; i++) {
			System.out.println("-" + files[i].getName() + " (" + tipos[files[i].getType()] + ")");
		}
	}
}

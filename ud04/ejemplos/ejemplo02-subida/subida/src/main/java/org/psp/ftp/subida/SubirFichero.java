import java.io.*;
import org.apache.commons.net.ftp.*;

public class SubirFichero {
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
			System.out.println("Nos logueamos con el usuario " + usuario +"...");
			boolean login = ftpClient.login(usuario, clave);
			if (login) {
				System.out.println("Login correcto: " + ftpClient.getReplyString());
			}
			else {
				System.out.println("Login Incorrecto: " + ftpClient.getReplyString());
				ftpClient.disconnect();
				System.exit(1);
			}

			// si no existe el directorio de subidas, lo creamos
			System.out.println("Si no existe el directorio de subidas, lo creamos...");
			String directorioBase = "files";
			ftpClient.changeWorkingDirectory(directorioBase); //nos movemos a files que es donde tiene permisos de escritura
			System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());
			String directorio = "subidas";
			if (!ftpClient.changeWorkingDirectory(directorio)) {
				if (ftpClient.makeDirectory(directorio)) {
					System.out.println("Directorio :  " + directorio + " creado: -" + ftpClient.getReplyString());					
				} else {
					System.out.println("No se ha podido crear el directorio de subidas: " + ftpClient.getReplyString());
					System.exit(0);
				}
			}

			// subimos el fichero
			System.out.println("Subimos el fichero...");
			ftpClient.changeWorkingDirectory(directorio);
			System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			String archivo = "test.odt";
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

			if (ftpClient.storeFile(archivo, in)) {
				System.out.println("Fichero " + archivo +  " subido correctamente: " + ftpClient.getReplyString());
			} else {
				System.out.println("No se ha podido subir el fichero " + archivo +": " + ftpClient.getReplyString());
			}
			
			//cerrar el stream
			in.close();

			// cerrar sesión
			ftpClient.logout();

			// desconectar
			ftpClient.disconnect();
		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

public class ObtenerResumen {
	public static void main(String args[]) {
		try {
			//cargar el documento del que obtener el resumen
			byte dataBytes[] = Files.readAllBytes(Paths.get("documento.txt"));
			//generar el resumen
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(dataBytes);// TEXTO A RESUMIR
			byte resumen[] = md.digest(); // SE CALCULA EL RESUMEN
			
			//se escribe el resumen en disco
			FileOutputStream fileout = new FileOutputStream("resumen.dat");
			fileout.write(resumen);
			fileout.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
	}
}
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class ComprobarResumen {
	public static void main(String args[]) {
		try {
			
			//cargar el resumen
			byte resumenOriginal[] = Files.readAllBytes(Paths.get("resumen.dat"));

			//volver a calcular el resumen del documento
			byte dataBytes[] = Files.readAllBytes(Paths.get("documento.txt"));
			//generar el resumen
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(dataBytes); //introducir texto a resumir
			byte resumenActual[] = md.digest(); // calcular el resumen

			//validación de integridad
			if (MessageDigest.isEqual(resumenActual, resumenOriginal)) {
				System.out.println("Datos válidos");
			}
			else {
				System.out.println("Los datos han sido alterados");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

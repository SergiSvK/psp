import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
 * Generador de claves seguras para AES
 */
public class GeneradorClavesAES {

	/**
	 * Genera una clave para segura para el algoritmo AES y la vuelca en el fichero  
	 * indicado que tendrá formato binario
	 * 
	 * @param nombreFichero nombre del fichero que contendrá la clave
	 */
	public static void generarClave(String nombreFichero) {
		try {
			// generación de clave de 256 bits para AES
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = new SecureRandom();
			int keyBitSize = 256;
			keyGenerator.init(keyBitSize, secureRandom);
			SecretKey clave = keyGenerator.generateKey();
			// volcado en fichero
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreFichero));
			out.writeObject(clave);
			out.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

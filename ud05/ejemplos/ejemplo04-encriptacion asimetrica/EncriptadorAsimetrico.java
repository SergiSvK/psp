import java.security.*;
import javax.crypto.*;

/**
 * Encriptación de textos usando criptografía asimétrica
 */
public class EncriptadorAsimetrico {

	/**
	 * Encripta el texto usando criptografía asimétrica
	 * 
	 * @param texto        texto a encriptar
	 * @param clavePublica clave de encriptación
	 * @return texto encriptado
	 * @throws GeneralSecurityException
	 */
	public static byte[] Encriptar(byte[] texto, PublicKey clavePublica) throws GeneralSecurityException {
		Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		c.init(Cipher.ENCRYPT_MODE, clavePublica);

		return c.doFinal(texto);
	}

	/**
	 * Desencripta el texto usando criptografía asimétrica
	 * @param textoCifrado texto a deseencriptar
	 * @param clavePrivada clave de descifrado
	 * @return texto desencriptado
	 * @throws GeneralSecurityException
	 */
	public static byte[] Desencriptar(byte[] textoCifrado, PrivateKey clavePrivada) throws GeneralSecurityException {
		Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		c.init(Cipher.DECRYPT_MODE, clavePrivada);

		return c.doFinal(textoCifrado);
	}
}
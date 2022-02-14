import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encriptación y desencriptación simple usando AES
 */
public class EncriptadorSimetrico {   
        
    /**
     * Encripta el texto usando la clave indicada
     * @param texto texto a encriptar
     * @param clave clave de encriptación
     * @return texto encriptado
     * @throws GeneralSecurityException
     */
    public static byte[] Encriptar(byte[] texto,byte[] clave) throws GeneralSecurityException {

        //validación de la longitud de la clave
        if (clave.length != 16) {
            throw new IllegalArgumentException("Longitud de clave inválida");
        }
        //selección del algoritmo a usar, en este caso el AES de criptografía simétrica
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //definición de la clave e inicialización,  el IvParameterSpec es el vector de inicialización
        SecretKeySpec key = new SecretKeySpec(clave, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        //encriptación del texto suministrado
        return cipher.doFinal(texto);
    }

    /**
     * Desencripta el texto usando la clave indicada
     * @param textoEncriptado texto a desencriptar
     * @param clave clave de encriptación
     * @return texto desencriptado
     * @throws GeneralSecurityException
     */
    public static byte[] Desencriptar(byte[] textoEncriptado,byte[] clave) throws GeneralSecurityException {

        //validación de la longitud de la clave
        if (clave.length != 16) {
            throw new IllegalArgumentException("Longitud de clave inválida");
        }
        //selección del algoritmo a usar, en este caso el AES de criptografía simétrica
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //definición de la clave e inicialización
        SecretKeySpec key = new SecretKeySpec(clave, "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        //desencriptación del texto suministrado
        return cipher.doFinal(textoEncriptado);
    }

    
}
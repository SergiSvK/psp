import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncriptacionSimple {
    
    public static byte[] Encriptar(String textoUTF8,byte[] clave) throws GeneralSecurityException, UnsupportedEncodingException {

        return Encriptar(textoUTF8.getBytes("UTF-8"),clave);
    }    

    public static byte[] Desencriptar(String textoEncriptado,byte[] clave) throws GeneralSecurityException, UnsupportedEncodingException {

        return Desencriptar(textoEncriptado.getBytes("UTF-8"),clave);
    }
    
    public static byte[] Encriptar(byte[] texto,byte[] clave) throws GeneralSecurityException {

        //selección del algoritmo a usar, en este caso el AES de criptografía simétrica
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //definición de la clave e inicialización
        SecretKeySpec key = new SecretKeySpec(clave, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        //encriptación del texto suministrado
        return cipher.doFinal(texto);
    }

    public static byte[] Desencriptar(byte[] textoEncriptado,byte[] clave) throws GeneralSecurityException {

        //selección del algoritmo a usar, en este caso el AES de criptografía simétrica
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //definición de la clave e inicialización
        SecretKeySpec key = new SecretKeySpec(clave, "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        //desencriptación del texto suministrado
        return cipher.doFinal(textoEncriptado);
    }
/*
    public static String EncriptarATexto(String textoUTF8, byte[] keyBytes ) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        
        byte[] cipherText = Encriptar(textoUTF8,keyBytes);

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String DesencriptarTexto(String textoBase64, byte[] keyBytes ) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        
        byte[] decodedBytes = Base64.getDecoder().decode(textoBase64);

        byte[] cipherText = Desencriptar(decodedBytes.toString(),keyBytes);

        return Base64.getEncoder().encodeToString(cipherText);
    }
    */
}
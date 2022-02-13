import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;

/**
 * Encriptación y desencriptación de ficheros simple usando AES
 */
public class EncriptadorFicheros {

    // extensión a añadir cuando no se especifica un nombre de fichero
    private static final String extension = ".cifrado";

    /**
     * Encripta el fichero suministrado usando el fichero con la clave indicada, el
     * fichero encriptado tendrá el mismo nombre y extensión .cifrado
     * 
     * @param nombreFichero      nombre del fichero a encriptar
     * @param nombreFicheroClave nombre del fichero que contiene la clave de
     *                           encriptación
     * @throws ClassNotFoundException
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static void Encriptar(String nombreFichero, String nombreFicheroClave)
            throws ClassNotFoundException, GeneralSecurityException, IOException {
        Encriptar(nombreFichero, nombreFicheroClave, nombreFichero + extension);
    }

    /**
     * Encripta el fichero suministrado usando el fichero con la clave indicada
     * 
     * @param nombreFichero           nombre del fichero a encriptar
     * @param nombreFicheroClave      nombre del fichero que contiene la clave de
     *                                encriptación
     * @param nombreFicheroEncriptado nombre del fichero encriptado a generar
     * @throws GeneralSecurityException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void Encriptar(String nombreFichero, String nombreFicheroClave, String nombreFicheroEncriptado)
            throws GeneralSecurityException, IOException, ClassNotFoundException {

        // recuperar la clave secreta del fichero
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(nombreFicheroClave));
        Key key = (Key) oin.readObject();
        oin.close();

        // selección del algoritmo a usar, en este caso el AES de criptografía simétrica
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        // encriptación del fichero suministrado
        FileInputStream fileIn = new FileInputStream(nombreFichero);
        CipherOutputStream cipherOut = new CipherOutputStream(new FileOutputStream(nombreFicheroEncriptado), cipher);
        byte[] buffer = new byte[cipher.getBlockSize()];
        // leemos bloques de bytes del fichero y los vamos volcando a la salida
        // encriptada
        int i = fileIn.read(buffer);
        while (i != -1) {
            cipherOut.write(buffer, 0, i);
            i = fileIn.read(buffer);
        }
        cipherOut.flush();
        cipherOut.close();
        fileIn.close();
    }

    /**
     * Desencripta el fichero indicado con la clave suministrada, el fichero
     * desencriptado tendrá el mismo nombre pero sin la extensión .cifrado
     * 
     * @param nombreFicheroEncriptado nombre del fichero encriptado
     * @param nombreFicheroClave      nombre del fichero que contiene la clave de
     *                                encriptación
     * @throws GeneralSecurityException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void Desencriptar(String nombreFicheroEncriptado, String nombreFicheroClave)
            throws ClassNotFoundException, GeneralSecurityException, IOException {
        Desencriptar(nombreFicheroEncriptado, nombreFicheroClave, nombreFicheroEncriptado.replace(extension, ""));
    }

    /**
     * Desencripta el fichero indicado con la clave suministrada
     * 
     * @param nombreFicheroEncriptado nombre del fichero encriptado
     * @param nombreFicheroClave      nombre del fichero que contiene la clave de
     *                                encriptación
     * @param nombreFichero           nombre del fichero resultante
     * @throws GeneralSecurityException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void Desencriptar(String nombreFicheroEncriptado, String nombreFicheroClave, String nombreFichero)
            throws GeneralSecurityException, IOException, ClassNotFoundException {

        // recuperar la clave secreta del fichero
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(nombreFicheroClave));
        Key key = (Key) oin.readObject();
        oin.close();

        // selección del algoritmo a usar, en este caso el AES de criptografía simétrica
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        // desencriptación del fichero suministrado
        CipherInputStream cipherIn = new CipherInputStream(new FileInputStream(nombreFicheroEncriptado), cipher);
        FileOutputStream fileOut = new FileOutputStream(nombreFichero);
        byte[] buffer = new byte[cipher.getBlockSize()];
        // leemos bloques de bytes del fichero encriptado y los vamos volcando a la
        // salida
        int i = cipherIn.read(buffer);
        while (i != -1) {
            fileOut.write(buffer, 0, i);
            i = cipherIn.read(buffer);
        }

        fileOut.flush();
        fileOut.close();
        cipherIn.close();
    }
}
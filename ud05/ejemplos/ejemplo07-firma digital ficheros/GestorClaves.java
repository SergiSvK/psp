import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Gestor de pares de claves de criptografía asimétrica
 */
public class GestorClaves {

    private String algoritmo;
    
    private String ficheroClavePrivada;
    private String ficheroClavePublica;

    private PrivateKey clavePrivada;
    private PublicKey clavePublica;

    /**
     * Constructor
     * @param algoritmo nombre del algoritmo a usar, por ejemplo RSA o DSA
     */
    public GestorClaves(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    /**
     * Inicializa el gestor con los nombres de ficheros de claves indicados, si no existen los genera automáticamente
     * @param ficheroClavePrivada
     * @param ficheroClavePublica
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public void init(String ficheroClavePrivada, String ficheroClavePublica) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        this.ficheroClavePrivada = ficheroClavePrivada;
        this.ficheroClavePublica = ficheroClavePublica;
        
        //si las claves existen se cargan de disco, si no se generan y almacenan en disco
        File file = new File(ficheroClavePrivada);

        if (file.exists()) {            
            cargarClaves();
        } else {
            crearClaves();
            guardarClaves();
        }
    }

    public PrivateKey getClavePrivada() {
        return clavePrivada;
    }

    public PublicKey getClavePublica() {
        return clavePublica;
    }

    /**
     * Carga las claves de los ficheros
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private void cargarClaves() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Lectura del fichero que contiene la clave privada
        FileInputStream privada = new FileInputStream(ficheroClavePrivada);
        byte[] bufferPrivada = new byte[privada.available()];
        privada.read(bufferPrivada);// lectura de bytes
        privada.close();

        //Construya la clave privada partiendo de datos codificados en PKCS8
        PKCS8EncodedKeySpec clavePrivadaPKCS8 = new PKCS8EncodedKeySpec(bufferPrivada);
        KeyFactory keyDSA = KeyFactory.getInstance(algoritmo);
        clavePrivada = keyDSA.generatePrivate(clavePrivadaPKCS8);

        // Lectura del fichero que contiene la clave pública
        FileInputStream publica = new FileInputStream(ficheroClavePublica);
        byte[] bufferPublica = new byte[publica.available()];
        publica.read(bufferPublica);// lectura de bytes
        publica.close();

        //Construya la clave pública partiendo de datos codificados en X509
        X509EncodedKeySpec clavePublicaX509 = new X509EncodedKeySpec(bufferPublica);
        clavePublica = keyDSA.generatePublic(clavePublicaX509);
    }

    /**
     * Vuelca a disco las claves
     * @throws IOException
     */
    private void guardarClaves() throws IOException {

        // guardar la clave privada en codificación PKCS8
        PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(clavePrivada.getEncoded());
        FileOutputStream privada = new FileOutputStream(ficheroClavePrivada);
        privada.write(pkcs8.getEncoded());
        privada.close();

        // guardar la clave publica en codificación X509
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(clavePublica.getEncoded());
        FileOutputStream publica = new FileOutputStream(ficheroClavePublica);
        publica.write(x509.getEncoded());
        publica.close();

    }

    /**
     * Crea el par de claves (pública y privada)
     */
    private void crearClaves() {
        
        System.out.println("Generando par de claves...");
        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance(algoritmo);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        keyGen.initialize(1024);
        KeyPair par = keyGen.generateKeyPair();
        clavePrivada = par.getPrivate();
        clavePublica = par.getPublic();
    }

   
}

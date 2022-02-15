import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Parte emisora en la verificación de ficheros firmados
 */
public class Emisor {
    /**
     * Firma el fichero indicado, generando la firma en un documento aparte
     * @param fichero fichero a firmar
     * @param clavePrivada clave privada
     * @param ficheroFirma fichero que contendrá la firma generada
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws IOException
     */
    public void FirmarFichero(String fichero, PrivateKey clavePrivada, String ficheroFirma)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
        // Firmamos el mensaje con la clave privada
        Signature firmador = Signature.getInstance("SHA256withDSA");
        // inicialización para firmar
        firmador.initSign(clavePrivada);

        // carga del fichero para firmar        
        BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream(fichero));
        byte[] buffer = new byte[bufferedInput.available()];
        int len;
        while ((len = bufferedInput.read(buffer)) >= 0) {
            firmador.update(buffer, 0, len);
        }
        bufferedInput.close();

        // genera la firma del fichero
        byte[] firma = firmador.sign();

        // almacenamos la firma en otro fichero con la extensión .firma
        FileOutputStream fos = new FileOutputStream(ficheroFirma);
        fos.write(firma);
        fos.close();
    }
}

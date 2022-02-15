import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Parte receptora en la verificación de ficheros firmados
 */
public class Receptor {
    /**
     * Verifica la firma del fichero recibido
     * @param ficheroRecibido fichero recibido
     * @param clavePublica clave pública del emisor
     * @param ficheroFirma fichero que contiene la firma del fichero recibido
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws IOException
     */
    public boolean VerificarFirma(String ficheroRecibido, PublicKey clavePublica,String ficheroFirma)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {

        // Verificamos el mensaje con la clave pública
        Signature firmador = Signature.getInstance("SHA256withDSA");
        // inicialización para verificar
        firmador.initVerify(clavePublica);
        // carga del fichero firmado
        BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream(ficheroRecibido));
        byte[] buffer = new byte[bufferedInput.available()];
        int len;
        while ((len = bufferedInput.read(buffer)) >= 0) {
            firmador.update(buffer, 0, len);
        }
        bufferedInput.close();

        // verificación del fichero contra la firma
        // primero hemos de cargar el fichero que tiene la firma
        FileInputStream firmafic = new FileInputStream(ficheroFirma);
        byte[] firma = new byte[firmafic.available()];
        firmafic.read(firma);
        firmafic.close();
        //verificación
        return firmador.verify(firma);
    }
}

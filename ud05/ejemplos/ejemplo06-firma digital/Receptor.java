import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Receptor del mensaje y verificador de su firma
 */
public class Receptor {
    /**
     * Verifica la firma del mensaje recibido con la clave pública del emisor
     * @param mensajeRecibido mensaje recibido del emisor
     * @param clavePublica clave pública del emisor
     * @param firma firma del mensaje recibido
     * @return verdadero si la firma es válida y falso en otro caso
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public boolean VerificarFirma(String mensajeRecibido, PublicKey clavePublica, byte[] firma)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Verificamos el mensaje con la clave publica
        Signature firmador = Signature.getInstance("SHA256withDSA");
        // inicialización para verificar la firma
        firmador.initVerify(clavePublica);
        // carga del mensaje para firmar
        firmador.update(mensajeRecibido.getBytes());
        // verificación
        return firmador.verify(firma);
    }
}

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Emisor del mensaje firmado
 */
public class Emisor {
    /**
     * Firma el mensaje indicado con la clave pública
     * @param mensaje mensaje a firmar
     * @param clavePrivada clave privada del emisor
     * @return firma generada 
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public byte[] FirmarMensaje(String mensaje, PrivateKey clavePrivada)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Firmamos el mensaje con la clave privada
        Signature firmador = Signature.getInstance("SHA256withDSA");
        // inicialización para firmar
        firmador.initSign(clavePrivada);
        // carga del mensaje para firmar
        firmador.update(mensaje.getBytes());
        // firmado
        return firmador.sign();
    }
}

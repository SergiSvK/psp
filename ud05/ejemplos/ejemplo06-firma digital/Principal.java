import java.security.*;

/**
 * Ejemplo de firmado y de verificación de un texto con generación de par de claves en el momento
 */
public class Principal {
	public static void main(String[] args) {
		try {
			// Creamos un par de claves para la ocasión
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
			// inicializar el generador de claves para 2048 bits
			keyGen.initialize(2048, SecureRandom.getInstance("SHA1PRNG"));
			// generar el par de claves
			KeyPair keyPair = keyGen.generateKeyPair();

			// El emisor firma el mensaje con su clave privada
			String mensaje = "Este mensaje va a ser firmado";
			Emisor emisor = new Emisor();
			byte[] firma = emisor.FirmarMensaje(mensaje, keyPair.getPrivate());

			//El emisor le pasa su clave publica al receptor, el mensaje y la firma
			String mensajeRecibido = mensaje + "kk";
			//mensajeRecibido += "esto es para que no se verifique";
			Receptor receptor = new Receptor();
			boolean check = receptor.VerificarFirma(mensajeRecibido, keyPair.getPublic(), firma);

			if (check) {
				System.out.println("FIRMA VÁLIDA");
			} else {
				System.out.println("FIRMA NO VÁLIDA");
			}

		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}

}

import java.io.IOException;
import java.security.*;

public class Principal {
	public static void main(String[] args) {
		try {
			// usamos el gestor de claves para obtener el par de claves
			GestorClaves gestorClavesEmisor = new GestorClaves("DSA");
			try {
				gestorClavesEmisor.init("ruben.pri", "ruben.pub");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}

			// El emisor firma el fichero con su clave privada
			String fichero = "test.odt";
			String ficheroFirma = fichero + ".firmado";
			Emisor emisor = new Emisor();
			emisor.FirmarFichero(fichero, gestorClavesEmisor.getClavePrivada(),ficheroFirma);

			//El receptor verifica fichero y su firma con la clave pública del emisor
			Receptor receptor = new Receptor();
			boolean check = receptor.VerificarFirma(fichero, gestorClavesEmisor.getClavePublica(),ficheroFirma);

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

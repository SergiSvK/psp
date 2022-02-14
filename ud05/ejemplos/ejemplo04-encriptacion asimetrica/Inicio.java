import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Inicio {

    public static void main(String[] args) {

        // se crea el par de claves (pública y privada)
        GestorClaves gestorClaves = new GestorClaves();
        try {
            gestorClaves.init("ruben.pri", "ruben.pub");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        PrivateKey clavePrivada = gestorClaves.getClavePrivada();
        PublicKey clavePublica = gestorClaves.getClavePublica();

        // encriptación
        System.out.print("Introduce el texto a encriptar: ");
        Scanner scanner = new Scanner(System.in);
        String texto = scanner.nextLine();
        scanner.close();

        byte[] textoEncriptado = new byte[] { 0 };
        try {
            textoEncriptado = EncriptadorAsimetrico.Encriptar(texto.getBytes("UTF-8"), clavePublica);
            System.out.print("El texto encriptado es: ");
            System.out.println(new String(textoEncriptado, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // desencriptación
        try {
            byte[] textoDesencriptado = EncriptadorAsimetrico.Desencriptar(textoEncriptado, clavePrivada);
            System.out.println("El texto desencriptado es: " + new String(textoDesencriptado, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

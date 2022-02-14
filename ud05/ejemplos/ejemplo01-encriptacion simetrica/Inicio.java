import java.security.Provider;
import java.security.Security;
import java.util.Scanner;

public class Inicio {

    public static void main(String[] args) {

        System.out.println("Proveedores de seguridad disponibles:");
        // ver que proveedores de seguiridad tenemos disponibles
        for (Provider provider : Security.getProviders()) {
            System.out.println("- " + provider.getName() + ": " + provider.getInfo());
        }

        System.out.print("Introduce el texto a encriptar: ");
        Scanner scanner = new Scanner(System.in);
        String texto = scanner.nextLine();

        // clave a usar
        byte[] keyBytes = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        byte[] textoEncriptado = new byte[] { 0 };
        try {
            textoEncriptado = EncriptadorSimetrico.Encriptar(texto.getBytes("UTF-8"), keyBytes);
            System.out.println("El texto encriptado es: " + new String(textoEncriptado, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
        try {
            byte[] textoDesencriptado = EncriptadorSimetrico.Desencriptar(textoEncriptado, keyBytes);
            System.out.println("El texto desencriptado es: " + new String(textoDesencriptado, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

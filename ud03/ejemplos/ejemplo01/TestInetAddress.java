import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
    public static void main(String[] args) {
        try {
            // Obtenemos el objeto InetAddress y lo probamos con www.google.es
            InetAddress host = InetAddress.getByName("www.google.es");
            // Obtenemos el Host
            System.out.println("Host: " + host);
            // Obtenemos la IP
            System.out.println("IP: " + host.getHostAddress());
            // Obtenemos el nombre
            System.out.println("Nombre: " + host.getHostName());            
            // Obtenemos el FQDN (Fully Qualified Domain Name)
            System.out.println("FQDN: " + host.getCanonicalHostName());

        } catch (UnknownHostException uhe) {
            System.out.println("Host Exception");
            System.out.println(uhe.toString());
        }
    }
}
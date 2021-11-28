import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddressByIP {
    public static void main(String[] args) {
        try {
            // Obtenemos el objeto InetAddress y lo probamos con www.google.es
            InetAddress host = InetAddress.getByAddress(new byte[] {(byte)142,(byte)250,(byte)201,(byte)67});
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
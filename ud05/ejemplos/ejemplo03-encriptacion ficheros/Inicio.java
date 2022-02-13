
public class Inicio {

    public static void main(String[] args) {
        try {
            //encriptar un fichero
            EncriptadorFicheros.Encriptar("test.pdf", "Clave.secreta");
            //desencriptar un fichero
            EncriptadorFicheros.Desencriptar("test.pdf.cifrado", "Clave.secreta","desencriptado.pdf");
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}

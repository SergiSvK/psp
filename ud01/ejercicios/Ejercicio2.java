import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio2 {
   public static void main(String[] args)  {	   
	
	try {

		ProcessBuilder pb = new ProcessBuilder("whoami","/all");
		Process p = pb.start();	
	   
		InputStream is = p.getInputStream();

		// mostramos en pantalla la salida del comando línea a línea
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		System.out.println("Esta es la salida del comando:");
		String linea;
		while ((linea = br.readLine()) != null)	{		 
			System.out.println(linea);
		}
		br.close();	
	
	} catch (Exception e) {
		e.printStackTrace();
	}
   }
}


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

public class EjemploDirectorio {
	public static void main(String[] args) throws IOException {

		ProcessBuilder pb = new ProcessBuilder();
        //establecemos el comando a ejecutar
		pb.command("cmd.exe", "/c", "dir");		
		//establecemos que se ejecute en la carpeta users
		pb.directory(new File("C:\\users"));
		
		Process p = pb.start();		
		try {	
			
			//obtenemos un stream de entrada que nos permite leer lo que el comando ha escrito en su stream de salida
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

		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int valorSalida;
		try {
			valorSalida = p.waitFor(); //esperamos a que finalice el proceso
			System.out.println("El valor de Salida del proceso es: " + valorSalida);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

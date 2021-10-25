import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.InputStream;

public class Ejercicio5 {
	public static void main(String[] args)  {

		try {	
			ProcessBuilder pb = new ProcessBuilder();
        	//establecemos el comando a ejecutar
			pb.command("cmd.exe", "/c", "dir");		
		
			//validamos que la entrada tenga el número de argumentos adecuado
			if (args.length != 1) {
				System.out.println("Indique el directorio a usar como argumento");
				return;
			}
			pb.directory(new File(args[0]));
			
			Process p = pb.start();		
		
			
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

			//obtenemos el valor de salida del comando		
			int valorSalida = p.waitFor(); //esperamos a que finalice el proceso
			System.out.println("El valor de Salida del proceso es: " + valorSalida);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

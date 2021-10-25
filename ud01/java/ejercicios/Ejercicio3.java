import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio3 {
	public static void main(String[] args)  {
			
		try {			

			//lanzamos el comando java -version
			Process p = new ProcessBuilder("java", "-version").start();
			
			//obtenemos un stream de entrada que nos permite leer lo que el comando ha escrito en su stream de salida
			InputStream is = p.getErrorStream();

			// mostramos en pantalla la salida del comando línea a línea
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			System.out.println("Esta es la salida del comando:");
			String linea;
			while ((linea = br.readLine()) != null)	{		 
				System.out.println(linea);
			}
			br.close();
		
			//obtención del valor de salida del comando
			int valorSalida = p.waitFor(); //esperamos a que finalice el proceso
			System.out.println("El valor de Salida del proceso es: " + valorSalida);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

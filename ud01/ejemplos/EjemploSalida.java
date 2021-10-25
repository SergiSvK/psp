import java.io.*;

public class EjemploSalida {
	public static void main(String[] args) throws IOException {

		//lanzamos el comando CMD /C DIR
		Process p = new ProcessBuilder("CMD", "/C", "DIR").start();
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
			valorSalida = p.waitFor(); //esperamos a que finalice el proceso y recogemos el valor de salida que devolverá con System.exit()
			System.out.println("El valor de Salida del proceso es: " + valorSalida);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

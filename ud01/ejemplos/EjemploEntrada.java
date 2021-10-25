import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class EjemploEntrada {
	public static void main(String[] args) throws IOException {
		Process p = new ProcessBuilder("CMD", "/C", "DATE").start();

		// escritura -- envia entrada a DATE
		OutputStream os = p.getOutputStream();
		os.write("01-12-17".getBytes());
		os.flush(); // vacía el buffer de salida

		
		// mostramos en pantalla la salida del comando línea a línea
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		System.out.println("Esta es la salida del comando:");
		String linea;
		while ((linea = br.readLine()) != null)	{		 
			System.out.println(linea);
		}
		br.close();
		 
		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
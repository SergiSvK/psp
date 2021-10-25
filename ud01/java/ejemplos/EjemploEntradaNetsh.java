import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;

public class EjemploEntradaNetsh {
	
	private static void mostrarSalida(BufferedReader br) throws IOException {		
		System.out.println("Esta es la salida del comando:");
		String linea;
		while ((linea = br.readLine()) != null)	{		 
			System.out.println(linea);
		}		
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Process p = new ProcessBuilder("cmd","/c","netsh").start();

		// escritura -- envia interface
		OutputStream os = p.getOutputStream();
		os.write("interface".getBytes());
		os.flush(); // vacía el buffer de salida
		
		Thread.sleep(2000);

		// lectura -- obtiene la salida de DATE
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		mostrarSalida(br);
		
		Thread.sleep(2000);
		
		os.write("ip".getBytes());
		os.flush(); // vacía el buffer de salida
		
		Thread.sleep(2000);
		mostrarSalida(br);
		
		 
		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			br.close();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
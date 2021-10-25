import java.io.IOException;
import java.io.OutputStream;

public class Ejercicio7 {
	public static void main(String[] args) throws IOException {
		
		try {
			Process p = new ProcessBuilder("CMD", "/C", "msg Ruben").start();

			// escritura -- envia entrada a DATE
			OutputStream os = p.getOutputStream();
			os.write("hola\n".getBytes());
			os.flush(); // vacía el buffer de salida

			os.write("\u001A".getBytes());
			os.flush(); // vacía el buffer de salida		
		
			// COMPROBACION DE ERROR - 0 bien - <> 0 mal
			int exitVal;
		
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
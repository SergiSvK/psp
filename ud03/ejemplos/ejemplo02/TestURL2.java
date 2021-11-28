import java.net.*;
import java.io.*;

public class TestURL2 {
	/**
	 * Crea un objeto URL y mediante un ImputReader descarga el contenido HTML de la
	 * página
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("https://aules.edu.gva.es");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		BufferedReader in;
		try {
			InputStream inputStream = url.openStream();
			in = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}//
}// Ejemplo2URL
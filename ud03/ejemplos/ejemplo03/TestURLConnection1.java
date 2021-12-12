import java.net.*;
import java.io.*;

public class TestURLConnection1 {
	/**
	 * Crea un objeto URL al que abre una conexión y mediante un BufferedReader
	 * descarga el contenido HTML de la página
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		URL url = null;
		URLConnection urlCon = null;
		try {
			url = new URL("https://aules.edu.gva.es");
			urlCon = url.openConnection();

			InputStream inputStream = urlCon.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				System.out.println(inputLine);
			}

			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//
}// Ejemplo1urlCon

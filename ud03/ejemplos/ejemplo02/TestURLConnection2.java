import java.net.*;
import java.util.*;

/**
 * Recupera información de la cabecera de la respuesta de una conexión a una url
 */
public class TestURLConnection3 {
   public static void main(String[] args) throws Exception {

      URL url = new URL("https://aules.edu.gva.es");
      URLConnection conexion = url.openConnection();

      System.out.println("Direccion [getURL()]:" + conexion.getURL());

      Date fecha = new Date(conexion.getLastModified());
      System.out.println("Fecha ultima modificacion [getLastModified()]: " + fecha);
      System.out.println("Tipo de Contenido [getContentType()]: " + conexion.getContentType());

      System.out.println("============================================ ");
      System.out.println("TODOS LOS CAMPOS DE CABECERA CON getHeaderFields(): ");

      // USAMOS UNA ESTRUCTURA Map PARA RECUPERAR CABECERAS
      Map<String, List<String>> camposcabecera = conexion.getHeaderFields();
      for (Map.Entry<String, List<String>> map : camposcabecera.entrySet()) {
         System.out.println(map.getKey() + " : " + map.getValue());
      }

      System.out.println("============================================ ");
      System.out.println("CAMPOS 1 Y 4 DE CABECERA:");
      System.out.println("getHeaderField(1)=> " + conexion.getHeaderField(1));
      System.out.println("getHeaderField(4)=> " + conexion.getHeaderField(4));
      System.out.println("============================================");

      System.out.println("============================================ ");
      System.out.println("CAMPOS Date Y Keep-Alive DE CABECERA:");
      System.out.println("getHeaderField(\"Date\")=> " + conexion.getHeaderField("Date"));
      System.out.println("getHeaderField(\"Keep-Alive\")=> " + conexion.getHeaderField("Keep-Alive"));
      System.out.println("============================================");

   

   }
}//

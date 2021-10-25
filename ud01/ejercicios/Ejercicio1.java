import java.io.IOException;

public class Ejercicio1 {
   public static void main(String[] args) throws IOException  {	   
	   try {
         Process p = new ProcessBuilder("whoami","/all").start();
      } catch (Exception e) {
		   e.printStackTrace();
	   }
   }
}


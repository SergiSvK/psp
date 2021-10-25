import java.io.File;

public class Ejercicio8 {
   public static void main(String[] args)  {	   	   
	   
	   try {
		   
			ProcessBuilder pb = new ProcessBuilder("cmd","/C","whoami","/all");   	
			File fOut = new File("salida8.txt");
			File fErr = new File("error.txt");   
			
			pb.redirectOutput(fOut);
			pb.redirectError(fErr);
			pb.start();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    

   }
}


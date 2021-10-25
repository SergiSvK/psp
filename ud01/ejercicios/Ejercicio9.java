import java.io.File;

public class Ejercicio9 {
    public static void main(String[] args)  {	   	   
	   
        try {
            
             ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c","java","Ejercicio10"); 	
             
             File fIn = new File("directorio.txt");
             File fOut = new File("salida.txt");
             File fErr = new File("error.txt");   
             
             pb.redirectInput(fIn);
             pb.redirectOutput(fOut);
             pb.redirectError(fErr);
             pb.start();			
           
             
         } catch (Exception e) {
             e.printStackTrace();
         }
         
 
    }

}
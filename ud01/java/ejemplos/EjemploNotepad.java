import java.io.IOException;

public class EjemploNotepad {
   public static void main(String[] args) throws IOException  {	   
	   ProcessBuilder pb = new ProcessBuilder("NOTEPAD");
	   Process p = pb.start();

   }
}


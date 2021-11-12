import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        java.util.Scanner scanner = new Scanner(System.in);
        
        MiHilo hilo = new MiHilo();

        String orden;
        do {           
            orden = scanner.nextLine();
            //con la primera entrada del usuario arrancamos el hilo
            if (!hilo.isAlive()) {
                hilo.start();
            }
            //con 's' se suspende el hilo y con 'r' se reanuda
            if (orden.equals("s")) {
                hilo.suspender();
            } else if (orden.equals("r")) {
                hilo.reanudar();
            }
        } while (!orden.equals("*")); //con * finaliza el programa

        scanner.close();
        
        //hemos de finalizar el hilo también
        hilo.finalizar();        
        
        
    }
    
}

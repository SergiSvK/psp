import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        java.util.Scanner scanner = new Scanner(System.in);
        
        MiHilo hilo = new MiHilo();
        
        //escribimos el estado del hilo            
        escribirEstado(hilo.getState());

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
            //escribimos el estado del hilo            
            escribirEstado(hilo.getState());


        } while (!orden.equals("*")); //con * finaliza el programa

        scanner.close();
        
        //hemos de finalizar el hilo también
        hilo.finalizar();   
        
        //escribimos el estado del hilo,pero antes esperamos un poco para darle tiempo a que muera
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
                 
        escribirEstado(hilo.getState());
    }

    /**
     * Escribe el consola el estado de un hilo como una cadena en español
     * @param estado estado del hilo
     */
    private static void escribirEstado(Thread.State estado) {        
        switch (estado) {            
            case WAITING:
            case TIMED_WAITING:
            case BLOCKED:
                System.out.println("El hilo está en estado Parado");
                break;
            case NEW:
                System.out.println("El hilo está en estado Nuevo");
                break;
            case RUNNABLE:
                System.out.println("El hilo está en estado Ejecutable");
                break;
            case TERMINATED:
                System.out.println("El hilo está en estado Muerto");
                break;
        }

    }
    
}

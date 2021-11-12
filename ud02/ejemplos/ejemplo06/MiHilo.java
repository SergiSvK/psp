public class MiHilo extends Thread {
    private ComprobadorSuspension comprobadorSuspension = new ComprobadorSuspension();
    private boolean finalizar = false;

    public void suspender() { 
        comprobadorSuspension.set(true); 
        System.out.println("Se ha ordenado la suspensión del hilo");
    }
    
    public void reanudar() { 
        comprobadorSuspension.set(false);
        System.out.println("Se ha ordenado la reanudación del hilo");
    }

    public void finalizar() {
      finalizar = true;
      System.out.println("Se ha ordenado la finalización del hilo");
    }
    
    public void run() {
      try {
        int i=0;
        while (!finalizar) {
          System.out.println("Valor del contador:  " + i);
          Thread.sleep(500);
          comprobadorSuspension.comprobarSuspension();          
          i++;
        }
      } catch (InterruptedException exception) {
      }
    
    }
}

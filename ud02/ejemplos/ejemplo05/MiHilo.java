public class MiHilo extends Thread {
    private boolean suspende = false;
    private boolean finaliza = false;

    public synchronized void suspender() { 
        suspende = true;
        System.out.println("Se ha ordenado la suspensión del hilo");
    }
    
    public synchronized void reanudar() { 
        suspende = false;
        notifyAll();
        System.out.println("Se ha ordenado la reanudación del hilo");
    }

    public void finalizar() {
      finaliza = true;
      System.out.println("Se ha ordenado la finalización del hilo");
    }
    
    public void run() {
      try {
        int i=0;
        while (!finaliza) {
          System.out.println("Valor del contador:  " + i);
          Thread.sleep(500);
          comprobarSuspension();          
          i++;
        }
      } catch (InterruptedException exception) {
      }
    
    }

    private synchronized void comprobarSuspension() throws InterruptedException {
      //lo normal sería que el wait bloquee el hilo pero para evitar comportamientos incoherentes
      //se recomienda incorporar el wait en un bucle 
      while (suspende) {
          wait();
      }
    }
}

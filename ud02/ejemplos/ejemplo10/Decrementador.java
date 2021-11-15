public class Decrementador extends Thread {
    private ContadorSincronizado contador;
    
    public Decrementador (String n, ContadorSincronizado c) {
        setName(n);
        contador = c;
    }
    
    public void run() {
        for (int j=0; j < 300; j++) {
            contador.decrementa();
            try { 
                sleep(100); 
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
  }
}

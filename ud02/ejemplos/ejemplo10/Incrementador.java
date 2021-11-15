public class Incrementador extends Thread {
    private ContadorSincronizado contador;
    
    public Incrementador (String n, ContadorSincronizado c) {
        setName(n);
        contador = c;
    }
    
    public void run() {
        for (int j=0; j < 300; j++) {
            contador.incrementa();
            try { 
                sleep(100); 
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
  }
}

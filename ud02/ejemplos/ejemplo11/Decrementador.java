public class Decrementador extends Thread {
    private Contador contador;
    
    public Decrementador (String n, Contador c) {
        setName(n);
        contador = c;
    }
    
    public void run() {
        for (int j=0; j < 300; j++) {
            synchronized (contador) {
                contador.decrementa();
            }
            try { 
                sleep(100); 
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
  }
}

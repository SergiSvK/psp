public class Incrementador extends Thread {
    private Contador contador;
    
    public Incrementador (String n, Contador c) {
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

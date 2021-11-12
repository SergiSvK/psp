public class ComprobadorSuspension {
    private boolean suspender;
    public synchronized void set (boolean suspende) {
        suspender = suspende;
        notifyAll();
    }
    public synchronized void comprobarSuspension() throws InterruptedException {
        //lo normal sería que el wait bloquee el hilo pero para evitar comportamientos incoherentes
        //se recomienda incorporar el wait en un bucle 
        while (suspender) {
            wait();
        }
      }
}

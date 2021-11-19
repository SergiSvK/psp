/**
 * Clase consumidor, recoge los números producidos por el productor
 */
public class Consumidor extends Thread {
    private Cola cola;
    private int n;

    public Consumidor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        int valor = 0;
        // consume 5 números
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); // recoge el número
            //TODO los mensajes puede que salgan desordenados, pasarlos a la clase COLA
            System.out.println(i + "=>Consumidor " + n + ", consume: " + valor);
            /*
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
            */
        }
    }
}
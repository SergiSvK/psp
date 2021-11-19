/**
 * Clase productor, genera números de 0 a 5 y los pone en el objeto Cola
 */
public class Productor extends Thread {
    private Cola cola;
    private int n;

    public Productor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        // genera números de 0 a 5
        for (int i = 0; i < 5; i++) {
            cola.put(i); // pone el número
            System.out.println(i + "=>Productor " + n + ", produce: " + i);
            // hacemos una pequeña pausa (el hilo pasa a estado PARADO)
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
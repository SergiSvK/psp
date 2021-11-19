/**
 * Clase cola, contiene el número producido por el productor para el consumidor
 */
public class Cola {
    private int numero; // la cola sólo tiene un número, es una minicola
    private boolean disponible = false;

    /**
     * Recoger un número de la cola
     * 
     * @return valor del número contenido o -1 si no hay ninguno
     */
    public synchronized int get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (disponible) { // existe un numero en la cola
            disponible = false; // indicamos que la cola está vacía
            //System.out.println("Se consume: " + numero);
            notifyAll(); //desbloquea al productor que esté esperando para generar un nuevo número
            return numero; // devolvemos el número;
        } else {
            return -1; //TODO esto ya no tiene sentido, disponible solo puede ser true al llegar aquí
        }
    }

    /**
     * Introducir un número en la cola
     * 
     * @param valor número a introducir
     */
    public synchronized void put(int valor) {
        //si ya hubiese un número, el productor se ha de esperar a que el consumidor le avise de que puede producir otro
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numero = valor;
        disponible = true;
        //System.out.println("Se produce: " + numero);
        //informa al consumidor que esté parado de que hay un número para consumir
        notifyAll();
    }
}

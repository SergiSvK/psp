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
    public int get() {
        if (disponible) { // existe un numero en la cola
            disponible = false; // indicamos que la cola está vacía
            return numero; // devolvemos el número;
        } else {
            return -1;
        }
    }

    /**
     * Introducir un número en la cola
     * 
     * @param valor número a introducir
     */
    public void put(int valor) {
        numero = valor;
        disponible = true;
    }
}

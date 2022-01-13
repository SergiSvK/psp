package org.psp.cliente;

/**
 * Excepción en protocolo de comunicación con servidor
 * @author Rubén
 */
public class ExcepcionNoConectado extends Exception {

    /**
     * Creates a new instance of
     * <code>ExcepcionComunicacion</code> without detail message.
     */
    public ExcepcionNoConectado() {
    }

    /**
     * Constructs an instance of
     * <code>ExcepcionComunicacion</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionNoConectado(String msg) {
        super(msg);
    }
}

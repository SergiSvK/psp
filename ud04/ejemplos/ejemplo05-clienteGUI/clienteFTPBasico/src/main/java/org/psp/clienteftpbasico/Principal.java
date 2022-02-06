package org.psp.clienteftpbasico;

import java.io.IOException;

/**
 *
 * @author ruben
 */
public class Principal {

    public static void main(String[] args) {
        InterfazClienteFTP cliente = new InterfazClienteFTP();
        cliente.conectar("localhost", "pruebasftp", "SMX2019*", "/");
        cliente.setVisible(true);
    }

}

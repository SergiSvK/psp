package org.psp.clienteftpbasico;

import java.io.IOException;

/**
 *
 * @author ruben
 */
public class Principal {
    
    public static void main(String[] args) throws IOException {
        ClienteFTPBasico cliente = new ClienteFTPBasico("localhost","pruebasftp","SMX2019*","/");
        cliente.setVisible(true);
    }
    
}

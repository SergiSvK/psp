package org.psp.cliente;

import org.psp.comunes.Ciudad;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clase que implementa el protocolo de comunicación en la parte cliente
 *
 * @author Rubén
 */
public class Cliente {

    private static final String END_OF_SESSION = "-1";
    
    private Socket socket;
    private DataOutputStream out;
    private ObjectInputStream in;
    private boolean conectado;

    public Cliente() {
        this.conectado = false;
    }

    public void Conectar(String host, int puerto) throws IOException {

        //conectamos con el servidor
        socket = new Socket(host, puerto);

        //creamos los streams de salida y de entrada
        out = new DataOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        conectado = true;
    }

    /**
     * Obtiene las temperaturas de la ciudad indicada
     *
     * @param codigoCiudad
     * @return
     */
    public Ciudad obtenerTemperaturas(String codigoCiudad) throws IOException, ClassNotFoundException, ExcepcionNoConectado {

        if (!conectado) {
            throw new ExcepcionNoConectado();
        }
        
        //solicitamos al servidor los datos de la ciudad
        out.writeBytes(codigoCiudad + '\n');

        //leemos la respuesta del servidor
        return (Ciudad) in.readObject();
    }

    public void Desconectar() throws IOException, ExcepcionNoConectado {
        
        if (!conectado) {
            throw new ExcepcionNoConectado();
        }
        
        //informamos al servidor del fin de la sesión
        out.writeBytes(END_OF_SESSION + '\n');

        //cerramos los streams y el socket
        out.close();
        in.close();
        socket.close();
        
        conectado = false;
    }

}

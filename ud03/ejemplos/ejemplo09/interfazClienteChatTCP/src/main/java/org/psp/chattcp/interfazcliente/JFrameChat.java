package org.psp.chattcp.interfazcliente;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.psp.chattcp.cliente.ClienteChat;

/**
 * Interfaz gráfico del cliente de chat TCP
 *
 * @author ruben
 */
public class JFrameChat extends javax.swing.JFrame implements Runnable {

    //instancia del cliente TCP
    private ClienteChat clienteChat;
    //flag que indica que el hilo sigue activo
    private boolean activo;

    /**
     * Creates new form JFrameChat
     */
    public JFrameChat(String nombre, ClienteChat clienteChat) {
        this.clienteChat = clienteChat;
        initComponents();
        this.setTitle(" CONEXIÓN DEL CLIENTE CHAT: " + nombre);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMensaje = new javax.swing.JTextField();
        botonSalir = new javax.swing.JButton();
        botonEnviar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        textareaMensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        txtMensaje.setName(""); // NOI18N

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        botonEnviar.setText("Enviar");
        botonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textareaMensajes.setEditable(false);
        textareaMensajes.setColumns(20);
        textareaMensajes.setLineWrap(true);
        textareaMensajes.setRows(5);
        jScrollPane1.setViewportView(textareaMensajes);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonSalir)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMensaje)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEnviar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonSalir)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * Gestión de la pulsación del botón enviar
 * @param evt 
 */
    private void botonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarActionPerformed
        //recuperamos el texto introducido por el usuario en el control gráfico
        if (txtMensaje.getText().trim().length() == 0) {
            return;
        }
        String texto = txtMensaje.getText();
        //usamos el cliente para enviar el mensaje
        try {
            clienteChat.enviarMensaje(texto);
            txtMensaje.setText("");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_botonEnviarActionPerformed

/**
 * Gestión de la pulsación del botón salir
 * @param evt 
 */
    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        try {
            clienteChat.salir();
            //desactivamos el hilo
            activo = false;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_botonSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEnviar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textareaMensajes;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables

    /**
     * Hilo de ejecución para la recepción de mensajes
     */
    public void run() {
        activo = true;
        while (activo) {
            try {
                String texto = clienteChat.recibirMensajes();
                textareaMensajes.setText(texto);

            } catch (IOException e) {
                // este error sale cuando el servidor se cierra
                JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
                        "<<MENSAJE DE ERROR:2>>", JOptionPane.ERROR_MESSAGE);
                activo = false;
            }
        }

        System.exit(0);
    }

}

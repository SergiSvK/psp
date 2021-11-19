/**
 * Hilo que mostrará un mensaje en pantalla *
 */
public class TicTac extends Thread {

    // mensaje a imprimir
    private String mensaje;
    // objeto monitor para realizar la sincronización
    private ImpresorMensajes impresorMensajes;

    public TicTac(String nombreHilo, String mensaje, ImpresorMensajes impresorMensajes) {
        super(nombreHilo);
        this.mensaje = mensaje;
        this.impresorMensajes = impresorMensajes;
    }

    public void run() {
        synchronized (impresorMensajes) {
            while (true) {
                impresorMensajes.PrintarMensaje(mensaje);
                impresorMensajes.notifyAll(); // ya hemos usado el objeto compartido, lo liberamos para otros hilos
                try {
                    Thread.sleep(500); // pausa para poder ver los mensajes
                    impresorMensajes.wait(); // bloqueamos al hilo hasta que otro hilo lo despierte
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
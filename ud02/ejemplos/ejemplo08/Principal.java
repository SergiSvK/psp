public class Principal {
    public static void main(String args[]) {
        HiloPrioridad hilo1 = new HiloPrioridad();
        HiloPrioridad hilo2 = new HiloPrioridad();
        HiloPrioridad hilo3 = new HiloPrioridad();

        //se establecen distintas prioridades para cada hilo
        hilo1.setPriority(Thread.NORM_PRIORITY);
        hilo2.setPriority(Thread.MAX_PRIORITY);
        hilo3.setPriority(Thread.MIN_PRIORITY);

        //iniciamos el hilo
        hilo1.start();
        hilo2.start();
        hilo3.start();

        //vamos a ver en un segundo cual ha llegado a contar más, ¿será el de máxima prioridad? ¿será igual el Linux y Windows?
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hilo1.pararHilo();
        hilo2.pararHilo();
        hilo3.pararHilo();

        System.out.println("h2 (Prioridad Máxima: " + hilo2.getContador());
        System.out.println("h1 (Prioridad Normal: " + hilo1.getContador());
        System.out.println("h3 (Prioridad Mínima: " + hilo3.getContador());
    }
  }

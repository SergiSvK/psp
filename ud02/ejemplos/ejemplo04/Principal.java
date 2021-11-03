public class Principal {
    public static void main(String[] args) {
        
        Hilo uno, dos;
        uno = new Hilo();
        dos = new Hilo();

        Thread thread1 = new Thread(uno);
        thread1.setName("Hilo 1");
        thread1.start();

        Thread thread2 = new Thread(dos);
        thread2.setName("Hilo 2");
        thread2.start();        

        /*
        //lo mismo pero escribiendo menos código 
        new Thread(new Hilo(),"Hilo 1").start();
        new Thread(new Hilo(),"Hilo 2").start();
        */

        /*
        //vamos a darle faena al hilo principal
        for (int i=0; i < 50;i++) {
            System.out.print(" .");
            try {
                Thread.sleep(100);
            } catch (InterruptedException exc) { }                
        }
        */

        System.out.println("Hilo principal finalizado.");
        
    }
}


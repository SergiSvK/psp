public class Principal {
    public static void main(String[] args) {
        
        HiloJoin h1 = new HiloJoin("Hilo1",2);
        HiloJoin h2 = new HiloJoin("Hilo2",5);
        HiloJoin h3 = new HiloJoin("Hilo3",7);
        
        h1.start();
        h2.start();
        h3.start();
        /*
        //esperaremos a que todos los hilos terminen
        try {
            h1.join();
            h2.join();
            h3.join();
        } catch (InterruptedException e) {  }
        */
        System.out.println("Final de programa");
    }
    
}

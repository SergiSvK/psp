public class Hilo implements Runnable{

    public void run() {
        for (int i=0; i < 10; i++) {
            System.out.println(i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {}
        }
        
        System.out.println("Fin de " + Thread.currentThread().getName());
    }

    
}

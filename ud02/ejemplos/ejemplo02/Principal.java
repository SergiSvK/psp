public class Principal {
    public static void main(String[] args) {
	 
        Thread.currentThread().setName("Principal");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().toString());		
        
        for (int i = 0; i < 3; i++) {
            HiloEjemplo2 h = new HiloEjemplo2(); //crear hilo
            h.setName("HILO " + i);    //damos nombre al hilo
            h.setPriority(i == 2 ? java.lang.Thread.MAX_PRIORITY : java.lang.Thread.NORM_PRIORITY);     //damos prioridad
            h.start();              //iniciar hilo
            
            System.out.println("Informacion del " + h.getName() + ": " + h.toString());	  
        }
        
        System.out.println("3 HILOS CREADOS...");	
        System.out.println("Hilos activos: " + Thread.activeCount());	
      } 
    
}

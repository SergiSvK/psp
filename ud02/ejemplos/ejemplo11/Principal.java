public class Principal {
    public static void main(String[] args) {
        Contador contador = new Contador(100);
        
        Incrementador incrementador = new Incrementador("Incrementador",contador);
        Decrementador decrementador = new Decrementador("Decrementador",contador);
        
        incrementador.start();
        decrementador.start();

        try {
            incrementador.join();
            decrementador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        

        System.out.println("Al final contador vale :" + contador.getValor());
    }   
}

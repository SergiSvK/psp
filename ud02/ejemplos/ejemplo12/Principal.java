class Principal {
    public static void main (String[] args) {
        //creamos el objeto monitor
        ImpresorMensajes impresorMensajes = new ImpresorMensajes(); 
        
        //creamos y lanzamos los hilos
        TicTac tic = new TicTac("tic","tic",impresorMensajes);
        TicTac tac = new TicTac("tac","tac",impresorMensajes);
         
        tic.start();
        tac.start(); 
    }
}

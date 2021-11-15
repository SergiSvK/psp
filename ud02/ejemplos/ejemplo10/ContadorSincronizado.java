class ContadorSincronizado {
    
    private int contador = 0;
    
    ContadorSincronizado (int valorInicial) {
      this.contador= valorInicial;
    }
    
    public synchronized void incrementa() { 
        contador++;
    }
    
    public synchronized void decrementa() { 
        contador--;
    }
    
    public synchronized int getValor() {
        return contador;
    }
  }
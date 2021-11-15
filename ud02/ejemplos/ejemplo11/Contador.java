class Contador {
    
    private int contador = 0;
    
    Contador (int valorInicial) {
      this.contador= valorInicial;
    }
    
    public void incrementa() { 
        contador++;
    }
    
    public void decrementa() { 
        contador--;
    }
    
    public int getValor() {
        return contador;
    }
  }
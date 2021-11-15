class HiloPrioridad extends Thread {
    private long c = 0;
    private boolean stopHilo = false;

    public long getContador () {
        return c;
    }

    public void pararHilo() {
        stopHilo = true;
    }

    public void run() {
        while (!stopHilo) {
            c++;
        }
    }
  }
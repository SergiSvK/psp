#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>

//gestión del hijo de la señal recibida
void manejador(int sig) {   	
	static int contador = 0;
	printf("He recibido la señal divina: %d\n",sig);
	contador++;
	if (contador == 2) {
		exit(0);
	}
}


int main() {
  int pid_hijo;
  pid_hijo = fork();
  switch(pid_hijo) {
    case -1:
       printf("Error al crear el prceso .. \n");
       exit (-1);
    case 0: //hijo
       signal (SIGUSR1, manejador); // MANEJADOR DE LA SEÑAL EN HIJO
       while(1) { //el hijo se queda en un bucle infinito esperando recibir señales
       }
    break;
    default: // padre envía 2 señales
       sleep(1);
       kill (pid_hijo, SIGUSR1);
       sleep (1);
       kill (pid_hijo, SIGUSR1);
       sleep (1);
       kill (pid_hijo, SIGKILL);    
     break;
  }
  return 0;
}



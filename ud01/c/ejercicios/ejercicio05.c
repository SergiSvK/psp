#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

//Abuelo-hijo-nieto
void main(void) {
  pid_t pid, Hijo_pid, pid2, Hijo2_pid;
  int fd1[2];
  int fd2[2];
  
  // saludos de pantalla 
  char saludoAbuelo[]="Saludos del Abuelo al Hijo.\0";
  char saludoPadre[]="Saludos del Hijo al Nieto.\0";
  char saludoHijo[]="Saludos del Nieto al Hijo.\0";
  char saludoNieto[]="Saludos del Hijo al Abuelo.\0";
  char buffer[80]="";
  
  pipe (fd1);
  pipe (fd2);
  pid = fork(); // Abuelo creando al hijo
  if (pid ==-1) {
     
  }
  if (pid == 0) { //Soy el hijo
    // creamos al nieto
    pid2 = fork();
    switch (pid2) {
      case -1: 
        
        break;
      case 0: // Soy el nieto
        // cierro fd2 como escritura; y leo de fd2
        ...
        // Imprimo el mensaje que me mandó Hijo (mi padre)
        ...
        // Envío un mensaje a Hijo (mi padre) a través de fd1
        // con lo que tengo que cerrar fd1 como lectura
        ...
        break;
      defaul:
        // Soy el hijo el que tiene más faena
        // Leo lo que me manda el abuelo por fd1 y lo imprimo por pantalla
        ...
        // envio a nieto (mi hijo) por fd2 el mensaje de la variable "saludoPadre"
        ...
        // me quedo esperando a que Nieto termine (mi hijo)
        Hijo2_pid=wait(NULL);
        // Recibo el mensaje de Nieto por fd1, y lo imprimo por pantalla
        ...
        // Envío mensaje al abuelo a través de fd2
        ...  
    }
  } else { // Soy el abuelo
    ...
    printf("Abuelo envia mensaje al hijo ...\n");
    // cierra fd1[0] para la lectura el abuelo lee por fd2[0]
    ...
    // escribe en fd1 saludoAbuelo, acuérdate de calcular la longitud del string.
    ...
    // EL abuelo se queda esperando a que termine el hijo
    ...
    // El abuelo recibe el mensaje por fd2, luego cierra fd2 en modo escritura
    ...
    // Lee de la pipe fd2, entendemos que su hijo le dejó un mensaje antes de terminar.
    read(fd2[0],buffer, sizeof(buffer));
    printf("El abuelo recibe el siguiente mensaje del hijo: %s\n",buffer);
    
  }
  exit(0);
}


#include <stdlib.h> 
#include <unistd.h> 
#include <stdio.h> 
#include <sys/types.h> 
#include <sys/wait.h> 
void main(void) {
  pid_t pid_hijo, pid_nieto, pid_hijo_terminado, pid_nieto_terminado; 
  pid_hijo = fork();   // Soy el abuelo e intento crear a mi hijo
  if (pid_hijo == -1) { // Hubo error 
    printf("Hubo un problema al crear el hijo"); 
    exit(-1);
  } 
  // Si todo va bien y se crea el hijo tenemos que hacer 
  // que el programa ejecute un código con distinto para cada 
  // proceso 
  if (pid_hijo == 0) { 
    // Nos encontramos en el hijo 
    pid_nieto = fork(); //soy el hijo y creo al nieto
    switch(pid_nieto) {
      case -1: //error
        printf("No se ha podido crear el proceso nieto en el hijo");
        exit(-1);
        break;
      case 0: // Estoy en el nieto
        printf("NIETO: Soy el proceso NIETO %d, Mi padre es = %d \n", getpid(),getppid());
        break;
      default: //proceso padre del nieto
        printf("HIJO: Soy el proceso HIJO %d, Mi padre es = %d \n", getpid(),getppid());  
        pid_nieto_terminado=wait(NULL); //espero a que termine el nieto, que es mi hijo.
        printf("HIJO: Mi hijo (el nieto): %d terminó.\n", pid_nieto_terminado);
    }
    
  } else { 
    // Nos encontramos en el abuelo
   printf("ABUELO: Yo soy el abuelo de las dos criaturas\n"); 
   printf("ABUELO: Mi PID es %d, el de mi padre (Sistema Operativo) es %d.\n", getpid(),getppid()); 
   printf("ABUELO: Mi hijo si es de verdad hijo mio deberia tener el PID %d.\n",pid_hijo);

   pid_hijo_terminado = wait(NULL); // Espero a que termine mi hijo, que a su vez espera que termine el nieto
   printf("ABUELO: Mi hijo: %d terminó.\n", pid_hijo_terminado);
  } 

  exit(0); 
}

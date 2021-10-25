#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

void main(void) {
  pid_t id_actual, id_padre, pid, pid_hijo_finalizado;
  pid = fork();
  if (pid == -1) { // Hubo error
    printf("Hubo un problema al crear el hijo");
    exit(-1); }
  // Si todo va bien y se crea el hijo, lo suyo es mandarle trabajo al hijo 
  if (pid == 0) { // Nos encontramos en el hijo
    printf ("Soy el proceso hijo\n\t");
    printf(" Mi PID es %d, y el mi papa %d\n",getpid(),getppid());
  } else { // Nos encontramos en el padre
    //vamos a hacer que espere al hijo
    pid_hijo_finalizado = wait(NULL);
    printf("Yo soy el padre de la criatura:\n\t");
    printf("Mi PID es %d, el de mi padre (abuelo de la criatura) es %d.\n\t", getpid(),getppid());
    printf("Mi hijo si es de verdad hijo mio deberia tener el PID %d.\n\t",pid);
    printf("Mi hijo al terminar me ha dicho que tenía el PID %d.\n",pid_hijo_finalizado);
  }
  exit(0);
}



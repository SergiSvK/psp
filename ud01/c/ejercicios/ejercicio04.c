#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
//paso de informacion de proceso padre a hijo mediante un Pipe
int main(void)
{
  int fd[2];
  pid_t pid;
  
  pipe(fd);   //creo pipe
  pid=fork(); //creo proceso 

 switch(pid) {
   case -1 : //ERROR
           printf("NO SE HA PODIDO CREAR HIJO...");
           return(-1);             
             
   case 0 ://Hijo envía
            close(fd[0]); //el hijo sólo va a escribir así que cierra el descriptor de entrada
            char saludoPadre[]="Buenos dias papi!";
            write(fd[1], saludoPadre, sizeof(saludoPadre));//hijo escribe en pipe
            printf("El HIJO ENVIA MENSAJE AL PADRE...\n");                
            break;
   
   default: 
            close(fd[1]);//el padre sólo va a leer así que cierra el descriptor de salida
            char buffer[80];
            read(fd[0], buffer, sizeof(buffer)); //padre lee del pipe            
            printf("\tEl PADRE recibe algo del pipe: %s\n",buffer);             
            break;  
   
 }  
 return 0;
}
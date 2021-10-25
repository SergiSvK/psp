//fifocrea.c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

void main(void)
{
  int fp;  
  int p, bytesleidos;
  char buffer[10];
 
  //creamos el named fifo con el nombre FIFO2
  p=mkfifo("FIFO2", S_IFIFO | S_IRWXU);//con S_IFIFO le decimos que queremos crear un FIFO y S_IRWXU indica permiso de lectura y escritura
  
  if (p==-1) {
      printf("HA OCURRIDO UN ERROR...\n");
      exit(0); 
  }
  
  //nos quedamos esperando que llegue algo para escribirlo por la pantalla
  while(1) {  
   fp = open("FIFO2", O_RDONLY);  
   bytesleidos = read(fp, buffer, 1); 
   printf("OBTENIENDO Información...");
   while (bytesleidos != 0){      
       printf("%1c", buffer[0]);    //escribo un caracter por pantalla
       bytesleidos= read(fp, buffer, 1);//leo otro byte
   }
   close(fp);  
  }
  
}


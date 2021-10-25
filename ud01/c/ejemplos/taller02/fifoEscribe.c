//fifoescribe2.c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

int main()
{
  int fp;
  char saludo[] = "Un saludo!!!\n";
  fp = open("FIFO2", O_WRONLY); //abre el named pipe con permisos de escritura 
 
  if(fp == -1) {
    printf("ERROR AL ABRIR EL FICHERO...");
    exit(1);
  } 
  printf("Mandando información al FIFO...\n");
  write(fp,saludo, sizeof(saludo));
  close(fp);   
  return 0; 
}

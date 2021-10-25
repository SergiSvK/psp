#include <stdlib.h> 
#include <unistd.h> 
#include <stdio.h> 
#include <sys/types.h> 
#include <sys/wait.h> 

void main(void) {
	int x = 6;	 
	pid_t pid_hijo = fork();   
	
	if (pid_hijo == -1) { // Hubo error 
		printf("Hubo un problema al crear el hijo"); 
		exit(-1);
	} else if (pid_hijo == 0) { 
		// Nos encontramos en el hijo 
		x -= 5;
	} else {
		// Nos encontramos en el padre
		x += 5;
	}

	printf("La variable vale %d\n", x);
	exit(0); 
}

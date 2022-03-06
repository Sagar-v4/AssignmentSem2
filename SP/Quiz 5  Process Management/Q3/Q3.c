#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>

int main (int argc, char* argv[])
{
	if (argc < 5) {
		printf("USAGE: parent_process.out <file1> <file2> .. <file5>\n");
	}
	
	int status;
    for (int i=0; i<argc-1; i++) {
    
  		if (fork()==0) {
          execlp("wc", "wc", argv[i+1], NULL);
          exit(0);
       }
	}
	
    for (int i=0; i<5; i++) {
		wait(&status);
	}
	
	return 0;
}

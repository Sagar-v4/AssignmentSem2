#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int fd[6][2], i;
    
    // opening 6 pipes
    for(int i=0; i<6; i++)
    {
	/* pipe() returns : 0 on Success
	 	   	   -1 on error 
	   fd[i][0]: fd(file descriptor) for the read end of pipe
	   fd[i][1]: fd for the write end of pipe
	*/
    	if(pipe(fd[i]) < 0) 
    	{
    		printf("\n An error occurred while opening the pipe \n");
        	exit(1);
    	}
    }
    
     // creating child process 1 
     int id1 = fork();
     
     //creation of a child process was unsuccessful
     if(id1 < 0)
     {
     	printf("\n An error occurred while creating child process 1\n");
        exit(1);
     } 
     
     
     if(id1 == 0) //In child 1
     {
	//closing all the file descriptors which are not used by child process 1
     	close(fd[0][1]);
     	close(fd[1][0]);
     	close(fd[1][1]);
     	close(fd[2][0]);
     	close(fd[2][1]);
     	close(fd[3][0]);
     	close(fd[4][0]);
     	close(fd[5][0]);
     	
     	char * recv;
	// reading message from fd[0]
     	if(read(fd[0][0], &recv, sizeof(recv)) < 0)
     	{
     		printf("\n An error occurred while reading from pipe in child process 1 \n");
        	exit(1);	
     	}
     	printf("\n In child process 1 : Got from Parent process: %s \n", recv);
     	
     	//(b) -> Child to parent
     	char * send_msg1 = "Hello parent, I am child process 1";
	// writing message to fd[3]
     	if(write(fd[3][1], &send_msg1, sizeof(send_msg1)) < 0)
     	{
     		printf("\n An error occurred while writing in pipe from child process 1 \n");
        	exit(1);
     	}
     	
     	//(c) -> Child to siblings
     	char * send_msg2 = "Hello child process 2, I am your sibling child process 1";
     	if(write(fd[4][1], &send_msg2, sizeof(send_msg2)) < 0)
     	{
     		printf("\n An error occurred while writing in pipe from child process 1 \n");
        	exit(1);
     	}
     	
     	char * send_msg3 = "Hello child process 3, I am your sibling child process 1";
     	if(write(fd[5][1], &send_msg3, sizeof(send_msg3)) < 0)
     	{
     		printf("\n An error occurred while writing in pipe from child process 1 \n");
        	exit(1);
     	}
     	
     	close(fd[0][0]);
     	close(fd[3][1]);
     	close(fd[4][1]);
     	close(fd[5][1]);
     }
     else //in parent
     {
     	int id2 = fork();
     	if(id2 < 0)
    	 {
     		printf("\n An error occurred while creating child process 2 \n");
        	exit(1);
     	} 
     
     
     	if(id2 == 0) //In child 2
     	{
     		close(fd[0][0]);
     		close(fd[0][1]);
     		close(fd[1][1]);
     		close(fd[2][0]);
     		close(fd[2][1]);
     		close(fd[3][0]);
     		close(fd[3][1]);
     		close(fd[4][1]);
     		close(fd[5][0]);
     		close(fd[5][1]);
     	
     		char * recv1;
     		if(read(fd[1][0], &recv1, sizeof(recv1)) < 0)
     		{
     			printf("\n An error occurred while reading from pipe in child process 2 \n");
        		exit(1);	
     		}
     		printf("\n In child process 2 : Got from Parent process: %s \n", recv1);
     		
     		char *recv2;
     		if(read(fd[4][0], &recv2, sizeof(recv2)) < 0)
     		{
     			printf("\n An error occurred while reading from pipe in child process 2 \n");
        		exit(1);	
     		}
     		printf("\n In child process 2 : Got from sibling process: %s \n", recv2);
     		
     		close(fd[1][0]);
     		close(fd[4][0]);
     	}
     	else //again in parent
     	{
     		int id3 = fork();
     		if(id3 < 0)
    	 	{
     			printf("\n An error occurred while creating child process 3 \n");
        		exit(1);
     		} 
     
     
     		if(id3 == 0) //In child 3
     		{
     			close(fd[0][0]);
     			close(fd[0][1]);
     			close(fd[1][0]);
     			close(fd[1][1]);
     			close(fd[2][1]);
     			close(fd[3][0]);
     		 	close(fd[3][1]);
     		 	close(fd[4][0]);
     			close(fd[4][1]);
     			close(fd[5][1]);
     	
     			char * recv1;
     			if(read(fd[2][0], &recv1, sizeof(recv1)) < 0)
     			{
     				printf("\n An error occurred while reading from pipe in child process 3 \n");
        			exit(1);	
     			}
     			printf("\n In child process 3 : Got from Parent process: %s \n", recv1);
     			
     			char *recv2;
     			if(read(fd[5][0], &recv2, sizeof(recv2)) < 0)
     			{
	     			printf("\n An error occurred while reading from pipe in child process 2 \n");
				exit(1);	
     			}
     			printf("\n In child process 3 : Got from sibling process: %s \n", recv2);
     		
     			close(fd[2][0]);
     			close(fd[5][0]);
     		}
     		else{
     	
     			//parent process code
     			close(fd[0][0]);
     			close(fd[1][0]);
     			close(fd[2][0]);
     			close(fd[3][1]);
     			close(fd[4][0]);
     			close(fd[4][1]);
     			close(fd[5][0]);
     			close(fd[5][1]);
     	
     			char * msg1 = "Hello, child 1";
    
     			if(write(fd[0][1], &msg1, sizeof(msg1)) < 0)
     			{
     				printf("\n An error occurred while writing in pipe from parent process for child process 1\n");
        			exit(1);	
     			}
     		
     			char * msg2 = "Hello, child 2";
     			if(write(fd[1][1], &msg2, sizeof(msg2)) < 0)
     			{
     				printf("\n An error occurred while writing in pipe from parent process for child process 2\n");
        			exit(1);	
     			}
     		
     			char * msg3 = "Hello, child 3";
     			if(write(fd[2][1], &msg3, sizeof(msg3)) < 0)
     			{
     				printf("\n An error occurred while writing in pipe from parent process for child process 3\n");
        			exit(1);	
     			}
     			
     			char * recv_msg;
     			if(read(fd[3][0], &recv_msg, sizeof(recv_msg)) < 0)
     			{
     				printf("\n An error occurred while reading from pipe in parent process from child process 1\n");
        			exit(1);
     			}
     			
     			printf("\n In parent process: Got from child process 1: %s \n", recv_msg);
     	
     			close(fd[0][1]);
     			close(fd[1][1]);
     			close(fd[2][1]);
     			close(fd[3][0]);
     		}
     	}
     }
    
    return 0;
}

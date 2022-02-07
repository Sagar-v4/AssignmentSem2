#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
   
#define PORT 8080
#define MAXLINE 1024

int main(int argc, char const *argv[])
{
    int sock = 0;
    struct sockaddr_in servaddr;
    char *exit_msg = "exit";
    char *msg;
    char buffer[MAXLINE] = {0};

    // The socket() - creates a socket in the specified domain and of the specified type.
    // AF_INET - communicating between processes on different hosts connected by IPV4
    // SOCK_DGRAM - UDP (User Datagram Protocol)
    // 0 - Protocol value for Internet Protocol(IP)
    if ((sock = socket(AF_INET, SOCK_DGRAM, 0)) < 0)
    {
        printf("\n Socket creation error \n");
        return -1;
    }
    //memset() - used to fill servaddr variable with 0.
    memset(&servaddr, 0, sizeof(servaddr));
    
    servaddr.sin_family = AF_INET;// match the socket() call
    // The htons() - used to convert an IP port number in host byte order to the IP port number in network byte order
    servaddr.sin_port = htons(PORT);// specify port to listen
    servaddr.sin_addr.s_addr = INADDR_ANY;// bind to any local address 

    int n, len;
    
    while(1){
    	memset(buffer, 0, 1024);
        // taking input of string
    	fgets(msg,100,stdin);
    	msg[strlen(msg)-1] = '\0';
        // while loop break when message will be "exit"
    	if(!strcmp(msg, exit_msg)){
            // close() - shuts down the socket associated with the socket descriptor socket, and frees resources allocated to the socket.
            close(sock);
            return 0;
        }
    	// The sendto() - sends data on the socket with descriptor socket.
        // MSG_CONFIRM - This flag is used to tell the kernel that the neighbour has successfully replied to a message of ours.
    	sendto(sock, (const char *)msg, strlen(msg),MSG_CONFIRM, (const struct sockaddr *) &servaddr,sizeof(servaddr));

        // recvfrom() - places the received message into the buffer. This function is typically used with connectionless sockets.
        // MSG_WAITALL - flag requests that the operation block until the full request is satisfied. 
        n = recvfrom(sock, (char *)buffer, MAXLINE, MSG_WAITALL, (struct sockaddr *) &servaddr,&len);
    	buffer[n] = '\0';
    	printf("From Server : %s\n",buffer );
    }

    return 0;

}

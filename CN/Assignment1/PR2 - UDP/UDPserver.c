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
    int server_fd;
    struct sockaddr_in servaddr, cliaddr;
    char buffer[MAXLINE] = {0};
    char *msg;
   
    // The socket() - creates a socket in the specified domain and of the specified type.
    // AF_INET - communicating between processes on different hosts connected by IPV4
    // SOCK_DGRAM - UDP (User Datagram Protocol)
    // 0 - Protocol value for Internet Protocol(IP)
    if ((server_fd = socket(AF_INET, SOCK_DGRAM, 0)) == 0)
    {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }
    
    //memset() - used to fill servaddr and cliaddr variable with 0.
    memset(&servaddr, 0, sizeof(servaddr));
    memset(&cliaddr, 0, sizeof(cliaddr));
       
    servaddr.sin_family = AF_INET; // match the socket() call
    servaddr.sin_addr.s_addr = INADDR_ANY;// bind to any local address 
    // The htons() - used to convert an IP port number in host byte order to the IP port number in network byte order
    servaddr.sin_port = htons(PORT);// specify port to listen

    // Forcefully attaching socket to the port 8080
    // bind() - binds the socket to the address and port number specified in servaddr
    if (bind(server_fd, (struct sockaddr *)&servaddr,sizeof(servaddr))<0)
    {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

    int len, n;
   
    len = sizeof(cliaddr);
   
    while(1){
        // recvfrom() - places the received message into the buffer. This function is typically used with connectionless sockets.
        // MSG_WAITALL - flag requests that the operation block until the full request is satisfied. 
    	n = recvfrom(server_fd, (char *)buffer, MAXLINE, MSG_WAITALL, ( struct sockaddr *) &cliaddr, &len);
    	buffer[n] = '\0';
    	printf("Client : %s\n",buffer );

    	memset(buffer, 0, 1024);
        printf("Server : ");
        // taking input of string
        fgets(msg,100,stdin);
        // The sendto() - sends data on the socket with descriptor socket.
        // MSG_CONFIRM - This flag is used to tell the kernel that the neighbour has successfully replied to a message of ours.
        sendto(server_fd, (const char *)msg, strlen(msg), MSG_CONFIRM, (const struct sockaddr *) &cliaddr,len);
    } 
    
    return 0;
}

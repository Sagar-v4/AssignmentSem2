#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#define PORT 8080

int main(int argc, char const *argv[])
{
    int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};
    char *exit_msg = "exit", *msg;

    // Creating socket file descriptor
    // The socket() - creates a socket in the specified domain and of the specified type.
    // AF_INET - communicating between processes on different hosts connected by IPV4
    // SOCK_STREAM - TCP (Transmission Control Protocol)
    // 0 - Protocol value for Internet Protocol(IP)
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
    {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }
    
    // Forcefully attaching socket to the port 8080 - For address reuse
    // This is completely optional, but it helps in reuse of address an
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT,&opt, sizeof(opt)))
    {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }

    address.sin_family = AF_INET; // match the socket() call
    address.sin_addr.s_addr = INADDR_ANY; // bind to any local address
    // The htons() - used to convert an IP port number in host byte order to the IP port number in network byte order
    address.sin_port = htons( PORT ); // specify port to listen on

    // Forcefully attaching socket to the port 8080
    // bind() - binds the socket to the address and port number specified in addr
    if (bind(server_fd, (struct sockaddr *)&address,sizeof(address))<0)
    {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

    // listen() - It puts the server socket in a passive mode, where it waits for the client to approach the server to make a connection. 
    if (listen(server_fd, 3) < 0)
    {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    // The server gets a socket for an incoming client connection by calling accept()
    if ((new_socket = accept(server_fd, (struct sockaddr *)&address,(socklen_t*)&addrlen))<0)
    {
        perror("accept");
        exit(EXIT_FAILURE);
    }

    while(1) {
        // memset() - used to fill buffer variable with 0.
        memset(buffer, 0, 1024);
        // The read() - reads data on a socket with descriptor fs and stores it in a buffer. 
        valread = read( new_socket , buffer, 1024);
        buffer[valread]='\0';
        if(strlen(buffer)==0)
        {
        	printf("Client exited...\n");//if buffer is empty then print message client exit 
    	}
    	else{
          	printf("Client : %s\n",buffer );//if any message send from server then it will print
    	}

        memset(buffer, 0, 1024);
        printf("Server : ");
        // taking input of string
        fgets(msg,100,stdin);
    	msg[strlen(msg)-1] = '\0';
        // while loop break when message will be "exit"
        if(!strcmp(msg, exit_msg)){
            // close() - shuts down the socket associated with the socket descriptor socket, and frees resources allocated to the socket.
            close(server_fd);
            return 0;
        }
        // The send() - sends data on the socket with descriptor socket.
        send(new_socket , msg , strlen(msg) , 0 );
    }

    return 0;

}

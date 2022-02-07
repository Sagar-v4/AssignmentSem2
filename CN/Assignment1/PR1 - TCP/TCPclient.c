// Client side C/C++ program to demonstrate Socket programming

#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#define PORT 8080


int main(int argc, char const *argv[])
{
    int sock = 0, valread;
    struct sockaddr_in serv_addr;
    char *exit_msg = "exit";
    char buffer[1024] = {0};
    char *msg;

    // The socket() - creates a socket in the specified domain and of the specified type.
    // AF_INET - communicating between processes on different hosts connected by IPV4
    // SOCK_STREAM - TCP (Transmission Control Protocol)
    // 0 - Protocol value for Internet Protocol(IP)
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    {
        printf("\n Socket creation error \n");
        return -1;
    }

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);

    // Convert IPv4 and IPv6 addresses from text to binary form
    // inet_pton() - converts an Internet address in its standard text format into its numeric binary form.    
    if(inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr)<=0)
    {
        printf("\nInvalid address/ Address not supported \n");
        return -1;
    }

    // The connect() - connects the socket referred to by the file descriptor sockfd to the address specified by addr.
    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
    {
        printf("\nConnection Failed \n");
        return -1;
    }

    while(1) {
        //memset() - used to fill buffer variable with 0.
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
        // The send() - sends data on the socket with descriptor socket.
        send(sock , msg , strlen(msg) , 0 );
        // The read() - reads data on a socket with descriptor fs and stores it in a buffer. 
        valread = read( sock , buffer, 1024);
        buffer[valread]='\0';
        if(strlen(buffer)==0)
        {
        	printf("Server exited...\n");// if buffer is empty then print message server exit
    	}
    	else{
    	
    	        printf("From Server : %s\n",buffer );// if any message send from server then it will print
    	}
    }

    return 0;

}

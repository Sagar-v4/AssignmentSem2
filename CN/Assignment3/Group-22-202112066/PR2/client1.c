// Client side C/C++ program to demonstrate Socket programming

#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#define PORT 8080

int main(int argc, char const *argv[])
{
	int sock_descriptor = 0, recv_msg_size;
	struct sockaddr_in server_addr;
	int socket_addr_len = sizeof(server_addr);
	char buffer[1024] = {0};
	char *client_msg;
	int option;
	int grp_port; // group port to join
	
	if ((sock_descriptor = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		printf("\n (X) Socket creation error... \n");
		return -1;
	}
	
	server_addr.sin_family = AF_INET;
	server_addr.sin_port = htons(PORT);
	
	if(inet_pton(AF_INET, "127.0.0.1", &server_addr.sin_addr)<=0)
	{
		printf("\n (X) Invalid address/ Address not supported... \n");
		return -1;
	}
	
	if (connect(sock_descriptor, (struct sockaddr *)&server_addr, socket_addr_len) < 0)
	{
		printf("\n (X) Connection Failed... \n");
		return -1;
	}

	printf("\n (#) Client connected to main server... \n");
	
	gets(client_msg); //get your name
    	send(sock_descriptor , client_msg , strlen(client_msg) , 0 ); // send your name
    	//recieve group list 
    	recv_msg_size = read(sock_descriptor , buffer, 1024); 
	
	printf("\n Server : %s",buffer);
	
	memset(buffer, 0, 1024);
	
	gets(client_msg); 
    	send(sock_descriptor , client_msg , strlen(client_msg) , 0 ); // send msg for joining group
    	
    	recv_msg_size = read(sock_descriptor , &grp_port, sizeof(grp_port)); 
	printf("\n (#) PORT recieve : %d",grp_port);
    	
    	close(sock_descriptor);
    	
    	printf("\n (#) Client disconnected from Main Server...");
    	
    	if ((sock_descriptor = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		printf("\n (X) Socket creation error... \n");
		return -1;
	}
	
	server_addr.sin_family = AF_INET;
	server_addr.sin_port = htons(grp_port);
	
	if(inet_pton(AF_INET, "127.0.0.1", &server_addr.sin_addr)<=0)
	{
		printf("\n (X) Invalid address/ Address not supported... \n");
		return -1;
	}
	
	if (connect(sock_descriptor, (struct sockaddr *)&server_addr, socket_addr_len) < 0)
	{
		printf("\n (X) Connection Failed... \n");
		return -1;
	}
	
	printf("\n (#) Client connected to Group Server...");
	
	recv_msg_size = read(sock_descriptor , buffer, 1024); 
	
	printf("\n Server : %s\n",buffer);
	
	memset(buffer,0,2024);
    	while(1){
    		gets(client_msg); // taking input for msg
    		if(!strcmp(client_msg,"exit")){
    			send(sock_descriptor,client_msg,strlen(client_msg),0);
    			printf("\n (X) Client disconnected from Group Server");
    			close(sock_descriptor);
    			break;
    		}
    		
    		send(sock_descriptor,client_msg,strlen(client_msg),0);
    	}
	return 0;
}

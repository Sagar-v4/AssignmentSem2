#include <stdio.h>
#include <stdlib.h>
#include "202112114_Lab5_header.h"

void main() {

	char cmd[10], src[50], des[50];
	char *user = getlogin();
	char hostname[1024];
	hostname[1023] = '\0';
	gethostname(hostname, 1023);
	
	while(1){
		printf("\nmyshell@%s@%s$ ", user, hostname);
		scanf("%s", cmd);
		trim(cmd);
		
		switch(cmd[0]){
			case 'c':{
				scanf("%s", src);
				cat(src);
				break;
			}
			case 'l':{
				scanf("%s", src);
				ls(src);
				break;
			}
			case 'm':{
				scanf("%s", src);
				scanf("%s", des);
				mv(src, des);
				break;
			}
			case 'e':{
				exit(0);
			}
			default:{
				printf("[-] command not found !!!\n");
			}
		}
	}
}

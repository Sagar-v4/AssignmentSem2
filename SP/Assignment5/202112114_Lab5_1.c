#include <stdio.h>
#include "202112114_Lab5_header.h"

void main() {

	char cmd[10], src[50], des[50];

	printf("\nEnter command: ");
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
		default:{
			printf("[-] command not found !!!");
		}
	}
	printf("\n");	
}

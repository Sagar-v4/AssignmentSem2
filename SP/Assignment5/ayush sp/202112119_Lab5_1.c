#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<dirent.h>
#include<unistd.h>
#include"myheader.h"

void main()
{
	char str[100];
	
	printf("cat ");
	scanf("%[^\n]%*c",str);
	cat(str);
	
	printf("ls ");
	scanf("%[^\n]%*c",str);
	ls(str);	
	
	printf("\nmv ");
	scanf("%[^\n]%*c",str);
	mv(str);
}

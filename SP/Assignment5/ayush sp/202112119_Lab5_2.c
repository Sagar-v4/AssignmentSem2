#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<dirent.h>
#include<unistd.h>
#include"myheader.h"

void main()
{
	char *name=getlogin();
	char hname[30];
	gethostname(hname,30);
	char input[100];
	while(1)
	{
		printf("myshell@%s@%s$ ",name,hname);
		scanf("%[^\n]%*c",input);
		char *cmd=strtok(input," ");
		if(cmd!=NULL)
		{
			if(strcmp(cmd,"exit")==0)
				break;
			char *cmdp=strtok(NULL,"\n");
			if(strcmp(cmd,"cat")==0)
			{
				if(cmdp!=NULL)
					cat(cmdp);
				else
					printf("myshell@%s@%s$ Invalid Argument\n",name,hname);
			}
			else if(strcmp(cmd,"ls")==0)
			{
				if(cmdp!=NULL)
					ls(cmdp);
				else
					printf("myshell@%s@%s$ Invalid Argument\n",name,hname);
			}
			else if(strcmp(cmd,"mv")==0)
			{	
				if(cmdp!=NULL)
					mv(cmdp);
				else
					printf("myshell@%s@%s$ Invalid Argument\n",name,hname);
			}else
				printf("myshell@%s@%s$ Invalid Command\n",name,hname);
		}
	}
	
}

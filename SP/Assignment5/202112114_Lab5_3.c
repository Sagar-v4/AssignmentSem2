#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>

void main()
{
	struct stat info;
	long int arr[2], l;
	char str[100],lnk[100],*ptr;
	
	printf("Enter File name/path : ");
	scanf("%s",str);
	strcpy(lnk,str);
	ptr = lnk + (strlen(lnk));
	
	while(*ptr != '.')
		ptr--;
		
	*ptr='\0';
	strcat(lnk , "_hlnk");
	l = link(str , lnk);  
	
	stat(lnk, &info);
	arr[0] = info.st_ino;
	
	*ptr='\0';
	strcat(lnk , "_slnk");
	l= symlink(str , lnk);   
	
	lstat(lnk , &info);
	arr[1]=info.st_ino;

	stat(str, &info);
	printf("\ninode of original file : %ld\n",info.st_ino);
	printf("inode of hard link : %ld\n",arr[0]);
	printf("inode of soft link : %ld\n",arr[1]);
	printf("\nHere we can see that inode of hard links is same as the inode of original file \nBut the inode of the soft link is different than the original file.\n");
	
}

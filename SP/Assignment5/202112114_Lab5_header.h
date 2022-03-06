#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <dirent.h>
#include <sys/stat.h>
#include <sys/types.h>

void cat (char *file) {

	FILE *fp = fopen(file,"r");

	if (fp == NULL) {
		printf("cat: %s: No such file or directory\n", file);
		return;
	}

	do
	{
		char c = fgetc(fp);
		if (feof(fp)) break ;
		printf("%c", c);
	} while(1);

	fclose(fp);
}

void ls (char *directory) {
	
	struct dirent *pDirent;
	DIR *pDir = opendir(directory);
	
    if (pDir == NULL) {
        printf ("ls: cannot access '%s': No such file or directory\n", directory);
        return;
    }

	printf ("%s/:\n", directory);
    while ((pDirent = readdir(pDir)) != NULL) {
        printf ("%s  ", pDirent->d_name);
    }

    closedir (pDir);
}

void mv(char *src,char *des){

	int status;
	char tmp1[50],tmp2[50];
	
	strcpy(tmp1, src);
	char *ptr = tmp1 + (strlen(tmp1));
	
	while(*ptr != '/')
		ptr--;
		
	*ptr = '\0';
	strcpy(tmp2, des);
	ptr = tmp2 + (strlen(tmp2));
	
	while(*ptr != '/')
		ptr--;
		
	*ptr = '\0';
	if ( strcmp(tmp1, tmp2) == 0 ) {
	
		if (status = rename(src, des) < 0) {
			printf("can't rename the file\n");
			return;
		}
	}
	else {
	
		int c;
		FILE *fp2 = fopen(des, "w");
		FILE *fp1 = fopen(src, "r");
		
		while ((c = fgetc(fp1)) != EOF)
			fputc(c, fp2);

		fclose(fp1);
		fclose(fp2);

		status = remove(src);
		
		if(status < 0){
			printf("can't move the file\n");
			return;
		}
	}
}

void trim (char *str) {

	while(*str == ' '){
		*str = *(str + 1);
		str++;
	}
}

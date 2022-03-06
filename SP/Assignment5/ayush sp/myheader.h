void cat(char *str)
{
     FILE *fp = fopen(str,"r");
     if(fp==NULL)
     	printf("Cannot open file\n");
     else
     {
     	char ch;
     	while((ch = fgetc(fp)) != EOF)
     	{
     		printf("%c",ch);
     	}
     	fclose(fp);
     }
}

void ls(char *str)
{
	DIR *dir_ptr;
	struct dirent *direntp; /* each entry! */
	dir_ptr = opendir( str );
	if ( dir_ptr == NULL ){
		fprintf(stderr, "cannot open a directory\n");
		exit(1);
	}

	while ( ( direntp = readdir(dir_ptr) ) != NULL ){
		printf("%s\n",direntp->d_name);	
	}
}


void mv(char *str)
{
	char *src=(char *)malloc(100*sizeof(char));
	char *dest=(char *)malloc(100*sizeof(char));
	
	strcpy(src, strtok(str," "));
	strcpy(dest, strtok(NULL," "));
	
	
	char *homepath={"/home/ayush"};
	char *temp=(char *)malloc(100*sizeof(char));
	
	if(src[0]=='~')
	{
		strcpy(temp,homepath);
		src++;
		strcat(temp,src);
		strcpy(src,temp);
	}
	if(dest[0]=='~')
	{
		strcpy(temp,homepath);
		dest++;
		strcat(temp,dest);
		strcpy(dest,temp);
	}
	rename(src,dest);	
}


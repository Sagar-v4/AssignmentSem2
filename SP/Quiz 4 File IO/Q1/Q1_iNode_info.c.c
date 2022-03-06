#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/stat.h>

void main(int argc, char *argv[])
{
	struct stat st;
		
	for (int i = 1; i < argc; i++) {
		
		if ( lstat(argv[i], &st) == 0 ) {
			printf("iNode number of file %s is: %ld\n", argv[i], st.st_ino);
		} else {
			stat(argv[i], &st);
			printf("iNode number of file %s is: %ld\n", argv[i], st.st_ino);
		}
	}	
}

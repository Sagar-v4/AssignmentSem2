#include <stdio.h>
#include <unistd.h>
#include <string.h>

void main(int argc, char *argv[])
{
	if (argc != 3) {
		printf("Invalid arguments !!!\n");
	} else if ( symlink(argv[1], argv[2]) == -1) {
		printf("Unable to create link !!!\n");	
	} else {
		printf("Softlink created...\n");
	}
}
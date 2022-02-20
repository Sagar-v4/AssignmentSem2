#include <stdio.h>
#define MAX_LEN 100

int recursive_strlen(char *str)
{
	if (*str == '\0') return 0;
	
	int count = recursive_strlen(str + 1);
	return ++count;
}

int main(int argc, char *argv[])
{
	char string[MAX_LEN];
	
	printf("Enter a String: ");
	scanf("%[^\n]s", string);

	printf("Length of \"%s\" is: %d\n", string, recursive_strlen(string));

	return 0;
}

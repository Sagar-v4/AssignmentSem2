#include <stdio.h>

int myfact(int n)
{
	int fact = 1;
	while(n) fact *= n--;
	
	return fact;	
}


#include <stdio.h>
#include "q1.h"

int main() {

	char c;
	int no, ans;
	
	printf("\nEnter character to check it is digit or not: ");
	scanf("%c", &c);
	getchar();
	
	ans = is_digit(c);
	
	if(ans == 1) printf("%c is a digit...\n", c);
	else printf("%c is not a digit...\n", c);

	printf("\nEnter number to check it is prime or not: ");
	scanf("%d", &no);
	getchar();
	
	ans = is_prime(no);
	
	if(ans == 1) printf("%d is a prime number...\n\n", no);
	else printf("%d is not a prime number...\n\n", no);
	
	return 0;
}

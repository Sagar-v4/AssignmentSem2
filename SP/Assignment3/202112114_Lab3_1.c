#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int fib(int x)
{
	return ((x <= 1) ? x : fib(x - 1) + fib(x - 2));
}

int fact(int x)
{
	return ((x <= 1) ? 1 : x * fact(x - 1));
}

int numberLength(int x, int count)
{
	return ((x > 0) ? numberLength(x / 10, ++count) : count);
}

int numlen(int x)
{
	return numberLength(x, 0);
}

int exitSystem(int x)
{
	printf("Process finished with exit code %d\n", x);
	sleep(1);
	system("clear");
	exit(0);
}

int main(int argc, char *argv[])
{
	int (*functions[4])(int) = {exitSystem, fib, fact, numlen};

	int choice;

	while (1)
	{
		system("clear");
		printf("\n--- MENU ---");
		printf("\n1) Fibonacci");
		printf("\n2) Factorial");
		printf("\n3) length");
		printf("\n0) Exit");
		printf("\n------------");
		printf("\n\nEnter Your Choice: ");
		scanf("%d", &choice);
		getchar();

		if (choice == 0)
			functions[choice](choice);

		else if (1 <= choice && choice <= 3)
		{
			int n = 0;

			printf("\nEnter the number: ");
			scanf("%d", &n);
			getchar();
			printf("\nAnswer: %d\n", functions[choice](n));
		}
		else
			printf("\nInvalid Choice..!!!\n");

		sleep(1);
	}

	return 0;
}

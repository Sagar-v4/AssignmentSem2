#include <stdio.h>
#include "202112114_Lab4_1_myheader.h"

float calcSin(float x, int n)
{
	int sign = -1;
	double power, factorial, sum = 0;
	
	x = x*3.14 / 180;
	sum = x;
	
	for(int i=3; i<=n; i+=2)
	{
		power = mypow(x, i);
		factorial = myfact(i);
		
		sum += sign * (power / factorial);
		sign *= -1;
	}
	
	return sum;
}

int main()
{
	int n;
	float x;
	
	printf("Enter Degree: ");
	scanf("%f", &x);

	printf("Enter n: ");
	scanf("%d", &n);
	
	printf("\nAns: %.2f\n", calcSin(x, n));


	return 0;
}

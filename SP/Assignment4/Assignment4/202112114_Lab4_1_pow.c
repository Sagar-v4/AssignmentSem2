#include <stdio.h>

int mypow(int x, int n)
{
	int temp = 1, powered = x;
	while(n) {
		if(n & 1) {
			temp *= powered;	
			n--;
		} else {
			powered *= powered;
			n >>= 1;
		}
	}
	powered = temp;
	return powered;
}

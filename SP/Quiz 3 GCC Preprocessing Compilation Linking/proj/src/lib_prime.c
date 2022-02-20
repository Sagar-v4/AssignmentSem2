#include <stdio.h>

int is_prime(int no) {

	if (no == 1) return 0;

	for (int i = 2; i * i <= no; i++)
		if (!(no % i)) return 0;

	return 1;
}

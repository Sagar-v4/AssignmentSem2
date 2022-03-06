#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
  
int main(int argc, char* argv[])
{
    int l = link(argv[1],"hardlink.txt");
  
    int sl = symlink(argv[1],"softlink.txt");  

    if (l == 0) 
    	printf("Hard Link created\n");
    	
    if (sl == 0) 
        printf("Soft Link created\n");
    
    return 0;
}

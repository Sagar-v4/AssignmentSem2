#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/wait.h>

int main(void)
{
    int x = 10, status, childpid;
    int pid1 = fork();
    if (pid1 != 0)
    {
        childpid = wait(&status);
        x = x + WEXITSTATUS(status);
        printf("%d", x);
    }
    else
    {
        int pid2 = fork();
        if (pid2 != 0)
        {
            childpid = wait(&status);
            x = x + WEXITSTATUS(status);
            printf("%d", x);
            exit(x);
        }
        else
            x = x + 5;
        printf("%d", x);
        exit(x);
    }
}

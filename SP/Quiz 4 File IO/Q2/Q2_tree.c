#include <ctype.h>
#include <dirent.h>
#include <fnmatch.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <unistd.h>

void printaTree(int livello, char * directory, int * filenum, int * dirnum, int hidden)
{
    struct dirent ** dobjects;
    int numeroOggetti = scandir(directory, &dobjects, NULL, alphasort);
    if (numeroOggetti == -1) {
        perror("scandir");
    }
    char pathnuovo[1024];
    int i;
    int checkSym = 0;
    char * buf;
    for (i = 0; i < numeroOggetti; i++) {
        if ((strcmp(dobjects[i]->d_name, ".") == 0) || (strcmp(dobjects[i]->d_name, "..") == 0))
            continue;
        if (hidden == 0) {
            if (dobjects[i]->d_name[0] == '.') {
                continue;
            }
        }

      
        if (i != numeroOggetti - 1) {
            if (livello != 0) {
                printf("%*s", livello * 4 - 1, "");
            } else {
                printf("%*s", livello, "");
            }
            printf("|-- ");
            printf("%s\n", dobjects[i]->d_name);
        }
		else
		{
		    if (livello != 0) {
		        printf("%*s", livello * 4 - 1, "");
		    } else {
		        printf("%*s", livello, "");
		    }
		    printf("`-- ");
		    printf("%s\n", dobjects[i]->d_name);
		}
		if (dobjects[i]->d_type == DT_REG) {
		    (*filenum)++;
		}
		if (dobjects[i]->d_type == DT_DIR) {
		    (*dirnum)++;
		    livello = livello + 1;
		    snprintf(pathnuovo, sizeof pathnuovo, "%s%s%s", directory, "/", dobjects[i]->d_name);
		    printaTree(livello, pathnuovo, filenum, dirnum, hidden);
		    livello = livello - 1;
		}
    }
}

int main(int argc, char * argv[])
{
    int checkgetopt;
    char * pattern;
    char * optargvalue;
    int maxHeight;
    int checkHidden = 0;
    int indice;
    int dirsindex = 0;
    char ** directories;
    directories = malloc(argc * sizeof(char *));
    for (indice = optind; indice < argc; indice++) {
        directories[dirsindex] = malloc(strlen(argv[indice]) * sizeof(char));
        strcpy(directories[dirsindex], argv[indice]);
        dirsindex++;
    }
    int livello = 0;
    int i;
    int countfiles = 0;
    int countDirs = 0;
    if (dirsindex == 0) {
        printf("%s\n", ".");
        printaTree(livello, ".", &countfiles, &countDirs, checkHidden);
    } else {
        for (i = 0; i < dirsindex; i++) {
            printf("%s\n", directories[i]);
            printaTree(livello, directories[i], &countfiles, &countDirs, checkHidden);
        }
    }
    printf("\n%d directories, %d files\n", countDirs, countfiles);
    free(directories);
    return 0;
}

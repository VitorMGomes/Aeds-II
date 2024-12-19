#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool stop(char input[])
{
    bool stop = strcmp(input, "FIM");

    return stop;
}

void alteracao(char input[], char letra1, char letra2, int len, int i)
{
    if(i < len)
    {
        if(input[i] == letra1)
        {
            input[i] = letra2;
        }

        alteracao(input, letra1, letra2, len, ++i);
    }

}

int main()
{
    char input[1000];

    scanf(" %1000[^\n]");

    while(stop(input))
    {
        char letra1 = 'a' + rand()%26;
        char letra2 = 'a' + rand()%26;

        int len = strlen(input);

        alteracao(input, letra1, letra2, len, 0);

        printf(" %s \n", input);

        scanf(" %1000[^\n]", input);
        
    }
}
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool stop(char input[])
{
    bool stop = false;
    if( strlen(input) == 3  && input[0] == 'F' && input[1] == 'I' && input[2] == 'M')
    {
        stop = true;
    }

    return stop;
}

void verificarPal(char input[])
{
    int len = strlen(input);
    bool is = true;

    for(int i = 0; i < len; i++)
    {
        if(input[i] != input[len - i - 1])
        {
            is = false;
            i = len;
        }
    }

    if(is)
    {
        puts("SIM");
    }
    else
    {
        puts("NAO");
    }
}

int main()
{
    char input[1000];

    scanf(" %1000[^\n]", input);

    while(!stop(input))
    {
        verificarPal(input);

        scanf(" %1000[^\n]", input);
    }

    return 0;
}

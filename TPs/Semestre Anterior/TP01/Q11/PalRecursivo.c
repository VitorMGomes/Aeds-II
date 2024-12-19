#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>

bool stop(char input[])
{
    bool stop = strcmp("FIM", input);

    return stop;

}

bool palindromoRec(char input[], int len, int i, bool stop)
{
    if(i < len)
    {
        if(input[i] != input[len - i - 1])
        {
            stop = false;
            i = len;
        }

        stop = palindromoRec(input, len, ++i, stop);
    }

    return stop;
}

int main()
{
    char input[1000];

    scanf(" %1000[^\n]", input);

    while(stop(input))
    {
        int len = strlen(input);

        bool is = palindromoRec(input, len, 0, true);

        if(is)
        {
            puts("SIM");
        }
        else
        {
            puts("NAO");
        }

        scanf(" %1000[^\n]", input);
    }
}
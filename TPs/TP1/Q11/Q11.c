#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool verify(char input[1000], int len, bool isPal, int pos)
{
    if(pos <= len/2)
    {
        if(input[pos] != input[len - 1 - pos])
        {
            isPal = false;
            pos = len/2 + 1;
        }

        return verify(input, len, isPal, ++pos);
    }


    return isPal;
}

bool isPal(char input[1000])
{
    return verify(input, strlen(input), true, 0);
}

int main()
{

    char input [1000];
    scanf(" %1000[^\n]", input);

    while(strcmp(input, "FIM"))
    {

        isPal(input) ? printf("SIM\n") : printf("NAO\n");

        scanf(" %1000[^\n]", input);

    }


    return 0;
}
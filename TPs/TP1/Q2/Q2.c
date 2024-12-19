#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool stop(char* input)
{   
    bool resp =  false;

    if(strlen(input) == 3 && input[0] == 'F' && input[1] == 'I' && input[2] == 'M')
    {
        resp = true;
    }

    return resp;
}

bool isPal(char* input)
{
    int len = strlen(input);
    bool resp = true;

    for(int i = 0; i < len/2; i++)
    {
        if(input[i] != input[len - 1 - i])
        {
            resp = false;
        }
    }

    return resp;
}

int main()
{
    char input [1000];

    do
    {
        //fgets(input, 1000, stdin);
        scanf(" %1000[^\n]", input);
        if(!stop(input))
        {
            puts(isPal(input) ? "SIM" : "NAO");
        }

    }while(!stop(input));
}
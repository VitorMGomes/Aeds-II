#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool stop(char input[1000])
{
    bool resp = false;

    if(strlen(input) == 3  && input[0] == 'F' && input[1] == 'I' && input[2] == 'M')
    {
        resp = true;
    }

    return resp;
}

void alterar(char input[1000] , char a, char b, int len, int pos)
{
    if(pos <= len)
    {
        if(input[pos] == a)
        {
            input[pos] = b;
        }

        return alterar(input, a, b, len, ++pos);
    }

    return;
}



int main()
{
    char input[1000];

    scanf(" %1000[^\n]", input);

    while(!stop(input))
    {
        char a = 'a' + rand()%26;
        char b = 'a' + rand()%26;


        alterar(input, a, b, strlen(input), 0);

        printf("%s\n", input);

        scanf(" %1000[^\n]", input);
    }


    return 0;
}
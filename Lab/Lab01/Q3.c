#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>



int main()
{
    char input [1000];

    scanf(" %1000[^\n]", input);

    while(strcmp(input, "FIM") != 0)
    {
        int len = strlen(input);
        int contador = 0;

        for(int i = 0; i < len; i++)
        {
            if(input[i] >= 'A' && input[i] <= 'Z')
            {
                contador++;
            }
        }

        printf("%d\n", contador);
        scanf(" %1000[^\n]", input);
    }

    return 0;
}
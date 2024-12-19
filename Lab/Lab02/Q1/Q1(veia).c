#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int main()
{
    char str1[100];
    char str2[100];

    while(scanf(" %100[^ ]", str1) != EOF)
    {

        // scanf(" %1000[^ ]", str1);

        getchar();

        scanf("%s", str2);
        
        //printf("%s", str1);
        //printf("%s", str2);

        int maior;
        int menor;
        int tam1 = strlen(str1);
        int tam2 = strlen(str2);

        if(tam1 > tam2)
        {
            maior = tam1;
        }
        else
        {
            maior = tam2;
        }

        
        for(int i = 0; i < maior; i++)
        {
            if(i < tam1)
            {
                printf("%c", str1[i]);
            }

            if(i < tam2)
            {
                printf("%c", str2[i]);
            }
        }


        printf("\n");

    }

    return 0;
}
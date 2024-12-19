#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int main()
{
    char str1[100];
    char str2[100];

    while (scanf(" %100[^ ]", str1) != EOF)
    {

        getchar();

        scanf("%s", str2);

        int tam1 = strlen(str1);
        int tam2 = strlen(str2);


        int i = 0;
        while (i < tam1 || i < tam2)
        {
            if (i < tam1)
            {
                printf("%c", str1[i]);
            }

            if (i < tam2)
            {
                printf("%c", str2[i]);
            }

            i++;
        }

        printf("\n");
    }

    return 0;
}
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int main()
{
    int entrada;
    while (scanf(" %d", &entrada) != EOF)
    {
        int arrayIni[entrada];

        for (int i = 0; i < entrada; i++)
        {
            scanf(" %d", &arrayIni[i]);
        }

        int arrayFim[entrada];

        for (int i = 0; i < entrada; i++)
        {
            scanf(" %d", &arrayFim[i]);
        }

        int movInit = 0;

        for (int i = 0; i < entrada - 1; i++)
        {
            for (int j = 0; j < entrada - i - 1; j++)
            {
                if (arrayIni[j] < arrayIni[j + 1]) // ordenando em ordem decrescente
                {
                    int temp = arrayIni[j];
                    arrayIni[j] = arrayIni[j + 1];
                    arrayIni[j + 1] = temp;
                    movInit++;
                }
            }
        }

        int movFim = 0;

        for (int i = 0; i < entrada - 1; i++)
        {
            for (int j = 0; j < entrada - i - 1; j++)
            {
                if (arrayFim[j] < arrayFim[j + 1]) // ordenando em ordem decrescente
                {
                    int temp = arrayFim[j];
                    arrayFim[j] = arrayFim[j + 1];
                    arrayFim[j + 1] = temp;
                    movFim++;
                }
            }
        }

        int resp = movFim - movInit;
        if(resp < 0)
        {
            resp = resp * (-1);
        }

        printf("%d\n", resp);
    }
}
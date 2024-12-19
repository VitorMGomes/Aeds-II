#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int main()
{
    int rep;
    int posicaoCorte;

    scanf("%d %d", &rep, &posicaoCorte);
    getchar();

    int listaNotas[rep];

    for(int i = 0; i < rep; i++)
    {
        scanf("%d", &listaNotas[i]);
        getchar();
    }


    //bubble sort
    for(int i = 0; i < rep - 1; i++)
    {
        for(int j = 0; j < rep - i - 1; j++)
        {
            if(listaNotas[j] < listaNotas[j+1])//ordenando em ordem decrescente
            {
                int temp = listaNotas[j];
                listaNotas[j] = listaNotas[j+1];
                listaNotas[j+1] = temp;
            }
        }
    }

    printf("%d\n", listaNotas[posicaoCorte - 1]);
    


}

//gcc -o main main.c; ./main < pub.in
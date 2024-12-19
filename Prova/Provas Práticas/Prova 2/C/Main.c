#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

struct Presente
{
    char nome[50];
    float preco;
    int prioridade;
    
}typedef Presente;

int compare(Presente array[], int i, int j)
{
    int resp = 0;

    if(array[i].prioridade < array[j].prioridade)
    {
        resp = 1;
    }
    else if(array[i].prioridade > array[j].prioridade)
    {
        resp = -1;
    }

    if(resp == 0)
    {
        if(array[i].preco > array[j].preco)
        {
            resp = 1;
        }
        if(array[i].preco < array[j].preco)
        {
            resp = -1;
        }
    }

    if(resp == 0)
    {
        resp = strcmp(array[i].nome, array[j].nome);
    }

    //printf("%d", resp);

    return resp;
}

int main()
{
    char name[50];

    while(scanf(" %s", name) != EOF)
    {
        int n; //quantidade de itens na lista

        scanf(" %d", &n);
        //printf("%d", n);

        Presente presentes[n];

        for(int i = 0; i < n; i++)
        {
            scanf(" %1000[^\n]", presentes[i].nome);
            scanf(" %f", &presentes[i].preco);
            scanf(" %d", &presentes[i].prioridade);
            getchar();
        }
        
        for(int i = 0; i < n - 1; i++)
        {
            for(int j = 0; j < n - 1 - i; j++)
            {
                if(compare(presentes, j, j + 1) == 1)
                {
                    Presente tmp = presentes[j];
                    presentes[j] = presentes[j + 1];
                    presentes[j + 1] = tmp;
                }
            }
        }

        printf("Lista de %s\n", name);

        for(int i = 0; i < n; i++)
        {
            printf("%s - R$%.2f\n", presentes[i].nome, presentes[i].preco);
        }
        printf("\n");









        // for(int i = 0; i < n; i++)
        // {
        //     printf("%s\n", presentes[i].nome);
        //     printf("%.2f\n", presentes[i].preco);
        //     printf("%d\n", presentes[i].prioridade);
        //     printf("\n");
        // }
    }

}
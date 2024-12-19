#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>



struct pokemon
{
    char nome[1000];
}
typedef pokemon;

pokemon lista[151];
int fim = 0;

void addLista(pokemon x)
{
    lista[fim] = x;
    fim++;
}

bool presente(char nome[1000])
{
    bool is = false;

    for(int i = 0; i < fim; i++)
    {
        if(strcmp(nome, lista[i].nome) == 0)
        {
            is = true;
            i = fim;
        }
    }

    return is;
}


int main()
{
    int rep;
    scanf("%d", &rep);

    getchar();


    for(int i = 0; i < rep; i++)
    {
        pokemon poke;
        scanf(" %1000[^\n]", poke.nome);
        getchar();

        if(!presente(poke.nome))
        {
            addLista(poke);
        }

    }

    printf("Falta(m) %d pomekon(s).", (151 - fim));
}

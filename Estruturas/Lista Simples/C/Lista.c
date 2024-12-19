#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

typedef struct List
{
    int *array;
    int n;
    int MAXTAM;

}List;

void startList(List* lista, int tam)
{
    lista->MAXTAM = tam;
    lista-> n = 0;  
    lista->array = (int*) malloc(sizeof(int) * tam);
    //por que não somente - malloc(sizeof(int) * tam);
}

void inserirInicio(List* lista, int x)
{
    if(lista->n == lista->MAXTAM)
    {
        puts("Lista cheia");
        exit(1);
    }

    for(int i = lista->n; i > 0; i--)
    {
        lista->array[i] = lista->array[i- 1];
    }

    lista->array[0] = x;
    lista->n++;
}

void inserirFim(List* lista ,int x)
{
    if(lista->n == lista->MAXTAM)
    {
        puts("Lista cheia");
        exit(1);
    }

    lista->array[lista->n] = x;
    lista->n++;

}

void inserir(List* lista, int pos, int x)
{
    if(lista->n == lista->MAXTAM)
    {
        puts("Lista cheia");
        exit(1);
    }

    if(0 > pos || pos <=lista->n)
    {
        puts("Posição inválida");
        exit(1);
    }

    for(int i = lista->n; i > pos; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[pos] = x;
    lista->n++;
}

int removerIncio()
{

}

int removerFim()
{

}

int remover(int pos)
{

}

void mostrar(List* lista)
{
    for(int i = 0; i < = n)
}


int main()
{

}
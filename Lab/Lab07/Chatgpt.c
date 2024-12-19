#include <stdio.h>
#include <stdlib.h>

// Função auxiliar para contar as inversões usando o algoritmo de merge sort
int merge_and_count(int arr[], int temp[], int left, int mid, int right)
{
    int i, j, k;
    int inv_count = 0;

    i = left;  // Índice da sublista esquerda
    j = mid + 1; // Índice da sublista direita
    k = left;  // Índice do array temporário

    while ((i <= mid) && (j <= right))
    {
        if (arr[i] <= arr[j])
        {
            temp[k++] = arr[i++];
        }
        else
        {
            temp[k++] = arr[j++];
            inv_count += (mid - i + 1); // Contando inversões
        }
    }

    while (i <= mid)
        temp[k++] = arr[i++];

    while (j <= right)
        temp[k++] = arr[j++];

    for (i = left; i <= right; i++)
        arr[i] = temp[i];

    return inv_count;
}

int merge_sort_and_count(int arr[], int temp[], int left, int right)
{
    int mid, inv_count = 0;
    if (right > left)
    {
        mid = (right + left) / 2;

        inv_count += merge_sort_and_count(arr, temp, left, mid);
        inv_count += merge_sort_and_count(arr, temp, mid + 1, right);
        inv_count += merge_and_count(arr, temp, left, mid, right);
    }
    return inv_count;
}

// Função para contar inversões no array
int count_inversions(int arr[], int n)
{
    int temp[n];
    return merge_sort_and_count(arr, temp, 0, n - 1);
}

int main()
{
    int entrada;
    while (scanf("%d", &entrada) != EOF)
    {
        int arrayIni[entrada], arrayFim[entrada], posIni[entrada];

        // Lendo o grid de largada
        for (int i = 0; i < entrada; i++)
        {
            scanf("%d", &arrayIni[i]);
        }

        // Lendo o grid de chegada
        for (int i = 0; i < entrada; i++)
        {
            scanf("%d", &arrayFim[i]);
        }

        // Criando um array que mapeia a posição dos competidores no grid de largada
        int index_map[entrada + 1]; // +1 para acomodar os competidores numerados de 1 a N
        for (int i = 0; i < entrada; i++)
        {
            index_map[arrayIni[i]] = i; // Posiciona o competidor i
        }

        // Transformando o grid de chegada para posições no grid de largada
        for (int i = 0; i < entrada; i++)
        {
            posIni[i] = index_map[arrayFim[i]];
        }

        // Calculando o número de inversões no grid transformado
        int inversions = count_inversions(posIni, entrada);

        printf("%d\n", inversions);
    }

    return 0;
}

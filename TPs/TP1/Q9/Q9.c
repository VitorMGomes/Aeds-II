#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE *arq;
    arq = fopen("file.txt", "wb");

    int quantidade;
    double result;

    scanf("%i", &quantidade);

    for(int i = 0; i < quantidade; i++)
    {
        scanf("%lf", &result);
        fwrite(&result, sizeof(double), 1,arq);
    }

    fclose(arq);

    arq = fopen("file.txt", "rb");

    for(int i = quantidade - 1; i >= 0; i--)
    {
        fseek(arq, i * sizeof(double), SEEK_SET);
        double resultD;
        fread(&resultD, sizeof(double), 1, arq);

        int resultI = (int) resultD;

        if(resultI == resultD)
        {
            printf("%i\n", resultI);
        }
        else
        {
            printf("%g\n", resultD);
        }

    }

    fclose(arq);

    return 0;
}
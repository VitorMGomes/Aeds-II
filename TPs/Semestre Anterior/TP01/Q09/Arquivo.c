#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE *arq; //declaração do arquivo
    arq = fopen("texto.txt", "wb");//executa o fopen(abre o arquivo) e seta para escrever nele

    int quantidade;//define a quantidade de numeros a serem lidos
    double result;//ja cria a variavel para os resultados

    scanf("%i", &quantidade);

    for(int i = 0; i < quantidade; i++)
    {
        scanf("%lf", &result);
        fwrite(&result, sizeof(double), 1,arq);//escreve o numero no arquivo
    }

    fclose(arq);//fecha o arquivo

    arq = fopen("texto.txt", "rb");//abre o arquivo e seta para ler

    for(int i = quantidade - 1; i >= 0; i--)
    {
        fseek(arq, i * sizeof(double), SEEK_SET);//busca o byte de cada numero double, seguindo o for de tras pra frente
        double resultD;
        fread(&resultD, sizeof(double), 1, arq);//le o double na posição do byte

        int resultI = (int) resultD;//por meio de TypeCasting transforma o double em int

        if(resultI == resultD)// se for igual, imprime o valor inteiro, se não, o double
        {
            printf("%i\n", resultI);
        }
        else
        {
            printf("%g\n", resultD);//%g escolhe automaticamente o melhor entre %f e %e
        }

    }

    fclose(arq);//fecha o arquivo

    return 0;
}
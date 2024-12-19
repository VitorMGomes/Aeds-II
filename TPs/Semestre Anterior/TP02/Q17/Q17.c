#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#include <time.h>

#define sTAM 200
#define aNTAM 300

int comparacoes = 0;
int movimentacoes = 0;

bool toBool(char *input)
{
    return strcmp(input, "VERDADEIRO") == 0;
}

typedef struct Data
{
    int dia, mes, ano;
} Data;

struct Character
{
    char id[sTAM];
    char name[sTAM];
    char alternativeNames[aNTAM];
    char house[sTAM];
    char ancestry[sTAM];
    char species[sTAM];
    char patronus[sTAM];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[sTAM];
    bool alive;
    Data dateOfBirth;
    int yearOfBirth;
    char eyeColour[sTAM];
    char gender[sTAM];
    char hairColour[sTAM];
    bool wizard;
};
typedef struct Character Character;

void freeSplit(char **array)
{
    int i;
    for (i = 0; strcmp(array[i], "cFIM"); i++)
    {
        free(array[i]);
    }

    free(array[i]);

    free(array);
}

char **split(char *regex, char *string)
{

    int len = strlen(string);
    int n = 1;

    for (int i = 0; i < len; i++)
    {
        if (string[i] == regex[0])
            n++;
    }

    char **array = (char **)malloc((n + 1) * sizeof(char *));

    for (int i = 0; i < n + 1; i++)
    {
        array[i] = calloc(sTAM, sizeof(char *));
    }

    strcpy(array[n], "cFIM");

    strcpy(array[0], strsep(&string, regex));

    for (int i = 1; i < n; i++)
    {
        char *temp = strsep(&string, regex);
        strcpy(array[i], temp);
    }

    return array;
}

void setId(char id[], Character *x)
{
    strcpy(x->id, id);
}

void setName(char name[], Character *x)
{
    strcpy(x->name, name);
}

void setAlternativeNames(char alternativeNames[], Character *x)
{
    int tam = strlen(alternativeNames);

    alternativeNames[0] = '{';
    alternativeNames[tam - 1] = '}';

    strcpy(x->alternativeNames, alternativeNames);
}

void setHouse(char house[], Character *x)
{
    strcpy(x->house, house);
}

void setAncestry(char ancestry[], Character *x)
{
    strcpy(x->ancestry, ancestry);
}
void setSpecies(char species[], Character *x)
{
    strcpy(x->species, species);
}
void setPatronus(char patronus[], Character *x)
{
    strcpy(x->patronus, patronus);
}
void setHogwartsStaff(char staff[], Character *x)
{
    x->hogwartsStaff = toBool(staff);
    // x->hogwartsStaff = false;
}
void setHogwartsStudent(char student[], Character *x)
{
    x->hogwartsStudent = toBool(student);
    // x->hogwartsStudent = false;
}
void setActorName(char actorName[], Character *x)
{
    strcpy(x->actorName, actorName);
}
void setAlive(char alive[], Character *x)
{
    x->alive = toBool(alive);
    // x->alive = false;
}
void setDateOfBirth(char dateOfBirth[], Character *x)
{
    char **array = split("-", dateOfBirth);

    x->dateOfBirth.dia = atoi(array[0]);
    x->dateOfBirth.mes = atoi(array[1]);
    x->dateOfBirth.ano = atoi(array[2]);

    freeSplit(array);
}
void setYearOfBirth(char yearOfBirth[], Character *x)
{
    x->yearOfBirth = atoi(yearOfBirth);
}
void setEyeColour(char eyeColour[], Character *x)
{
    strcpy(x->eyeColour, eyeColour);
}
void setGender(char gender[], Character *x)
{
    strcpy(x->gender, gender);
}
void setHairColour(char hairColour[], Character *x)
{
    strcpy(x->hairColour, hairColour);
}
void setWizard(char wizard[], Character *x)
{
    x->wizard = toBool(wizard);
}

Character ler(char *string)
{

    // char aux[2000];
    // strcpy(aux, string);
    char **array = split(";", string);

    int pos = 0;
    Character x;

    setId(array[pos++], &x);

    setName(array[pos++], &x);

    setAlternativeNames(array[pos++], &x);

    setHouse(array[pos++], &x);

    setAncestry(array[pos++], &x);

    setSpecies(array[pos++], &x);

    setPatronus(array[pos++], &x);

    setHogwartsStaff(array[pos++], &x);

    setHogwartsStudent(array[pos++], &x);

    setActorName(array[pos++], &x);

    setAlive(array[pos++], &x);

    // Para pular o atributo não usado
    pos++;

    setDateOfBirth(array[pos++], &x);

    setYearOfBirth(array[pos++], &x);

    setEyeColour(array[pos++], &x);

    setGender(array[pos++], &x);

    setHairColour(array[pos++], &x);

    setWizard(array[pos++], &x);

    freeSplit(array);

    return x;
}

// bool toBool(char *input)
// {
//     return strcmp(input, "VERDADEIRO") == 0;
// }

void printarAlternativeNames(char *string)
{
    int len = strlen(string);
    for (int i = 0; i < len; i++)
    {
        if (string[i] != '\'')
            printf("%c", string[i]);
    }
    printf(" ## ");
}

void status(Character x)
{
    printf("[%s ## ", x.id);
    printf("%s ## ", x.name);
    printarAlternativeNames(x.alternativeNames);
    printf("%s ## ", x.house);
    printf("%s ## ", x.ancestry);
    printf("%s ## ", x.species);
    printf("%s ## ", x.patronus);
    printf("%s ## ", x.hogwartsStaff ? "true" : "false");
    printf("%s ## ", x.hogwartsStudent ? "true" : "false");
    printf("%s ## ", x.actorName);
    printf("%s ## ", x.alive ? "true" : "false");
    printf("%02d-%02d-%d ## ", x.dateOfBirth.dia, x.dateOfBirth.mes, x.dateOfBirth.ano);
    printf("%d ## ", x.yearOfBirth);
    printf("%s ## ", x.eyeColour);
    printf("%s ## ", x.gender);
    printf("%s ## ", x.hairColour);
    printf("%s]", x.wizard ? "true" : "false");
    printf("\n");
}

bool stop(char *id)
{
    return strcmp(id, "FIM") != 0;
}

void importDB(Character lista[], char *fileName)
{
    FILE *arq = fopen(fileName, "r");

    if (arq == NULL)
    {
        printf("ERRO NO ARQUIVO\n");
        exit(1);
    }

    char line[2000];

    fgets(line, 2000, arq);

    for (int i = 0; fgets(line, 2000, arq); i++)
    {
        int pos = (int)strcspn(line, "\n\r");
        line[pos] = '\0';
        lista[i] = ler(line);
        // status(lista[i]);
        // printf("Linha: %s\n", line);
    }

    fclose(arq);
}

char *readString(char *string)
{
    scanf("%s", string);
    return string;
}

Character pesquisaSequencial(char *id, Character *array)
{
    for (int i = 0; i < 404; i++)
    {
        if (strcmp(array[i].id, id) == 0)
            return array[i];
    }
}

int compararString(char *x, char *y)
{
    int LenX = strlen(x), LenY = strlen(y);
    int i = 0, minLength = (LenX < LenY) ? LenX : LenY;
    while (i < minLength && x[i] == y[i])
    {
        i++;
    }
    if (i == minLength)
    {
        return (LenX < LenY);
    }
    else
    {
        return x[i] < y[i];
    }
}

void swap(Character array[], int i, int j)
{
    Character temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    movimentacoes+=3;
}

    void constroi(Character *personagem, int tamHeap, int i) {
        while (i > 1) {
            int pai = i / 2;
            comparacoes+=2;
            if (strcmp(personagem[i].hairColour, personagem[pai].hairColour) > 0 || 
            (strcmp(personagem[i].hairColour, personagem[pai].hairColour) == 0 &&
                strcmp(personagem[i].name, personagem[pai].name) > 0)) {
                swap(personagem, i, pai);
                i = pai;
            } else {
                break;
            }
        }
    }

    int getMaiorFilho(Character *wizard, int i, int tamHeap) {
        int filhoEsq = 2 * i;
        int filhoDir = 2 * i + 1;
        if (filhoDir > tamHeap) return filhoEsq; // Sem filho direito
        if (strcmp(wizard[filhoEsq].hairColour, wizard[filhoDir].hairColour) > 0 || 
        (strcmp(wizard[filhoEsq].hairColour, wizard[filhoDir].hairColour) == 0 &&
            strcmp(wizard[filhoEsq].name, wizard[filhoDir].name) > 0)) {
            return filhoEsq;
        } else {
            return filhoDir;
        }
    }

    void reconstroi(Character *wizard, int tamHeap) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(wizard, i, tamHeap);
            comparacoes+=3;
            if (strcmp(wizard[i].hairColour, wizard[filho].hairColour) < 0 ||
            (strcmp(wizard[i].hairColour, wizard[filho].hairColour) == 0 &&
                strcmp(wizard[i].name, wizard[filho].name) < 0)) {
                swap(wizard, i, filho);
                i = filho;
            } else {
                break;
            }
        }
    }

    void heapsortParcial(Character *wizard, int n) {
        Character* tmp = (Character*) malloc((n+1) * sizeof(Character));
        for (int i = 0; i < n; i++) {
            tmp[i+1] = wizard[i];
        }

        int k = 10;
        for (int tamHeap = 2; tamHeap <= k; tamHeap++) {
            constroi(tmp, tamHeap, tamHeap);
        }

        for (int i = k + 1; i <= n; i++) {
            if (strcmp(tmp[i].hairColour, tmp[1].hairColour) < 0) {
                swap(tmp, i, 1);
                reconstroi(tmp, 10);
            }
        }

        int tamHeap = k;
        while (tamHeap > 1) {
            swap(tmp, 1, tamHeap);
            tamHeap--;
            reconstroi(tmp, tamHeap);
        }

        for (int i = 0; i < n; i++) {
            wizard[i] = tmp[i+1];
        }
        free(tmp);
    }

// gcc programa.c -o programa
// ./programa < entrada.in > saida.out

int main()
{

    Character listaCSV[404];

    importDB(listaCSV, "/tmp/characters.csv");
    char id[100];

    Character wizard[404];

    int n = 0;
    for (; stop(readString(id)); n++)
    {
        wizard[n] = pesquisaSequencial(id, listaCSV);
        // status(wizard[n]);
    }

    int i = 0;
    clock_t inicio = clock();
    heapsortParcial(wizard, n);
    clock_t final = clock();

    for (int i = 0; i < 10; i++)
    {
        status(wizard[i]);
    }

    double tempo = ((double)(final - inicio)) / CLOCKS_PER_SEC;

    FILE *fw = fopen("800643_heapParcial.txt", "w");
    if (fw != NULL)
    {
        fprintf(fw, "Matrícula: 800643 |\tTempo: %.5fs |\tComparações: %d |\tMovimentações: %d\n", tempo, comparacoes, movimentacoes);
        fclose(fw);
    }
    else
    {
        printf("Erro ao abrir o arquivo.\n");
    }
}
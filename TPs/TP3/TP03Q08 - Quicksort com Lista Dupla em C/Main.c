#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>

int com(char *s)
{
    bool find = false;
    int i = 0;
    for (; i < strlen(s) && !find; i++)
    {
        if (s[i] == '.')
            find = true;
    }
    return i - 1;
}

double parseDouble(char *s)
{
    int i = 0;
    double result = 0.0;
    int leng = strlen(s);
    int comma = com(s),
        amtLeft = leng - comma + 2,
        j = comma - 1;
    for (i = 0; i < comma; i++)
    {
        result += (double)((s[i] - 48) * pow(10, j));
        j--;
    }
    j = 1;
    for (i = i + 1; i < leng; i++)
    {
        result += (double)((s[i] - 48) / pow(10, j));
        j++;
    }
    return result;
}

int parseYear(char *s)
{
    int result = 0;
    int exp = 3;
    int i = 0;
    while (i < 4)
    {
        result += (int)(s[i] - 48) * (int)pow(10.0, (double)exp);
        i++;
        exp--;
    }
    return result;
}

int parseInt(char *s)
{
    int result = 0;
    int i = strlen(s) - 1,
        exp = 0;
    while (i >= 0)
    {
        result += (int)(s[i] - 48) * (int)pow(10.0, (double)exp);
        i--;
        exp++;
    }
    return result;
}

typedef struct
{
    int day;
    int month;
    int year;
} Date;

Date printdate(char *s)
{
    char *day = strtok(s, "/");
    char *mes = strtok(NULL, "/");
    char *yar = strtok(NULL, "/");
    Date result;
    result.day = parseInt(day);
    result.month = parseInt(mes);
    result.year = parseYear(yar);
    return result;
}

char *dateSTR(Date i)
{
    char *result = malloc(40 * sizeof(char));
    sprintf(result, "%02d/%02d/%04d", i.day, i.month, i.year);
    return result;
}

typedef struct
{
    int n;
    char type1[80];
    char type2[80];
} Types;

typedef struct
{
    int n;
    char ab1[80];
    char ab2[80];
    char ab3[80];
    char ab4[80];
    char ab5[80];
    char ab6[80];
} Abilities;

typedef struct
{
    int id;
    int generation;
    char name[80];
    char description[80];
    Types types;
    Abilities abilities;
    double weight;
    double height;
    int captureRate;
    bool legendary;
    Date captureDate;
} Pokemon;

Pokemon *makeMon(char *line)
{
    Pokemon *result = calloc(1, sizeof(Pokemon));

    int leng = strlen(line);
    char *format = malloc(leng * sizeof(char));
    bool control = true;
    int j = 0;
    for (int i = 0; i < leng; i++)
    {
        if (line[i] == '"')
            control = !control;
        else if (line[i] == ',' && control)
            format[j++] = ';';
        else if (line[i] != '[' && line[i] != ']')
            format[j++] = line[i];
    }

    char **aux = calloc(12, sizeof(char *));
    int nSplits = 0;
    for (aux[nSplits++] = strtok(format, ";"); aux[nSplits] = strtok(NULL, ";"); nSplits++);
    result->id = parseInt(aux[0]);
    result->generation = parseInt(aux[1]);
    strcpy(result->name, aux[2]);
    strcpy(result->description, aux[3]);
    result->types.n = nSplits == 11 ? 1 : 2;
    strcpy(result->types.type1, aux[4]);
    if (result->types.n == 2)
        strcpy(result->types.type2, aux[5]);

    bool commaInAbilities = false;
    int posAbs = nSplits == 10 ? 6 : nSplits == 11 ? 5
                                                   : 6;
    for (int j = 0; j < strlen(aux[posAbs]); j++)
        if (aux[posAbs][j] == ',')
            commaInAbilities = true;
    if (!commaInAbilities)
    {
        result->abilities.n = 1;
        strcpy(result->abilities.ab1, aux[posAbs]);
    }
    else
    {

        char *formt = malloc(80 * sizeof(char));
        strcpy(formt, aux[posAbs]);
        for (int x = 0; x < strlen(formt); x++)
            if (formt[x] == ' ' && formt[x - 1] == ',')
                formt[x] = ',';

        char *buffer;
        buffer = strtok(formt, ",");
        result->abilities.n = 1;
        strcpy(result->abilities.ab1, buffer);

        if (buffer = strtok(NULL, ","))
        {
            result->abilities.n = 2;
            strcpy(result->abilities.ab2, buffer);

            if (buffer = strtok(NULL, ","))
            {
                result->abilities.n = 3;
                strcpy(result->abilities.ab3, buffer);

                if (buffer = strtok(NULL, ","))
                {
                    result->abilities.n = 4;
                    strcpy(result->abilities.ab4, buffer);

                    if (buffer = strtok(NULL, ","))
                    {
                        result->abilities.n = 5;
                        strcpy(result->abilities.ab5, buffer);

                        if (buffer = strtok(NULL, ","))
                        {
                            result->abilities.n = 6;
                            strcpy(result->abilities.ab6, buffer);
                        }
                    }
                }
            }
        }
    }

    if (nSplits > 10)
    {
        result->weight = parseDouble(aux[7 - (12 - nSplits)]);
        result->height = parseDouble(aux[8 - (12 - nSplits)]);
    }
    else
    {
        result->weight = 0.0;
        result->height = 0.0;
    }

    result->captureRate = parseInt(aux[9 - (12 - nSplits)]);

    if (strcmp(aux[10 - (12 - nSplits)], "1") == 0)
        result->legendary = true;
    else
        result->legendary = false;

    result->captureDate = printdate(aux[11 - (12 - nSplits)]);

    return result;
}

void printMon(Pokemon *poke)
{
    printf("[#%d -> %s: %s - [", poke->id, poke->name, poke->description);

    if (poke->types.n == 1)
        printf("'%s'] - [", poke->types.type1);
    else
        printf("'%s', '%s'] - [", poke->types.type1, poke->types.type2);

    if (poke->abilities.n == 1)
        printf("%s] - ", poke->abilities.ab1);
    else if (poke->abilities.n == 2)
        printf("%s, %s] - ", poke->abilities.ab1, poke->abilities.ab2);
    else if (poke->abilities.n == 3)
        printf("%s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3);
    else if (poke->abilities.n == 4)
        printf("%s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4);
    else if (poke->abilities.n == 5)
        printf("%s, %s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4, poke->abilities.ab5);
    else if (poke->abilities.n == 6)
        printf("%s, %s, %s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4, poke->abilities.ab5, poke->abilities.ab6);

    printf("%.1lfkg - %.1lfm - %d%c - ", poke->weight, poke->height, poke->captureRate, '%');

    if (poke->legendary)
        printf("true ");
    else
        printf("false ");

    printf("- %d gen] - ", poke->generation);

    char *capDate = dateSTR(poke->captureDate);
    printf("%s\n", capDate);
}

Pokemon **readFile(const char *path)
{
    Pokemon **result = calloc(801, sizeof(Pokemon *));
    FILE *csv = fopen(path, "rt");
    char *trash = malloc(1000 * sizeof(char));
    fgets(trash, 999, csv);
    free(trash);
    for (int i = 0; i < 801; i++)
    {
        char *buffer = malloc(1000 * sizeof(char));
        fgets(buffer, 999, csv);
        result[i] = makeMon(buffer);
        free(buffer);
    }
    fclose(csv);
    return result;
}
//------------------------------------------------------------------------------

double diff(clock_t start, clock_t end)
{
    return ((double)(end - start)) / CLOCKS_PER_SEC;
}

void logTP(const char *fileName, double time, int comps, int moves)
{
    FILE *fil = fopen(fileName, "w");
    if (fil)
    {
        fprintf(fil, "Matrícula: 800643\t");
        fprintf(fil, "Tempo de Execução: %lf\t", time);
        fprintf(fil, "Comparações: %d\t", comps);
        fprintf(fil, "Movimentações: %d", moves);
        fclose(fil);
    }
    else
        printf("Erro ao abrir o arquivo");
}

void logSearch(const char *fileName, double time, int comps)
{
    FILE *fil = fopen(fileName, "w");
    if (fil)
    {
        fprintf(fil, "Matrícula: 800643\t");
        fprintf(fil, "Tempo de Execução: %lf\t", time);
        fprintf(fil, "Comparações: %d", comps);
        fclose(fil);
    }
    else
        printf("Erro ao abrir o arquivo");
}
//------------------------------------------------------------------------------

int comps = 0;
int moves = 0;

typedef struct Cell Cell;
typedef struct Cell
{
    Pokemon *p;
    Cell *next;
    Cell *prev;
} Cell;

Cell *makeCell(Pokemon *x)
{
    Cell *ret = calloc(1, sizeof(Cell));
    ret->next = NULL;
    ret->prev = NULL;
    ret->p = x;
    return ret;
}

typedef struct
{
    int n;
    Cell *start;
    Cell *end;
} Lista;

void delList(Cell *this)
{
    if (this->next)
        delList(this->next);
    free(this);
}

void delLista(Lista *l)
{
    delList(l->start);
    free(l);
}

void printLista(Lista *l)
{
    Cell *tmp = l->start->next;
    while (tmp)
    {
        printMon(tmp->p);
        tmp = tmp->next;
    }
}

Lista *makeLista(int x)
{
    Lista *ret = calloc(1, sizeof(Lista));
    ret->start = makeCell(NULL);
    ret->end = ret->start;
    ret->n = 0;
    return ret;
}

Pokemon *pop(Lista *l, int x)
{
    Pokemon *ret = NULL;
    Cell *tmp = l->start->next;
    for (int i = 0; i < x && tmp; i++)
    {
        tmp = tmp->next;
    }
    if (tmp && l->n > 0)
    {
        tmp->prev->next = tmp->next;
        tmp->next->prev = tmp->prev;
        tmp->next = NULL;
        tmp->prev = NULL;

        ret = tmp->p;
        free(tmp);
        l->n--;
        printf("(R) %s\n", ret->name);
    }
    return ret;
}

Pokemon *popEnd(Lista *l)
{
    Pokemon *ret = NULL;
    Cell *tmp = l->end;
    if (l->n >= 1)
    {
        l->end = tmp->prev;
        tmp->prev->next = NULL;
        tmp->next = NULL;
        tmp->prev = NULL;

        ret = tmp->p;
        free(tmp);
        l->n--;
        printf("(R) %s\n", ret->name);
    }
    return ret;
}

Pokemon *popStart(Lista *l)
{
    Pokemon *ret = NULL;
    Cell *tmp = l->start;
    if (l->n >= 1)
    {
        tmp = tmp->next;
        tmp->next->prev = tmp->prev;
        tmp->prev->next = tmp->next;
        tmp->prev = NULL;
        tmp->next = NULL;

        ret = tmp->p;
        free(tmp);
        l->n--;
        printf("(R) %s\n", ret->name);
    }
    return ret;
}

void push(Lista *l, Pokemon *p, int x)
{
    int i = 0;
    Cell *b1 = l->start;
    while (i < x && b1)
    {
        b1 = b1->next;
        i++;
    }
    if (b1)
    {
        Cell *b2 = b1->next;
        Cell *in = makeCell(p);
        b1->next = in;
        b2->prev = in;
        in->prev = b1;
        in->next = b2;
        l->n++;
    }
}

void pushEnd(Lista *l, Pokemon *p)
{
    Cell *in = makeCell(p);
    Cell *tmp = l->end;
    tmp->next = in;
    in->prev = tmp;
    l->n++;
    l->end = in;
}

void pushStart(Lista *l, Pokemon *p)
{
    Cell *in = makeCell(p);
    Cell *tmp = l->start->next;
    l->start->next = in;
    tmp->prev = in;
    in->prev = l->start;
    in->next = tmp;
    l->n++;
}

Pokemon *get(Lista *l, int n)
{
    Cell *tmp = l->start;
    int i = 0;
    while (i <= n && tmp->next)
    {
        i++;
        tmp = tmp->next;
    }
    return tmp->p;
}

void set(Lista *l, int n, Pokemon *p)
{
    int i = 0;
    Cell *tmp = l->start;
    while (i <= n && tmp->next)
    {
        i++;
        tmp = tmp->next;
    }
    tmp->p = p;
}

void swapPoke(Lista *l, int a, int b)
{
    Pokemon *tmp = get(l, a);
    set(l, a, get(l, b));
    set(l, b, tmp);
    moves += 3;
}

void quickSort(Lista *l, int left, int right)
{
    int i = left,
        j = right;
    Pokemon *pivo = get(l, (i + j) / 2);
    while (i <= j)
    {
        while (get(l, i)->generation < pivo->generation || (get(l, i)->generation == pivo->generation && 0 > strcmp(get(l, i)->name, pivo->name)))
        {
            i++;
            comps++;
        }
        while (get(l, j)->generation > pivo->generation || (get(l, j)->generation == pivo->generation && 0 < strcmp(get(l, j)->name, pivo->name)))
        {
            j--;
            comps++;
        }
        if (i <= j)
        {
            swapPoke(l, i, j);
            i++;
            j--;
        }
    }
    if (left < j)
        quickSort(l, left, j);
    if (i < right)
        quickSort(l, i, right);
}

int getListSize(Lista *l)
{
    int i = 0;
    Cell *c = l->start->next;
    while (c)
    {
        i++;
        c = c->next;
    }
    return i;
}

void sort(Lista *l)
{
    quickSort(l, 0, getListSize(l) - 1);
}

//------------------------------------------------------------------------------

bool equals(const char *s1, const char *s2)
{
    return 0 == strcmp(s1, s2);
}

void swapInt(int *arr, int a, int b)
{
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
}

bool shouldSwap(char *a, char *b)
{
    bool result = false;
    int i = -1;
    do
    {
        i++;
        if (a[i] > b[i])
            result = true;
    } while (a[i] == b[i]);
    return result;
}

int main()
{
    Pokemon **pokes = readFile("/tmp/pokemon.csv");

    int x = 0;
    Lista *l = makeLista(100);
    char *input = malloc(20 * sizeof(char));
    scanf(" %s", input);
    while (!equals(input, "FIM"))
    {
        x = parseInt(input);
        pushEnd(l, pokes[x - 1]);
        free(input);
        input = malloc(20 * sizeof(char));
        scanf(" %s", input);
    }
    free(input);

    clock_t start = clock();
    sort(l);
    clock_t end = clock();
    printLista(l);

    delLista(l);
    logTP("800643_quicksort3.txt", diff(start, end), comps, moves);
    return 0;
}
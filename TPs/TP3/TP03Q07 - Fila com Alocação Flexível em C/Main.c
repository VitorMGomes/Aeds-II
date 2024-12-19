#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <err.h>
#include <time.h>

bool toBool(char *input)
{
    return strcmp(input, "VERDADEIRO") == 0;
}

typedef struct Date
{
    int dia, mes, ano;
} Date;

typedef struct Types
{
    char type1[20];
    char type2[20];

} Types;

typedef struct Pokemon
{
    int id;
    int generation;
    char name[20];
    char description[40];
    Types types;
    char abilities[60];
    int nAbilities;
    double weight;
    double height;
    int captureRate;
    // bool legendary;
    char legendary[80];
    Date captureDate;
} Pokemon;

Pokemon pokemons[1000];

void printPokemon(Pokemon pokemons)
{
    printf("[#%d -> %s: %s - [%s%s] - [%s] - %.1fkg - %.1fm - %d%% - %s - %d gen] - %02d/%02d/%d\n",
           pokemons.id,          // ID
           pokemons.name,        // Nome
           pokemons.description, // Descrição
           pokemons.types.type1, // Tipos
           pokemons.types.type2,
           pokemons.abilities,        // Habilidades
           pokemons.weight,           // Peso
           pokemons.height,           // Altura
           pokemons.captureRate,      // Taxa de Captura
           pokemons.legendary,        // Legendary (0 ou 1)
           pokemons.generation,       // Geração
           pokemons.captureDate.dia,  // Data de Captura
           pokemons.captureDate.mes,  // Data de Captura
           pokemons.captureDate.ano); // Data de Captura
}

void freeSplit(char **papas)
{
    int i;
    for (i = 0; strcmp(papas[i], "cFIM"); i++)
    {
        free(papas[i]);
    }

    free(papas[i]);

    free(papas);
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

    char **papas = (char **)malloc((n + 1) * sizeof(char *));

    for (int i = 0; i < n + 1; i++)
    {
        papas[i] = calloc(200, sizeof(char *));
    }

    strcpy(papas[n], "cFIM");

    for (int i = 0; i < n; i++)
    {
        char *temp = strsep(&string, regex);
        strcpy(papas[i], temp);
    }

    return papas;
}

void inserirType(int pos, char *array1, char *array2)
{
    strcpy(pokemons[pos].types.type1, "'");
    strcat(pokemons[pos].types.type1, array1);
    strcat(pokemons[pos].types.type1, "'");
    if (array2[0] != 0)
    {
        strcpy(pokemons[pos].types.type2, ", '");
        strcat(pokemons[pos].types.type2, array2);
        strcat(pokemons[pos].types.type2, "'");
    }
}

void setCaptureDate(char *dateOfBirth, int x)
{
    char *d = strtok(dateOfBirth, "/");
    char *m = strtok(NULL, "/");
    char *y = strtok(NULL, "/");

    pokemons[x].captureDate.dia = atoi(d);
    pokemons[x].captureDate.mes = atoi(m);
    pokemons[x].captureDate.ano = atoi(y);
}

void ler(char *string)
{

    char **papas = split(";", string);

    static int pos = 0;

    pokemons[pos].id = atoi(papas[0]);
    pokemons[pos].generation = atoi(papas[1]);
    strcpy(pokemons[pos].name, papas[2]);
    strcpy(pokemons[pos].description, papas[3]);

    inserirType(pos, papas[4], papas[5]);

    strcpy(pokemons[pos].abilities, papas[6]);
    pokemons[pos].weight = atof(papas[7]);
    pokemons[pos].height = atof(papas[8]);
    pokemons[pos].captureRate = atoi(papas[9]);
    papas[10][0] == '1' ? strcpy(pokemons[pos].legendary, "true") : strcpy(pokemons[pos].legendary, "false");

    setCaptureDate(papas[11], pos);

    pos++;

    freeSplit(papas);
}

char *handleLine(char *line)
{
    int leng = strlen(line);
    char *formatted = malloc(leng * sizeof(char));
    bool control = true;
    int j = 0;

    for (int i = 0; i < leng; i++)
    {

        if (line[i] == '"')
        {
            control = !control;
        }

        else if (line[i] == ',' && control)
        {
            formatted[j++] = ';';
        }

        else if (line[i] != '[' && line[i] != ']')
        {
            formatted[j++] = line[i];
        }
    }
    formatted[j] = 0;
    return formatted;
}

void importDB(char *fileName)
{
    FILE *arq = fopen(fileName, "r");

    if (arq == NULL)
    {
        printf("ERRO NO ARQUIVO\n");
        exit(1);
    }

    char line[2000];

    fgets(line, 2000, arq); // pula a primeira linha do arquivo

    for (int i = 0; fgets(line, 2000, arq); i++)
    {
        int pos = (int)strcspn(line, "\n\r");
        line[pos] = '\0';

        char *formatted = handleLine(line);

        ler(formatted);
    }

    fclose(arq);
}

Pokemon clone(Pokemon x)
{
    Pokemon clonned;

    // Copiando campos primitivos
    clonned.id = x.id;
    clonned.generation = x.generation;
    clonned.nAbilities = x.nAbilities;
    clonned.weight = x.weight;
    clonned.height = x.height;
    clonned.captureRate = x.captureRate;

    // Copiando campos que são strings
    strcpy(clonned.name, x.name);
    strcpy(clonned.description, x.description);
    strcpy(clonned.abilities, x.abilities);
    strcpy(clonned.legendary, x.legendary);

    // Copiando as estruturas internas (tipos e data)
    strcpy(clonned.types.type1, x.types.type1);
    strcpy(clonned.types.type2, x.types.type2);

    clonned.captureDate.dia = x.captureDate.dia;
    clonned.captureDate.mes = x.captureDate.mes;
    clonned.captureDate.ano = x.captureDate.ano;

    return clonned;
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

typedef struct Celula
{
    Pokemon elemento;
    struct Celula *prox;
} Celula;

Celula *novaCelula(Pokemon elemento)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}

Celula *primeiro;
Celula *ultimo;
int n;

void start()
{
    n = 0;
    primeiro = novaCelula(pokemons[0]);
    ultimo = primeiro;
}

void mostrar()
{
    Celula *i;
    int count = 0;
    for (i = primeiro->prox; i != NULL; i = i->prox, count++)
    {
        printf("[%i] ", count);
        printPokemon(i->elemento);
    }
}

Pokemon dequeue()
{
    if (primeiro == ultimo)
    {
        printf("Erro ao remover!\n");
        exit(1);
    }

    Celula *tmp = primeiro->prox; 
    Pokemon resp = tmp->elemento; 

    primeiro->prox = tmp->prox;

    if (tmp == ultimo)
    {
        ultimo = primeiro;
    }

    free(tmp);
    n--;
    return resp;
}

void enqueue(Pokemon x)
{
    if (n == 5)
    {
        dequeue();
    }

    ultimo->prox = novaCelula(x);
    ultimo = ultimo->prox;
    n++;
}


void printMedia() {
    int sum = 0;
    int count = 0;
    double media = 0.0;

    if (n > 0) {
        Celula *tmp = primeiro->prox;
        while (tmp != NULL) {
            sum += tmp->elemento.captureRate;
            count++;
            tmp = tmp->prox;
        }
        media = (double)sum / count;
    }
    printf("Média: %.0lf\n", round(media));
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

void comando(char *id)
{
    char **splitted = split(" ", id);
    
    if (id[0] == 'I')
    {
        int idPoke = atoi(splitted[1]) - 1;
        Pokemon x = pokemons[idPoke];
        enqueue(x);

        printMedia();
    }
    else
    {
        Pokemon x = dequeue();
        printf("(R) %s\n", x.name);
    }

    free(splitted);
}

int main()
{
    // Pokemon fullBD[801];
    importDB("/tmp/pokemon.csv");

    int ids[100];

    char input[80];
    int len = 0;
    scanf(" %s", input);
    while (strcmp(input, "FIM"))
    {
        ids[len] = atoi(input) - 1;
        len++;
        scanf(" %s", input);
    }

    start();

    //printf("%d", len);

    for (int i = 0; i < len; i++)
    {
        //puts("ERROOOOO");
        enqueue(pokemons[ids[i]]);
        printMedia();
    }

    int num;
    scanf("%d", &num);

    char id[20];
    for (int i = 0; i < num; i++)
    {

        scanf(" %20[^\n]", id);
        //printf("%s\n", id);

        comando(id);
    }
    
    printf("\n");

    mostrar();
}
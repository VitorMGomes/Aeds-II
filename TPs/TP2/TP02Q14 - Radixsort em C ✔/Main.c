#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
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
    
}Types;

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

void printPokemon(Pokemon pokemons) {
    printf("[#%d -> %s: %s - [%s%s] - [%s] - %.1fkg - %.1fm - %d%% - %s - %d gen] - %02d/%02d/%d\n", 
           pokemons.id,                // ID
           pokemons.name,              // Nome
           pokemons.description,       // Descrição
           pokemons.types.type1,             // Tipos
           pokemons.types.type2, 
           pokemons.abilities,         // Habilidades
           pokemons.weight,            // Peso
           pokemons.height,            // Altura
           pokemons.captureRate,       // Taxa de Captura
           pokemons.legendary,         // Legendary (0 ou 1)
           pokemons.generation,        // Geração
           pokemons.captureDate.dia,       // Data de Captura
           pokemons.captureDate.mes,       // Data de Captura
           pokemons.captureDate.ano);      // Data de Captura
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
    char* d = strtok(dateOfBirth, "/");
	char* m = strtok(NULL, "/");
	char* y = strtok(NULL, "/");

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

char* handleLine(char* line){
    int leng = strlen(line);
    char* formatted = malloc(leng * sizeof(char));
    bool control = true;
    int j = 0;

    for(int i=0; i<leng; i++)
    {

        if(line[i] == '"')
        {
            control = !control;
        }

        else if(line[i] == ',' && control)
        { 
            formatted[j++] = ';';
        }

        else if(line[i] != '[' && line[i] != ']')
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

    fgets(line, 2000, arq);//pula a primeira linha do arquivo

    for (int i = 0; fgets(line, 2000, arq); i++)
    {
        int pos = (int)strcspn(line, "\n\r");
        line[pos] = '\0';

        char* formatted = handleLine(line);
        
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

Pokemon* array;
int comparacoes = 0;
int movimentacoes = 0;

void swap(int i, int j)
{
    Pokemon tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;

    movimentacoes++;

}

char* firstAb(Pokemon x) {
    char firstAbilitie[60];
    char temp[60];
    strcpy(temp, x.abilities);
    char *token = strtok(temp, "\'");
    
    if (token != NULL) {
        strcpy(firstAbilitie, token);
    } else {
        strcpy(firstAbilitie, "");
    }

    char *output = malloc(60 * sizeof(char));
    int j = 0;

    for (int i = 0; firstAbilitie[i] != '\0'; i++) {
        if (firstAbilitie[i] != ' ') {
            output[j] = firstAbilitie[i];
            j++;
        }
    }

    output[j] = '\0';
    return output;
}

int compareInsertion(Pokemon y, int x)
{

    char *abilitie1 = firstAb(y);
    char *abilitie2 = firstAb(array[x]);

    //printf("%s |||||||| %s", abilitie1, abilitie2);

    

    int resp = strcmp(abilitie1, abilitie2);

    if(resp == 0)
    {
       resp = strcmp(y.name, array[x].name);
    }

    free(abilitie1);
    free(abilitie2);

    return resp;
}




void insertionSort(int len)
{
    for(int i = 1; i < len; i++)
    {
        Pokemon tmp = array[i];
        int j = i - 1;


        while(j >= 0 && compareInsertion(tmp, j) < 0)
        {
            array[j+1] = array[j];
            j--;
            movimentacoes++;
        }

        array[j+1] = tmp;
        movimentacoes++;
    }
}


int findMaxLength(int length) {
    int maxLen = strlen(firstAb(array[0]));
    for (int i = 1; i < length; i++) {
        comparacoes++;
        int len = strlen(firstAb(array[i]));
        if (len > maxLen) {
            maxLen = len;
            movimentacoes++;
        }
    }
    return maxLen;
}




void countingSortByChar(int length, int charPos) {
    const int base = 256;
    int count[base];
    Pokemon output[length];

    for (int i = 0; i < base; i++) {
        count[i] = 0;
    }

    for (int i = 0; i < length; i++) {
        char *abilitie = firstAb(array[i]);
        int charValue = charPos < strlen(abilitie) ? (int)abilitie[charPos] : 0;
        count[charValue]++;
    }

    for (int i = 1; i < base; i++) {
        count[i] += count[i - 1];
    }

    for (int i = length - 1; i >= 0; i--) {
        char *abilitie = firstAb(array[i]);
        int charValue = charPos < strlen(abilitie) ? (int)abilitie[charPos] : 0;
        output[count[charValue] - 1] = array[i];
        movimentacoes++;
        count[charValue]--;
    }

    for (int i = 0; i < length; i++) {
        array[i] = output[i];
        movimentacoes++;
    }
}



void radixSort(int length) {
    int maxLen = findMaxLength(length);

    for (int charPos = maxLen - 1; charPos >= 0; charPos--) {
        countingSortByChar(length, charPos);
    }
}






int main()
{
    //Pokemon fullBD[801];
    importDB("/tmp/pokemon.csv");

    int ids[100];
    
    char input[80];
    int len = 0;
    scanf(" %s", input);
    while(strcmp(input, "FIM"))
    {
        ids[len] = atoi(input) - 1;
        len++;
        scanf(" %s", input);
    }

    array = malloc(len*sizeof(Pokemon));

    
    for(int i = 0; i < len; i++)
    {
        array[i] = clone(pokemons[ids[i]]);
    }

    clock_t inicio = clock();
    radixSort(len);
    insertionSort(len);
    clock_t final = clock();

    for(int i = 0; i < len; i++)
    {
        printPokemon(array[i]);
    }


    free(array);

    double tempo = ((double)(final - inicio)) / (CLOCKS_PER_SEC/1000);

    FILE *fw = fopen("800643_radixsort.txt", "w");
    if (fw != NULL)
    {
        fprintf(fw, "Matrícula: 800643 |\tTempo: %.5fms |\tComparações: %d |\tMovimentações: %d\n", tempo, comparacoes, movimentacoes);
        fclose(fw);
    }
    else
    {
        printf("Erro ao abrir o arquivo.\n");
    }


    


}
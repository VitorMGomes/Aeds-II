#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int counting(char input[1000], int len, int pos, int cases)
{
    if(pos != len)
    {
        if(input[pos] >= 'A' && input[pos] <= 'Z')
        {
            cases++;
        }

        return counting(input, len, ++pos, cases);
    }

    return cases;
}

int counter(char input[1000])
{
    return counting(input, strlen(input), 0, 0);
}

int main()
{
    char input [1000];
    scanf(" %1000[^\n]", input);

    while(strcmp(input, "FIM") != 0)
    {
        printf("%d\n", counter(input));
        scanf(" %1000[^\n]", input);
    }

}
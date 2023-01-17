#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>

typedef struct date
{
        int dia;
        char mes[5];
        int ano;
} Date;

typedef struct game
{
        int app_id;
        char name[500];
        Date release_date;
        char owners[200];
        int age;
        float price;
        int dlcs;
        char languages[2000];
        char website[500];
        bool windowsOS;
        bool macOS;
        bool linuxOS;
        float upvotes;
        int avg_pt;
        char developers[500];
        char genres[1500];
} Game;


bool isFim(char s[], int i)
{
        return (i == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}
int readID(char entry[])
{
        char* tok = strtok(entry, ",");
        return atoi(tok);
}

int main()
{
        FILE* fp = fopen("games.csv", "r");
        Game gamelist[5000];
        int tam = 0;
        char linha[1000];
        char gamelinha[10000];
        int i = 0;
        do
        {       
                fgets(gamelinha, 10000, fp);
                tam = strlen(linha) - 1;
                printf("%s\n", gamelinha);
        } while (!isFim(linha, tam));
        return 0;
}
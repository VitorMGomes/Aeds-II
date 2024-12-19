public class Main
{
    static int comparacoes = 0;
    static int movimentacoes = 0;

    static CustomArray Classe;

    static void swap(int i, int j)
    {
        int tmp = Classe.array[i];
        Classe.array[i] = Classe.array[j];
        Classe.array[j] = tmp;
        movimentacoes+=3;
    }
    
    static void quickSort()
    {
        quickSort(0, Classe.getTam() - 1);
    }


    static void quickSort(int esq, int dir)
    {   
        int i = esq;
        int j = dir;
        int pivo = Classe.array[(esq+dir)/2];

        while(i <= j)
        {
            comparacoes++;
            while(Classe.array[i] < pivo)
            {
                comparacoes++;
                i++;
            }
            comparacoes++;
            while(Classe.array[j] > pivo)
            {
                comparacoes++;
                j--;
            }
            if(i <= j)
            {
                swap(i,j);
                i++;
                j--;
            }
        }
        if(esq < j)
        {
            quickSort(esq, j);
        }
        if(i < dir)
        {
            quickSort(i, dir);
        }
    }

    public static void main(String args[])
    {
        Classe = new CustomArray(100);

        Classe.customShuffle();
        //Classe.printArray();
        System.out.println("Tamanho: " + 10);

        quickSort();

        Classe.printArray();
        System.out.println("Comparacoes: " + comparacoes +"| Movimentacoes: " + movimentacoes);
        
    }
}

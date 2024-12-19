import java.util.*;

class CustomArray{

    private int tam;
    public int array[];

    public int getTam()
    {
        return this.tam;
    }

    public CustomArray(int tam)
    {
        this.tam = tam;
        this.array = new int[tam];
        fillArray();
    }

    public void fillArray()
    {
        for(int i = 0; i < tam; i++)
        {
            this.array[i] = i + 1;
        }
    }

    public void printArray()
    {
        System.out.print("|");
        for(int i = 0; i < tam; i++)
        {
            System.out.print(array[i] + "|");
        }
        System.out.println();
    }

    public void customShuffle()
    {
        Random random = new Random();

        for(int i = tam - 1; i > 0; i--)
        {
            int j = random.nextInt(i + 1);

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
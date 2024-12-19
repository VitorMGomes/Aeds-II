import java.util.*;

public class Ord {
    static int comparacoes;
    static int movimentacoes;

    public static void selectionSort(CustomArray array, Scanner scanf) {
        // movimentacoes = 0;
        // comparacoes = 0;
        int iteracoes = 0;

        System.out.println("O array inicial é esse: ");
        array.printArray();
        int tam = array.getTam();

        // // Selection sort

        // for (int i = 0; i < tam - 1; i++) {
        // int menor = i;
        // for (int j = i + 1; j < tam; j++) {
        // iteracoes++;
        // comparacoes ++;
        // if (array.array[menor] > array.array[j]) {
        // menor = j;
        // }
        // }
        // movimentacoes += 3;
        // int temp = array.array[menor];
        // array.array[menor] = array.array[i];
        // array.array[i] = temp;
        // }

        // System.out.println("Após ordenação: ");
        // array.printArray();
        // System.out.println("Foram feitas " + comparacoes + " comparacoes, " +
        // movimentacoes + " movimentaçoẽs e " + iteracoes +" iteracoes");

        // movimentacoes = 0;
        // comparacoes = 0;
        // iteracoes = 0;

        // Selection sort Optimized

        for (int i = 0; i < tam / 2; i++) {
            int menor = i;
            int maior = i;
            for (int j = i + 1; j < tam - i; j++) {
                iteracoes++;
                if (array.array[menor] > array.array[j]) {
                    comparacoes++;
                    menor = j;
                } else if (array.array[maior] < array.array[j]) {
                    maior = j;
                    comparacoes += 2;
                }
            }
            movimentacoes += 3;
            int temp = array.array[menor];
            array.array[menor] = array.array[i];
            array.array[i] = temp;

            if (maior == i) {
                maior = menor;
            }

            movimentacoes += 3;
            temp = array.array[maior];
            array.array[maior] = array.array[tam - i - 1];
            array.array[tam - i - 1] = temp;
        }

        System.out.println("Após ordenação otimizada: ");
        array.printArray();
        System.out.println("Foram feitas " + comparacoes + " comparacoes, " + movimentacoes + " movimentaçoẽs e "
                + iteracoes + " iteracoes");

    }

    public static void bubbleSort(CustomArray array, Scanner scanf) {
        movimentacoes = 0;
        comparacoes = 0;

        System.out.println("O array inicial é esse: ");
        array.printArray();
        // int tam = array.getTam();

        // Bubble sort

        // for (int i = 0; i < tam - 1; i++) {
        // for (int j = 0; j < tam - 1 - i; j++) {
        // comparacoes++;
        // if (array.array[j] > array.array[j + 1]) {
        // int temp = array.array[j];
        // array.array[j] = array.array[j + 1];
        // array.array[j + 1] = temp;
        // movimentacoes+=3;
        // }
        // }
        // }

        // System.out.println("Após ordenação: ");
        // array.printArray();
        // System.out.println("Foram feitas " + comparacoes + " comparacoes, " +
        // movimentacoes + " movimentaçoẽs");

        // array.printArray();
        // Bubble sort otimizado

        int tam = array.getTam();
        int lastSwap = tam - 1;

        for (int i = 0; i < tam - 1; i++) {
            boolean swapped = false;
            int newlastSwap = 0;
            for (int j = 0; j < lastSwap; j++) {
                comparacoes += 2;
                if (array.array[j] > array.array[j + 1]) {
                    int temp = array.array[j];
                    array.array[j] = array.array[j + 1];
                    array.array[j + 1] = temp;
                    movimentacoes += 3;
                    swapped = true;
                    newlastSwap = j;
                }
            }

            lastSwap = newlastSwap;
            if (!swapped) {
                i = tam;
            }
        }

        System.out.println("Após ordenação otimizada: ");
        array.printArray();
        System.out.println("Foram feitas " + comparacoes + " comparacoes, " + movimentacoes + " movimentaçoẽs");

    }

    public static void insertionSort(CustomArray array, Scanner scanf) {

        movimentacoes = 0;
        comparacoes = 0;

        System.out.println("O array inicial é esse: ");
        array.printArray();

        int tam = array.getTam();

        for (int i = 1; i < tam; i++) {
            if (array.array[i] % 2 == 0) {
                int tmp = array.array[i];
                int j = i - 1;
                while (j >= 0 && ((array.array[j] > tmp) || array.array[j] % 2 == 1)){//while (j >= 0 && array.array[j] > tmp) {
                    array.array[j + 1] = array.array[j];
                    j = j - 1;
                }
                array.array[j + 1] = tmp;
            }
        }

        /*
         * Optimized
         * for (int i = 1; i < length; i++)
         * {
         * int tmp = array[i];
         * int j = i - 1;
         * int locate = binarySearchInsertion(array, i, array[i]);
         * while (j >= locate)
         * {
         * array[j + 1] = array[j];
         * j = j - 1;
         * }
         * array[j + 1] = tmp;
         * }
         */
    }

    public static void main(String args[]) {
        Scanner scanf = new Scanner(System.in);

        System.out.print("Digite o tamanho do array que deseja: ");
        int tam = scanf.nextInt();
        CustomArray array = new CustomArray(tam);

        // array.printArray();
        array.customShuffle();
        // array.printArray();

        System.out.println("(1) Selection Sort; \n(2) Bubble Sort\n(3) Insertion Sort\nDigite qual deseja utilizar: ");
        int choose = scanf.nextInt();
        switch (choose) {
            case 1:
                selectionSort(array, scanf);
                break;

            case 2:
                bubbleSort(array, scanf);
                break;

            case 3:
                insertionSort(array, scanf);
                break;

        }

        scanf.close();
    }
}

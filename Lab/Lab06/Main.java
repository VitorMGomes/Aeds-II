import java.util.Random;

public class Main {
    static int comparacoes;
    static int movimentacoes;

    static CustomArray Classe;

    static void swap(int i, int j) {
        int tmp = Classe.array[i];
        Classe.array[i] = Classe.array[j];
        Classe.array[j] = tmp;
        movimentacoes += 3;
    }

    // QuickSort usando o primeiro elemento como pivô
    static void quickSortFirstPivot() {
        quickSortFirstPivot(0, Classe.getTam() - 1);
    }

    static void quickSortFirstPivot(int esq, int dir) {
        if (esq < dir) {
            int i = esq, j = dir;
            int pivo = Classe.array[esq]; // Primeiro elemento como pivô

            while (i <= j) {
                comparacoes++;
                while (Classe.array[i] < pivo) {
                    comparacoes++;
                    i++;
                }
                comparacoes++;
                while (Classe.array[j] > pivo) {
                    comparacoes++;
                    j--;
                }
                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            if (esq < j) quickSortFirstPivot(esq, j);
            if (i < dir) quickSortFirstPivot(i, dir);
        }
    }

    // QuickSort usando o último elemento como pivô
    static void quickSortLastPivot() {
        quickSortLastPivot(0, Classe.getTam() - 1);
    }

    static void quickSortLastPivot(int esq, int dir) {
        if (esq < dir) {
            int i = esq, j = dir;
            int pivo = Classe.array[dir]; // Último elemento como pivô

            while (i <= j) {
                comparacoes++;
                while (Classe.array[i] < pivo) {
                    comparacoes++;
                    i++;
                }
                comparacoes++;
                while (Classe.array[j] > pivo) {
                    comparacoes++;
                    j--;
                }
                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            if (esq < j) quickSortLastPivot(esq, j);
            if (i < dir) quickSortLastPivot(i, dir);
        }
    }

    // QuickSort usando um pivô aleatório
    static void quickSortRandomPivot() {
        quickSortRandomPivot(0, Classe.getTam() - 1);
    }

    static void quickSortRandomPivot(int esq, int dir) {
        if (esq < dir) {
            int i = esq, j = dir;
            Random rand = new Random();
            int pivoIndex = esq + rand.nextInt(dir - esq + 1); // Pivô aleatório
            int pivo = Classe.array[pivoIndex];

            while (i <= j) {
                comparacoes++;
                while (Classe.array[i] < pivo) {
                    comparacoes++;
                    i++;
                }
                comparacoes++;
                while (Classe.array[j] > pivo) {
                    comparacoes++;
                    j--;
                }
                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            if (esq < j) quickSortRandomPivot(esq, j);
            if (i < dir) quickSortRandomPivot(i, dir);
        }
    }

    // QuickSort usando a mediana de três elementos como pivô
    static void quickSortMedianOfThree() {
        quickSortMedianOfThree(0, Classe.getTam() - 1);
    }

    static void quickSortMedianOfThree(int esq, int dir) {
        if (esq < dir) {
            int i = esq, j = dir;
            int meio = (esq + dir) / 2;
            int pivo = medianOfThree(Classe.array[esq], Classe.array[meio], Classe.array[dir]); // Mediana de três

            while (i <= j) {
                comparacoes++;
                while (Classe.array[i] < pivo) {
                    comparacoes++;
                    i++;
                }
                comparacoes++;
                while (Classe.array[j] > pivo) {
                    comparacoes++;
                    j--;
                }
                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            if (esq < j) quickSortMedianOfThree(esq, j);
            if (i < dir) quickSortMedianOfThree(i, dir);
        }
    }

    static int medianOfThree(int a, int b, int c) {
        if ((a > b) != (a > c)) return a;
        else if ((b > a) != (b > c)) return b;
        else return c;
    }

    public static void main(String args[]) {

        TP tp = new TP();


        System.out.println("[DESORDENADO]");

        System.out.println("Desordenado | Tamanho do Array: 100");

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(100);
            Classe.customShuffle();
            quickSortFirstPivot();
        }
        tp.endTime();
        System.out.println("Primeiro elemento | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(100);
            Classe.customShuffle();
            quickSortLastPivot();
        }
        tp.endTime();
        System.out.println("Último elemento | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(100);
            Classe.customShuffle();
            quickSortRandomPivot();
        }
        tp.endTime();
        System.out.println("Random Pivot | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(100);
            Classe.customShuffle();
            quickSortMedianOfThree();
        }
        tp.endTime();
        System.out.println("Mediana de 3 | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.println("Desordenado | Tamanho do Array: 1.000");

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(1000);
            Classe.customShuffle();
            quickSortFirstPivot();
        }
        tp.endTime();
        System.out.println("Primeiro elemento | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(1000);
            Classe.customShuffle();
            quickSortLastPivot();
        }
        tp.endTime();
        System.out.println("Último elemento | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(1000);
            Classe.customShuffle();
            quickSortRandomPivot();
        }
        tp.endTime();
        System.out.println("Random Pivot | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(1000);
            Classe.customShuffle();
            quickSortMedianOfThree();
        }
        tp.endTime();
        System.out.println("Mediana de 3 | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.println("Desordenado | Tamanho do Array: 10.000");

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(10000);
            Classe.customShuffle();
            quickSortFirstPivot();
        }
        tp.endTime();
        System.out.println("Primeiro elemento | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(10000);
            Classe.customShuffle();
            quickSortLastPivot();
        }
        tp.endTime();
        System.out.println("Último elemento | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(10000);
            Classe.customShuffle();
            quickSortRandomPivot();
        }
        tp.endTime();
        System.out.println("Random Pivot | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
        for (int i = 0; i < 10000; i++) {
            Classe = new CustomArray(10000);
            Classe.customShuffle();
            quickSortMedianOfThree();
        }
        tp.endTime();
        System.out.println("Mediana de 3 | Comparacoes: " + (comparacoes / 10000) + " | Movimentacoes: " + (movimentacoes / 10000) + " | Tempo: " + tp.Time() / 1000 + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.println("[ORDENADO]");
        


        System.out.println("Ordenado | Tamanho do Array: 100");

        tp.startTime();
            Classe = new CustomArray(100);
            quickSortFirstPivot();
        tp.endTime();
        System.out.println("Primeiro elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;
        tp.startTime();
            Classe = new CustomArray(100);
            quickSortLastPivot();
        tp.endTime();
        System.out.println("Último elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(100);
            quickSortRandomPivot();
        tp.endTime();
        System.out.println("Random Pivot | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(100);
            quickSortMedianOfThree();
        tp.endTime();
        System.out.println("Mediana de 3 | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.println("Ordenado | Tamanho do Array: 1.000");

        tp.startTime();
            Classe = new CustomArray(1000);
            quickSortFirstPivot();
        tp.endTime();
        System.out.println("Primeiro elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(1000);
            quickSortLastPivot();
        tp.endTime();
        System.out.println("Último elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(1000);
            quickSortRandomPivot();
        tp.endTime();
        System.out.println("Random Pivot | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(1000);
            quickSortMedianOfThree();
        tp.endTime();
        System.out.println("Mediana de 3 | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.println("Ordenado | Tamanho do Array: 10.000");

        tp.startTime();
            Classe = new CustomArray(10000);
            quickSortFirstPivot();
        tp.endTime();
        System.out.println("Primeiro elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(10000);
            quickSortLastPivot();
        tp.endTime();
        System.out.println("Último elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(10000);
            quickSortRandomPivot();
        tp.endTime();
        System.out.println("Random Pivot | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(10000);
            quickSortMedianOfThree();
        tp.endTime();
        System.out.println("Mediana de 3 | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        System.out.println("---------------------------------------------------------------------------------------------");


        System.out.println("[PARCIALMENTE ORDENADO]");


        System.out.println("Parcialmente ordenado | Tamanho do Array: 100");

        tp.startTime();
            Classe = new CustomArray(100);
            Classe.partialShuffle();
            quickSortFirstPivot();
        tp.endTime();
        System.out.println("Primeiro elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;
        tp.startTime();
            Classe = new CustomArray(100);
            Classe.partialShuffle();
            quickSortLastPivot();

        tp.endTime();
        System.out.println("Último elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(100);
            Classe.partialShuffle();
            quickSortRandomPivot();
        tp.endTime();
        System.out.println("Random Pivot | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(100);
            Classe.partialShuffle();
            quickSortMedianOfThree();
        tp.endTime();
        System.out.println("Mediana de 3 | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Parcialmente ordenado | Tamanho do Array: 1.000");

        tp.startTime();
            Classe = new CustomArray(1000);
            Classe.partialShuffle();
            quickSortFirstPivot();
        tp.endTime();
        System.out.println("Primeiro elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(1000);
            Classe.partialShuffle();
            quickSortLastPivot();
        tp.endTime();
        System.out.println("Último elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(1000);
            Classe.partialShuffle();
            quickSortRandomPivot();
        tp.endTime();
        System.out.println("Random Pivot | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(1000);
            Classe.partialShuffle();
            quickSortMedianOfThree();
        tp.endTime();
        System.out.println("Mediana de 3 | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        System.out.println("---------------------------------------------------------------------------------------------");

        System.out.println("Parcialmente ordenado | Tamanho do Array: 10.000");

        tp.startTime();
            Classe = new CustomArray(10000);
            Classe.partialShuffle();
            quickSortFirstPivot();
        tp.endTime();
        System.out.println("Primeiro elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(10000);
            Classe.partialShuffle();
            quickSortLastPivot();
        tp.endTime();
        System.out.println("Último elemento | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(10000);
            Classe.partialShuffle();
            quickSortRandomPivot();
        tp.endTime();
        System.out.println("Random Pivot | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        tp.startTime();
            Classe = new CustomArray(10000);
            Classe.partialShuffle();
            quickSortMedianOfThree();
        tp.endTime();
        System.out.println("Mediana de 3 | Comparacoes: " + (comparacoes) + " | Movimentacoes: " + (movimentacoes) + " | Tempo: " + tp.Time() + " ms");
        comparacoes = 0;
        movimentacoes = 0;

        System.out.println("---------------------------------------------------------------------------------------------");

        
    }
}

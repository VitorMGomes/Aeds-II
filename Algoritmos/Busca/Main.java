import java.util.*;

public class Main {

    static int comparacoes;

    public static boolean pesquisaBinaria(CustomArray array, int pesquisa, int esq, int dir) {
        boolean resp;
        int meio = (dir + esq) / 2;

        comparacoes++;

        if (esq > dir) {
            resp = false;
        } else if (pesquisa == array.array[meio]) {
            resp = true;
        } else if (pesquisa > array.array[meio]) {
            resp = pesquisaBinaria(array, pesquisa, meio + 1, dir);
        } else {
            resp = pesquisaBinaria(array, pesquisa, esq, meio - 1);
        }

        return resp;
    }

    public static void pesquisaBinaria(CustomArray array, Scanner scanf)
    {
        comparacoes = 0;

        System.out.println("Digite um número (inteiro) para pesquisar no array: ");
        int pesquisar = scanf.nextInt();

        System.out.println("//----------------------------------------------//----------------------------------------------//");
        //array.customShuffle();
        array.printArray();
        System.out.println(pesquisaBinaria(array, pesquisar, 0, array.getTam() - 1) ? "Está presente no array" : "Não está presente no array;");
        System.out.println("Foram feitas " + comparacoes + " comparações");

        System.out.println("//----------------------------------------------//----------------------------------------------//");
        
        System.out.println("O custo da busca binária é de lg(n)");
        System.out.println("Para efetuar a busca binária o array tem de estar ordenado. Todos nessa parte do código estarão em ordem crescente sem elementos repetidos");
        System.out.println("Caso o array esteja desordenado existe a chance de mesmo o elemento presente ser apontado como faltante");

        System.out.println("//----------------------------------------------//----------------------------------------------//");

        array.customShuffle();
        array.printArray();
        comparacoes = 0;
        System.out.println(pesquisaBinaria(array, pesquisar, 0, array.getTam() - 1) ? "Está presente no array" : "Não está presente no array;");
        System.out.println("Foram feitas " + comparacoes + " comparações");



    }

    public static boolean pesquisaSequencial(CustomArray array, int pesquisa, int tam)
    {
        boolean resp = false;

        for(int i = 0; i < tam; i++)
        {
            comparacoes++;
            if(array.array[i] == pesquisa)
            {
                resp = true;
                i = tam;
            }
        }

        return resp;
    }

    public static void pesquisaSequencial(CustomArray array, Scanner scanf)
    {
        comparacoes = 0;

        System.out.println("Digite um número(inteiro) para buscar no array");
        int pesquisar = scanf.nextInt();

        System.out.println("//----------------------------------------------//----------------------------------------------//");
        //array.customShuffle();
        array.printArray();

        System.out.println(pesquisaSequencial(array, pesquisar, array.getTam()) ? "Está presente no array" : "Não está presente no array");

        System.out.println("Foram feitas " + comparacoes + " comparações");

        System.out.println("//----------------------------------------------//----------------------------------------------//");
        
        System.out.println("O custo da busca sequencial é de teta(n)");
        System.out.println("Para efetuar a busca sequencial o array pode estar ordenado ou não");
        System.out.println("É possível alterar o sequencial para contar o número de repetiçoes de um elemento em um array, sendo ele ordenado ou não");
        System.out.println("É possível otimizar o algoritimo para encerrar as operações quando achar o item pesquisado");

        System.out.println("//----------------------------------------------//----------------------------------------------//");
        
        comparacoes = 0;

        array.customShuffle();
        System.out.println("Pesquisando agora em um array desordenado");
        
        System.out.println(pesquisaSequencial(array, pesquisar, array.getTam()) ? "Está presente no array" : "Não está presente no array");

        System.out.println("Foram feitas " + comparacoes + " comparações");

    }


    public static void main(String args[])
    {   
        Scanner scanf = new Scanner(System.in);
        
        System.out.print("Digite o tamanho do array que deseja: ");
        int tam = scanf.nextInt();
        CustomArray array = new CustomArray(tam);

        // array.printArray();
        // array.customShuffle();
        // array.printArray();

        System.out.println("(1) Pesquisa Sequencial; \n(2) Pesquisa Binária\nDigite qual deseja utilizar: ");
        int choose = scanf.nextInt();
        switch(choose)
        {
            case 1:
            pesquisaSequencial(array, scanf);
            break;

            case 2:
            pesquisaBinaria(array, scanf);
            break;

            
        }
        
    }
}

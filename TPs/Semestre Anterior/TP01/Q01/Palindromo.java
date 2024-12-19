import java.util.*;

public class Palindromo {

    public static boolean stop(String input) { // função que verifica se o input é equivalente a "FIM", e retorna falso
                                               // se não e verdadeiro se for
        boolean stop = false;

        if (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M') {
            stop = true;
        }

        return stop;
    }

    public static boolean isPal(String input) { // verifica se a palavra e um palindromo ou não
        boolean palindromo = true; // inicia o valor de palindromo como verdadeiro
        int len = input.length() - 1; // pega o tamanho da palavra/frase
        for (int i = 0; i <= len / 2; i++) {
            if (input.charAt(i) != input.charAt(len - i)) { // compara a letra com sua posição espelhada e se for
                                                            // diferente define a variavel como falsa e encerra o loop
                                                            // igualando I com o tamanho da palavra
                palindromo = false;
                i = len;
            }
        }

        return palindromo;
    }

    public static void main(String args[]) {
        Scanner scanf = new Scanner(System.in);

        String input = scanf.nextLine();

        while (!stop(input)) {

            MyIO.println(isPal(input)? "SIM" : "NAO");

            input = scanf.nextLine();
        }

        scanf.close();

    }
}

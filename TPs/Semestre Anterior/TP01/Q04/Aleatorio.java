import java.util.Random;

public class Aleatorio {
    public static boolean stop(String input) // verifica se a entrada é igual a FIM, caso sim, retorna verdadeiro, e
                                             // caso não, falso
    {
        boolean stop = false;

        if (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M') {
            stop = true;
        }
        return stop;
    }

    public static void alteracao(String input, String output, Random gerador) {
        char letraBase = (char) ('a' + (Math.abs(gerador.nextInt()) % 26)); //gera a letra que vai ser substituida
        char letraTrade = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));//gera a letra que vai substituir

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == letraBase) { //verifica se a letra tem de ser trocada ou não
                output += letraTrade;
            } else { //se ela não tiver de ser trocada adiciona a letra original na string de output
                output += input.charAt(i);
            }
        }

        MyIO.println(output);
    }

    public static void main(String Args[]) {

        Random gerador = new Random();
        gerador.setSeed(4);

        String input = MyIO.readLine();

        while (!stop(input)) {
            String output = "";

            alteracao(input, output, gerador);

            input = MyIO.readLine();
        }
    }
}
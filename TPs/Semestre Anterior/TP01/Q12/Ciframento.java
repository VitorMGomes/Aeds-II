import java.util.*;

public class Ciframento
{
    public static boolean stop(String input) //verifica se a entrada é igual a FIM, caso sim, retorna verdadeiro, e caso não, falso
    {
        boolean stop = false;

        if(input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M')
        {
            stop = true;
        }
        return stop;
    }

    /*public static void ciframento(String input)
    {
        String output = "";

        for(int i = 0; i < input.length(); i++)
        {
            int numLetra = (int) input.charAt(i);

            numLetra += 3;

            char charCifrado = (char) numLetra;

            output += charCifrado;
        }
        
        MyIO.println(output);
        
    }*/

    public static void ciframentoRec(String input, int i, int len, String saida)
    {
        if(i < len)
        {
            int charInt =  (int) input.charAt(i);

            charInt += 3;

            char Intchar = (char) charInt;

            saida += Intchar;

            ciframentoRec(input, ++i, len, saida);
        }
        else{
            System.out.println(saida);
        }
        
    }

    public static void main(String[] args) {
        
        Scanner scanf = new Scanner(System.in);

        String input = scanf.nextLine();
        while(!stop(input))
        {
            ciframentoRec(input, 0, input.length(), "");
            input = scanf.nextLine();
        }

        scanf.close();
    }
}
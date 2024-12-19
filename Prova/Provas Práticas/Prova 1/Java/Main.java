import java.util.*;

public class Main
{
    public static void main(String args[])
    {
        Scanner scanf = new Scanner(System.in);

        int rep = scanf.nextInt();
        scanf.nextLine();
        
        for(int j = 0; j < rep; j++)
        {
            int parenteses = 0;
            int chaves = 0;
            int colchetes = 0;

            String frase = scanf.nextLine();

            boolean resp = true;

            for(int i = 0; i < frase.length(); i++)
            {
                if(frase.charAt(i) == '[')
                {
                    colchetes++;
                }
                else if(frase.charAt(i) == ']')
                {
                    colchetes--;
                }
                else if(frase.charAt(i) == '(')
                {
                    parenteses++;
                }
                else if(frase.charAt(i) == ')')
                {
                    parenteses--;
                }
                else if(frase.charAt(i) == '{')
                {
                    chaves++;
                }
                else if(frase.charAt(i) == '}')
                {
                    chaves--;
                }

                if(parenteses < 0 || chaves < 0 || colchetes < 0)
                {
                    resp = false;
                    i = frase.length();
                }

            }//fim do for de contagem

            if(parenteses != 0 || chaves != 0 || colchetes != 0)
            {
                resp = false;
            }

            System.out.println(resp ? "S" : "N");
        }

        scanf.close();
    }
}
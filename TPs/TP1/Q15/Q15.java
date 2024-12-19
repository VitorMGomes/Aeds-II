import java.util.Scanner;

public class Q15 {
    
    public static boolean stop(String input)
    {
        boolean stop = false;
        if (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M') 
        {
        stop = true;
        }

        return stop;
    }

    public static boolean verificarVogal(String input, int len, boolean vogal, int i)
    {
        if(i < len)
        {
            if(input.charAt(i) != 'a' && input.charAt(i) != 'e' && input.charAt(i) != 'i' && input.charAt(i) != 'o' && input.charAt(i) != 'u')
            {
                i = len;   
                vogal = false; 
            }
            
            return verificarVogal(input, len, vogal, ++i);
        }

        return vogal;
    }

    public static boolean verificarConso(String input, int len, boolean conso, int i)
    {
        if(i < len)
        {
            if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')
            {
                if(input.charAt(i) == 'A' || input.charAt(i) == 'E' || input.charAt(i) == 'I' || input.charAt(i) == 'O' || input.charAt(i) == 'U')
                {
                    i = len;
                    conso = false;
                }
            }
            else
            {
                i = len;
                conso = false;
            }

            return verificarConso(input, len, conso, ++i);

        }

        return conso;
    }

    public static boolean verificarInt(String input, int len, boolean inteiro, int i)
    {
        if(i < len)
        {
            if(!(input.charAt(i) >= '0' && input.charAt(i) <= '9'))
            {
                inteiro = false;
                i = len;
            }


            return verificarInt(input, len, inteiro, ++i);
        }

        return inteiro;
    }
    
    public static boolean verificarReal(String input, int len, boolean real, int contador, int i)
    {
        if(i < len)
        {
            if((input.charAt(i) >= '0' && input.charAt(i) <= '9') || (input.charAt(i) == '.' || input.charAt(i) == ',')) 
            {
                if(input.charAt(i) == '.' || input.charAt(i) == ',')
                {
                    contador++;
                }

            }
            else
            {
                real = false;
                i = len;
            }

            return verificarReal(input, len, real, contador, ++i);
        }

        if(contador > 1)
        { 
            real = false;
        }
        
        return real;
    }

    public static void main(String args[])
    {
        Scanner scanf = new Scanner(System.in);

        for(String input = scanf.nextLine();!stop(input); input = scanf.nextLine())
        {
            input = input.toLowerCase(); //passa a string toda para minusculo para facilitar na verificacao

            int len = input.length(); //a funcao sera executada apenas uma vez aqui

            System.out.print(verificarVogal(input, len, true, 0) ? "SIM " : "NAO ");

            System.out.print(verificarConso(input, len, true, 0) ? "SIM " : "NAO ");
    
            System.out.print(verificarInt(input, len, true, 0) ? "SIM " : "NAO ");
    
            System.out.println(verificarReal(input, len, true, 0, 0) ? "SIM" : "NAO");

        }

        scanf.close();
    }
}

import java.util.*;

public class Q6 {
    
    public static boolean stop(String input)
    {
        boolean stop = false;
        int len = input.length(); 

        if(len == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M')
        {
            stop = true;
        }

        return stop;
    }

    public static boolean isVogal(String input)
    {
        boolean is = true;
        input = input.toLowerCase();
        int len = input.length();

        for(int i = 0; i < len; i++)
        {
            if(input.charAt(i) != 'a' && input.charAt(i) != 'e' && input.charAt(i) != 'i' && input.charAt(i) != 'o' && input.charAt(i) != 'u')
            {
                is = false;
                i = len;
            }
        }

        return is;
    }
    
    public static boolean isConso(String input)
    {
        boolean is = true;
        input = input.toLowerCase();
        int len = input.length();

        for(int i = 0; i < len; i++)
        {
            if(input.charAt(i) <= 'a' || input.charAt(i) > 'z')
            {
                i = len;
                is = false;
            }
            else if(input.charAt(i) == 'e' || input.charAt(i) == 'i' || input.charAt(i) == 'o' || input.charAt(i) == 'u')
            {
                i = len;
                is = false;
            }
        }

        return is;
    }

    public static boolean isInt(String input)
    {
        boolean is = true;
        int len = input.length();

        for(int i = 0; i < len; i++)
        {
            if(input.charAt(i) < '0' || input.charAt(i) > '9')
            {
                i = len;
                is = false;
            }
        }


        return is;
    }

    public static boolean isReal(String input)
    {
        boolean is = true;
        int len = input.length();
        int cont = 0;

        for(int i = 0; i < len; i++)
        {
            if((input.charAt(i) < '0' || input.charAt(i) > '9') && input.charAt(i) != '.' && input.charAt(i) != ',')
            {
                i = len;
                is = false;
            }
            else if(input.charAt(i) == '.' || input.charAt(i) == ',')
            {
                cont++;
                if(cont > 1)
                {
                    i = len;
                    is = false;
                }
            }
        }
        return is;
    }
    public static void main(String args[])
    {
        Scanner scanf = new Scanner(System.in);
        String input = scanf.nextLine();

        while(!stop(input))
        {
            if(isVogal(input))
            {
                System.out.print("SIM ");
            }
            else{
                System.out.print("NAO ");
            }
            if(isConso(input))
            {
                System.out.print("SIM ");
            }
            else{
                System.out.print("NAO ");
            }
            if(isInt(input))
            {
                System.out.print("SIM ");
            }
            else{
                System.out.print("NAO ");
            }
            if(isReal(input))
            {
                System.out.print("SIM\n");
            }
            else{
                System.out.print("NAO\n" );
            }

            input = scanf.nextLine();
        }

        scanf.close();
    }   
}

public class Is {
    
    public static boolean stop(String input)
    {
        boolean stop = false;

        if(input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M')
        {
            stop = true;
        }

        return stop;
    }

    public static boolean isVogal(String input)
    {
        boolean is = true;
        input = input.toLowerCase();
        int tamanho = input.length();

        for(int i = 0; i < tamanho; i++)
        {
            if(input.charAt(i) != 'a' && input.charAt(i) != 'e' && input.charAt(i) != 'i' && input.charAt(i) != 'o' && input.charAt(i) != 'u')
            {
                is = false;
                i = tamanho;
            }
        }

        return is;
    }
    
    public static boolean isConso(String input)
    {
        boolean is = true;
        input = input.toLowerCase();
        int tamanho = input.length();

        for(int i = 0; i < tamanho; i++)
        {
            if(input.charAt(i) <= 'a' || input.charAt(i) > 'z')
            {
                i = tamanho;
                is = false;
            }
            else if(input.charAt(i) == 'e' || input.charAt(i) == 'i' || input.charAt(i) == 'o' || input.charAt(i) == 'u')
            {
                i = tamanho;
                is = false;
            }
        }

        return is;
    }

    public static boolean isInt(String input)
    {
        boolean is = true;
        int tamanho = input.length();

        for(int i = 0; i < tamanho; i++)
        {
            if(input.charAt(i) < '0' || input.charAt(i) > '9')
            {
                i = tamanho;
                is = false;
            }
        }


        return is;
    }

    public static boolean isReal(String input)
    {
        boolean is = true;
        int tamanho = input.length();
        int contador = 0;

        for(int i = 0; i < tamanho; i++)
        {
            if((input.charAt(i) < '0' || input.charAt(i) > '9') && input.charAt(i) != '.' && input.charAt(i) != ',')
            {
                i = tamanho;
                is = false;
            }
            else if(input.charAt(i) == '.' || input.charAt(i) == ',')
            {
                contador++;
                if(contador > 1)
                {
                    i = tamanho;
                    is = false;
                }
            }
        }
        return is;
    }
    public static void main(String args[])
    {
        String input = MyIO.readLine();

        while(!stop(input))
        {
            if(isVogal(input))
            {
                MyIO.print("SIM ");
            }
            else{
                MyIO.print("NAO ");
            }
            if(isConso(input))
            {
                MyIO.print("SIM ");
            }
            else{
                MyIO.print("NAO ");
            }
            if(isInt(input))
            {
                MyIO.print("SIM ");
            }
            else{
                MyIO.print("NAO ");
            }
            if(isReal(input))
            {
                MyIO.print("SIM\n");
            }
            else{
                MyIO.print("NAO\n" );
            }

            input = MyIO.readLine();
        }
    }   
}

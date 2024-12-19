class PalindromoRec
{
    public static boolean verificarPal(String input, int tamanho, int i, boolean isPal)
    {   
        if(i < tamanho / 2 && isPal == true)
        {
            if(input.charAt(i) == input.charAt(tamanho - i - 1))
            {
               isPal = verificarPal(input, tamanho, ++i, isPal);
            }
            else{
                isPal = false;
            }
        }

        return isPal;
    }

    public static boolean stop(String input)
    {
        boolean stop = false;

        if(input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M')
        {
            stop = true;
        }

        return stop;
    }
    public static void main(String args[])
    {
        String input = MyIO.readLine();

        while(!stop(input))
        {
            int tamanho = input.length();

            MyIO.println(verificarPal(input, tamanho, 0, true) ? "SIM" : "NAO");

            input = MyIO.readLine();

        }
    }
}
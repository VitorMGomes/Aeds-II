import java.util.*;

public class Q10 {

    public static boolean stop(String input) {
        boolean resp = false;

        if (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M') 
        {
            resp = true;
        }

        return resp;
    }

    public static boolean isPal(String input)
    {
        return isPal(input, true, input.length(), 0);
    }

    public static boolean isPal(String input, boolean is, int len, int pos)
    {
        if(pos <= len/2)
        {
            if(input.charAt(pos) != input.charAt(len - pos - 1))
            {
                is = false;
                pos = len;
            }

            return isPal(input, is, len, ++pos);
        }

        return is;
    }

    

    public static void main(String args[])
    {
        Scanner scanf = new Scanner(System.in);

        for(String input = scanf.nextLine(); !stop(input); input = scanf.nextLine())
        {
            if(isPal(input))
            {
                System.out.println("SIM");
            }
            else
            {
                System.out.println("NAO");
            }
        }
        scanf.close();
    }




}
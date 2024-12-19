import java.util.*;

public class Q12 {

    public static boolean stop(String input) {
        boolean resp = false;

        if (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M') 
        {
            resp = true;
        }

        return resp;
    }

    public static void cifrar(String input)
    {
        System.out.println(cifrar(input, "", input.length(), 0));
    }

    public static String cifrar(String input, String output, int len, int pos)
    {
        if(len != pos)
        {
            char i = input.charAt(pos);
            output += (char) ((int) (i += 3));

            return cifrar(input, output, len, ++pos);
        }

        return output;
    }

    public static void main(String args[])
    {
        Scanner scanf = new Scanner(System.in);

        for(String input = scanf.nextLine(); !stop(input); input = scanf.nextLine())
        {
            cifrar(input);
        }
        
        scanf.close();
    }




}

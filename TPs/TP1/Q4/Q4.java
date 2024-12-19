import java.util.*;

public class Q4 {

    public static boolean stop(String input) {
        boolean resp = false;

        if (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M') 
        {
            resp = true;
        }

        return resp;
    }

    public static void randomize(String input, Random rand)
    {
        char replace = (char) ('a' + Math.abs(rand.nextInt() % 26));

        char substitute = (char) ('a' + Math.abs(rand.nextInt() % 26));

        int len = input.length();

        String output = "";

        for(int i = 0; i < len; i++)
        {
            if(input.charAt(i) == replace)
            {
                output += substitute;
            }
            else{
                output += input.charAt(i);
            }
        }

        System.out.println(output);
    }

    public static void main(String args[])
        {
            Random rand = new Random();
            rand.setSeed(4);

            Scanner scanf = new Scanner(System.in);

            String input = scanf.nextLine();

            do
            {
                if(!stop(input))
                {
                    randomize(input, rand);
                }

                input = scanf.nextLine();

            }while(!stop(input));

            scanf.close();
        }
}

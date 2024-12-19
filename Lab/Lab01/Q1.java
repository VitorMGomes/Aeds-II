import java.util.*;

class Q1
{
    public static void main(String args[])
    {
        Scanner scanf = new Scanner(System.in);

        String input = scanf.nextLine();

        while(!(input.equals("FIM")))
        {
            int len = input.length();

            int upperCases = 0;

            for(int i = 0; i < len; i++)
            {
                if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')
                {
                    upperCases++;
                }
            }

            System.out.println(upperCases);
            input = scanf.nextLine();
        }

        scanf.close();
    }
}
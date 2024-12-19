import java.util.*;

class Q2 {

    public static int counter(String input)
    {
        return counter(input, 0, input.length(), 0);
    }

    public static int counter(String input, int cases, int len, int pos)
    {
        if(pos != len)
        {
            if(input.charAt(pos) >= 'A' && input.charAt(pos) <= 'Z')
            {
                cases++;
            }

            return counter(input, cases, len, ++pos);

        }

        return cases;
    }

    public static void main(String args[])
    {
        Scanner scanf = new Scanner(System.in);

        String input = scanf.nextLine();

        while(!(input.equals("FIM")))
        {
            System.out.println(counter(input));
            input = scanf.nextLine();
        }

        scanf.close();
    }
}

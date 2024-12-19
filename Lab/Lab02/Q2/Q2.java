import java.util.*;

public class Q2{

    public static void main(String[] args)
    {
        Scanner scanf = new Scanner(System.in);

        int start;
        int end;
        String output;
        
        while(scanf.hasNext())
        {
            output = "";

            start = scanf.nextInt();
            end = scanf.nextInt();


            for(int i = start; i <= end; i++)
            {
                output += i;
            }
            
            System.out.print(output);

            for(int i = output.length() - 1; i >= 0; i--)
            {
                System.out.print(output.charAt(i));
            }

            System.out.println();
        }
    }
}

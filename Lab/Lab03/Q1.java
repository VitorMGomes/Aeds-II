import java.util.*;
import java.io.*;

public class Q1 {
    public static void main(String args[]) {
        Scanner scanf = new Scanner(System.in);

        String input = new String();

        for (input = scanf.nextLine(); !input.equals("FIM"); input = scanf.nextLine()) {
            int len = input.length();
            int count = 0;

            for(int i = 0; i < len; i++)
            {   
                if(input.charAt(i) == ')')
                {
                    count--;
                }
                if(input.charAt(i) == '(')
                {
                    count++;
                }

                if(count < 0)
                {
                    i = len;
                }
            }

            boolean resp = true;
            if(count != 0)
            {
                resp = false;
            }

            System.out.println(resp ? "correto" : "incorreto");

        }

        scanf.close();
    }
}

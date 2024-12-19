import java.util.*;

public class Q3 {

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
        String output = "";
        int len = input.length();

        for(int i = 0; i < len; i++)
        {
            char pre = input.charAt(i);

            int transform = (int) pre + 3;

            char pos = (char) transform;

            // if(transform > 127)
            // {
            //     output += '�';
            // }
            // else
            // {
                output += pos;
            // }
        }

        System.out.println(output);
        //MyIO.println(output);
    }

    
    public static void main(String[] args) { //no verde o Scanner lẽ usando o charset errado, sendo necessária a MyIO

        Scanner scanf = new Scanner(System.in);
        String input = scanf.nextLine();
        //String input = MyIO.readLine();

        while(!stop(input))
        {
            cifrar(input);

            input = scanf.nextLine();
            //input = MyIO.readLine();
        }

        scanf.close();

    }
}

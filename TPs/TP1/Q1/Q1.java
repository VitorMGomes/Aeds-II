import java.util.*;

public class Q1 {

    public static boolean stop(String input) {
        boolean resp = false;

        if (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M') 
        {
            resp = true;
        }

        return resp;
    }

    public static boolean isPal(String input) {
        int len = input.length();
        boolean resp = true;

        for (int i = 0; i < len / 2; i++) {
            if (input.charAt(i) != input.charAt(len - 1 - i)) {
                resp = false;
                i = len;
            }
        }

        return resp;
    }

    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);

        String input = scanf.nextLine();

        while (!stop(input)) {
            if (isPal(input)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            input = scanf.nextLine();
        }

        scanf.close();
    }
}
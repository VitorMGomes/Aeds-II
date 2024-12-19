import java.util.*;

public class Q5 {

    public static int parseInt(char input) {
        int cont = input - 48;
        return cont;
    }

    public static boolean parseBoolean(char input) {
        return input != '0';
    }

    public static String substring(String input, int start, int end) {
        String newStr = new String();

        for (int i = start; i < end; i++) {
            newStr += input.charAt(i);
        }

        return newStr;
    }

    public static String replace(String input, int star, int end, String subReplace) {

        String newStr1 = substring(input, 0, star);
        String newStr2 = substring(input, end, input.length());

        return newStr1 + subReplace + newStr2;
    }

    public static char[] getArgs(String input, int index) {

        int contador = 0;

        for (int i = index + 1; input.charAt(i) != ')'; i++) {
            if (input.charAt(i) == '0' || input.charAt(i) == '1') {
                contador++;
            }
        }

        char[] args = new char[contador];

        for (int i = index + 1, j = 0; input.charAt(i) != ')'; i++) {
            if (input.charAt(i) == '0' || input.charAt(i) == '1') {
                args[j] = input.charAt(i);
                j++;
            }
        }

        return args;
    }

    public static char not(char[] args) {

        if (args[0] == '0') {
            return '1';
        } else if (args[0] == '1') {
            return '0';
        }
        return 0;

    }

    public static char and(char[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i] == '0') {
                return '0';
            }
        }
        return '1';

    }

    public static char or(char[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i] == '1') {
                return '1';
            }
        }
        return '0';

    }

    public static String equacionando(String input) throws Exception {

        char[] args;
        char result;
        int end;

        for (int i = (input.length() - 1); i >= 0; i--) {
            if (input.charAt(i) == '(') {
                switch (input.charAt(i - 1)) {
                    case 'd':
                        args = getArgs(input, i);
                        result = and(args);

                        for (end = i; input.charAt(end) != ')'; end++)
                            ;
                        input = replace(input, i - 3, end + 1, result + "");
                        i -= 3;
                        break;
                    case 't':
                        args = getArgs(input, i);
                        result = not(args);
                        input = replace(input, i - 3, i + 3, result + "");
                        i -= 3;
                        break;
                    case 'r':
                        args = getArgs(input, i);
                        result = or(args);

                        for (end = i; input.charAt(end) != ')'; end++)
                            ;
                        input = replace(input, i - 2, end + 1, result + "");
                        i -= 2;
                        break;

                    default:
                        throw new Exception("Erro");
                }
            }
        }

        return substring(input, 0, 1);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanf = new Scanner(System.in);
        String input = scanf.nextLine();

        while (input.charAt(0) != '0') {
            int cont = parseInt(input.charAt(0));

            boolean[] array = new boolean[cont];

            if (cont == 2) {
                array[0] = parseBoolean(input.charAt(2));
                array[1] = parseBoolean(input.charAt(4));
                input = substring(input, 6, input.length());

            } else if (cont == 3) {
                array[0] = parseBoolean(input.charAt(2));
                array[1] = parseBoolean(input.charAt(4));
                array[2] = parseBoolean(input.charAt(6));
                input = substring(input, 8, input.length());
            }

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'A') {
                    input = replace(input, i, i + 1, array[0] == false ? "0" : "1");
                } else if (input.charAt(i) == 'B') {
                    input = replace(input, i, i + 1, array[1] == false ? "0" : "1");
                } else if (input.charAt(i) == 'C') {
                    input = replace(input, i, i + 1, array[2] == false ? "0" : "1");
                }
            }
            input = equacionando(input);
            System.out.println(input);

            input = scanf.nextLine();
        }

        scanf.close();
    }

}
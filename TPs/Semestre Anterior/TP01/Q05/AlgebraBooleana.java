class AlgebraBooleana {

    public static boolean and(String aux) { 
        boolean and = true;
        for (int i = 0; i < aux.length() && and; i++) {
            if (aux.charAt(i) == '0') { 
                and = false;
            }
        }
        return and;
    }

    public static boolean or(String aux) { 
        boolean or = false;
        for (int i = 0; i < aux.length() && !or; i++) {
            if (aux.charAt(i) == '1') { 
                or = true;
            }
        }
        return or;
    }

    public static boolean not(String aux) {
        boolean not = true;
        if (aux.charAt(0) == '1') {
            not = false;
        }
        return not;
    }

    public static boolean expressao(String input, int x, int npa, int n) {

        boolean result = true, resultB = false;
        int pa = 0, pf = 0;
        String aux = "";

        if (x < npa) { 
            boolean exp = false;
            for (int i = 0; i < input.length() && !exp; i++) { 
                if (input.charAt(i) == '(') {
                    pa = i;
                } else if (input.charAt(i) == ')') {
                    pf = i;
                }
                if (pa < pf) {
                    exp = true;
                }
            }

            for (int j = pa + 1; j < pf; j++) { 
                aux += input.charAt(j);
            }
            switch (input.charAt(pa - 1)) { 
                case 'd': // and
                    resultB = and(aux);
                    aux = "";
                    for (int j = 0; j < input.length(); j++) { 
                        if (j == (pa - 3)) {
                            if (resultB) {
                                aux += '1';
                            } else {
                                aux += '0';
                            }
                        }
                        if (j < (pa - 3) || j > pf) {
                            aux += input.charAt(j);
                        }
                    }
                    break;
                case 'r': // or
                    resultB = or(aux);
                    aux = "";
                    for (int j = 0; j < input.length(); j++) {
                        if (j == (pa - 2)) {
                            if (resultB) {
                                aux += '1';
                            } else {
                                aux += '0';
                            }
                        }
                        if (j < (pa - 2) || j > pf) {
                            aux += input.charAt(j);
                        }
                    }
                    break;
                case 't': // not
                    resultB = not(aux);
                    aux = "";
                    for (int j = 0; j < input.length(); j++) {
                        if (j == (pa - 3)) {
                            if (resultB) {
                                aux += '1';
                            } else {
                                aux += '0';
                            }
                        }
                        if (j < (pa - 3) || j > pf) {
                            aux += input.charAt(j);
                        }
                    }
                    break;

                default:
                    break;
            }

            if (aux != "") { 
                input = aux;
            }

            result = expressao(input, x + 1, npa, n);
        } else {
            if (input.charAt(n + 1) == '0') {
                result = false;
            }
        }

        return result;
    }

    public static boolean expressao(String input) { 
        String aux = "";
        int n = 0, npa = 0;

        n = input.charAt(0) - '0';

        for (int i = 0; i < input.length(); i++) { 
            if (input.charAt(i) != ' ' && input.charAt(i) != ',') {
                aux += input.charAt(i);
            }
        }
        input = aux;
        aux = "";
        for (int i = 0; i < input.length(); i++) { 
            if ((input.charAt(i) >= 'A' && input.charAt(i) <= ('A' + n - 1))) {
                aux += input.charAt(input.charAt(i) - 'A' + 1);
            } else {
                aux += input.charAt(i);
            }
        }
        input = aux;

        for (int i = 0; i < input.length(); i++) { 
            if (input.charAt(i) == '(') {
                npa++;
            }
        }

        return expressao(input, 0, npa, n);

    }

    public static boolean stop(String input)
    {
        boolean stop = true;

        if(input.charAt(0) == '0')
        {
            stop = false;
        }

        return stop;
    }

    public static void main(String[] args) {

        String input = MyIO.readLine();


        while (stop(input)) {

            boolean result = expressao(input);

            if (result) {
                MyIO.println(1);
            } else {
                MyIO.println(0);
            }

            input = MyIO.readLine();

        }

    }
}
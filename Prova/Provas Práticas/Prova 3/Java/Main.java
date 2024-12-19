import java.util.*;

class god {
    String nome;
    int poder;
    int kills;
    int deaths;

}

public class Main {

    public static int compare(god a, god b) {
        int result = 0;

        if (a.poder > b.poder) {
            result = 1;
        } else if (a.poder < b.poder) {
            result = -1;
        }

        if (result == 0) {
            if (a.kills > b.kills) {
                result = 1;
            } else if (a.kills < b.kills) {
                result = -1;
            }
        }

        if (result == 0) {
            if (a.deaths < b.deaths) {
                result = 1;
            } else if (a.deaths > b.deaths) {
                result = -1;
            }
        }

        if(result == 0)
        {
            result = a.nome.compareTo(b.nome);
        }

        return result;
    }

    public static void main(String args[]) {
        Scanner scanf = new Scanner(System.in);
        int rep = scanf.nextInt();
        scanf.nextLine();

        god gods[] = new god[rep];

        for (int i = 0; i < rep; i++) {
            String line = scanf.nextLine();

            String splitted[] = line.split(" ");
            god deus = new god();

            deus.nome = splitted[0];
            deus.poder = Integer.parseInt(splitted[1]);
            deus.kills = Integer.parseInt(splitted[2]);
            deus.deaths = Integer.parseInt(splitted[3]);

            gods[i] = deus;
        }

        for (int i = 0; i < rep - 1; i++) {
            for (int j = 0; j < rep - 1 - i; j++) {
                if(compare(gods[j], gods[j + 1]) == 1)
                {
                    god tmp = gods[j];
                    gods[j] = gods[j + 1];
                    gods[j + 1] = tmp;
                }
            }
        }

        System.out.println(gods[rep - 1].nome);

        scanf.close();
    }
}

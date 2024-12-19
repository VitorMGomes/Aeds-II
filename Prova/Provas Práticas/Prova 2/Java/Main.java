import java.util.*;

class Participante
{
    String nome;
    int numero;

    Participante(String line)
    {
        String splitted[] = line.split(" ");

        this.nome = splitted[0];
        this.numero = Integer.parseInt(splitted[1]);
    }
}

class Lista
{
    int maxTam = 100;
    int tam;
    Participante array[] = new Participante[maxTam];


    Lista()
    {
        this.tam = 0;
    }





    public void inserirFim(Participante x) throws Exception {
        if (tam == maxTam) {
            throw new Exception("Lista cheia!");
        }

        array[tam] = x;
        tam++;

    }

    public int remover(int pos) throws Exception {
        if (tam == 0) {
            throw new Exception("Lista vazia!");
        } else if (pos < 0 || pos > tam) {
            throw new Exception("Posição inválida!");
        }

        int resp = array[pos].numero;
        tam--; // se fizer isso depois do shift de elementos a condicao tem de ser (i < tam -
               // 1)

        for (int i = pos; i < tam; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }
}


public class Main 
{
    public static void main(String args[]) throws Exception
    {
        Scanner scanf = new Scanner(System.in);

        int n = scanf.nextInt();
        scanf.nextLine();

        while(n != 0)
        {
            Lista list = new Lista();

            for(int i = 0; i < n; i++)
            {
                String line = scanf.nextLine();
                list.inserirFim(new Participante(line));
                
            }

            if((list.array[0].numero % list.tam) % 2 != 0)
            {
                
            }



            while(list.tam > 1)
            {
                num = list.remover(num);
                

            }



        



            n = scanf.nextInt();
            if(n != 0)scanf.nextLine();
        }

        scanf.close();
    }
}
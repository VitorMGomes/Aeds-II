import java.util.*;

public class Q1
{

    static boolean condicional(int x, int y, int parametro)
    {
        boolean resp = false;

        if(x % parametro < y % parametro)
        {
            resp = true;
        }
        else{
            if(y % parametro == x % parametro)
            {
                if(x % 2 == 0 && y % 2 == 1)
                {
                    resp = true;
                }
                else
                {
                    if(x % 2 == 1 && y % 2 == 1)
                    {
                        if(x > y)
                        {
                            resp = true;
                        }
                    }
                    else if(x % 2 == 0 && y % 2 == 0)
                    {
                        if(x < y)
                        {
                            resp = true;
                        }
                    }
                }
            }
            
        }
        return resp;

        
    }


    public static void main(String args[])
    {
        Scanner scanf = new Scanner(System.in);

        int repeticoes = scanf.nextInt();
        int parametro = scanf.nextInt();

        System.out.println(repeticoes + " " + parametro);

        while(repeticoes != 0 || parametro != 0)
        {
            //inicializa o array com o numero dado
            int[] array = new int[repeticoes];

            //preencher o array
            for(int i = 0; i < repeticoes; i++)
            {               
                array[i] = scanf.nextInt();
            }

            /*
             * mod parametro
             *  
             *      impar ou par
             * 
             *          impar --> maior para menor
             * 
             *      par --> menor para o maior
            */


            for(int i = 0; i < repeticoes - 1; i++)
            {
                int menor = i;

                for(int j = i + 1; j < repeticoes; j++)
                {
                    if(array[menor]%parametro > array[j]%parametro){
                        menor = j;
                    }
                    else if( (array[menor]%parametro == array[j]%parametro) && array[menor]%2==0 && array[j]%2!=0 ){
                        menor = j;
                    }
                    else if( (array[menor]%parametro == array[j]%parametro) && array[menor]%2!=0  && array[j]%2!=0 && array[menor]<array[j] ){
                        menor = j;
                    }
                    else if( (array[menor]%parametro == array[j]%parametro) && array[menor]%2==0  && array[j]%2==0 && array[menor]>array[j] ){
                        menor = j;
                    }
                }

                int temp = array[i];
                array[i] = array[menor];
                array[menor] = temp;
            }

            for(int i = 0; i < repeticoes; i++)
            {
                System.out.println(array[i]);
            }
            
            
            
            
            
            repeticoes = scanf.nextInt();
            parametro = scanf.nextInt();
            System.out.println(repeticoes + " " + parametro);
        }



        scanf.close();
    }
}
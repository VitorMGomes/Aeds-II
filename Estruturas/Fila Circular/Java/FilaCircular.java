class FilaCircular
{
    private int array[];
    private int primeiro;
    private int ultimo;

    public FilaCircular()
    {
        this(5);
    }

    public FilaCircular(int tam)
    {
        this.array = new int[tam + 1];
        primeiro = ultimo = 0;
    }

    public void inserir(int x) throws Exception
    {
        if(((ultimo + 1) % array.length) == primeiro)
        {
            throw new Exception("Erro ao inserir");
        }
        
        array[ultimo] = x;
        ultimo = (ultimo + 1) % array.length;
    }

    public int remover() throws Exception
    {
        if(primeiro == ultimo)
        {
            throw new Exception("Erro ao remover");
        }

        int resp = array[primeiro];
        primeiro = (primeiro + 1) % array.length;

        return resp;
    }

    public void mostrar()
    {
        System.out.print("[ ");

        for(int i = primeiro; i != ultimo;i = ((i + 1) % array.length)) 
        {
            System.out.print(array[i] + " ");
        }

        System.out.println("]");
    }

    public boolean isVazia()
    {
        return (primeiro == ultimo);
    }
}
import java.util.*;

class Celula{
    public int x;
    public Celula esq;
    public Celula dir;
    public Celula sup;
    public Celula inf;
    
    Celula(){
        x = 0;
        esq = dir = sup = inf = null;
    }

    Celula (Celula esq, Celula dir, Celula sup, Celula inf){
         this.x = 0;
         this.esq = esq;
         this.dir = dir;
         this.sup = sup;
         this.inf = inf;
    }


    Celula (int x, Celula esq, Celula dir, Celula sup, Celula inf){
         this.x = x;
         this.esq = esq;
         this.dir = dir;
         this.sup = sup;
         this.inf = inf;
    }
}

class Matriz{
    public Celula inicio;
    public int lin, col;

    //construtores
    Matriz(){
        this(3,3);
    }

    Matriz(int lin, int col){
        this.lin = lin;
        this.col = col;
        inicio = new Celula();
        Celula i = inicio;
        
        //criacao primeira coluna
        for(int l=1; l<lin; l++){
            i.inf = new Celula(null, null, i, null);
            i = i.inf;
        }

        i = inicio;

        //criacao proximas coluna
        for (int c=1; c<col; c++){
            Celula iTmp = i;
            i.dir = new Celula(i, null, null, null);
            Celula jTmp = i.dir;
            for(int l=1; l<lin; l++){
                iTmp = iTmp.inf;
                jTmp.inf = new Celula(iTmp, null, jTmp, null);
                jTmp = jTmp.inf;
                iTmp.dir = jTmp;
            }
            i = i.dir;
        }
    }
    
    //Operacoes entre matrizes
    public Matriz soma(Matriz mat) throws Exception{
        if(mat.lin != this.lin || mat.col != this.col)
            throw new Exception("Matriz incompativel (linha ou coluna diferente)");
        Matriz resultado = new Matriz(lin, col);
        for(Celula i1 = inicio, i2 = mat.inicio, iF = resultado.inicio; i1 != null; i1 = i1.dir, i2 = i2.dir, iF = iF.dir){
            for(Celula j1 = i1, j2 = i2, jF = iF; j1 != null; j1 = j1.inf, j2 = j2.inf, jF = jF.inf){
                jF.x = j1.x + j2.x;
            }
        }
        return resultado;
    }
    public Matriz multiplicacao(Matriz mat) throws Exception{
        if(mat.lin != this.col || mat.col != this.lin)
            throw new Exception("Matriz incompativel (linha ou coluna diferente)");
        Matriz resultado = new Matriz(lin, lin);
        for(Celula i1 = inicio, iF = resultado.inicio; i1 != null; i1 = i1.inf, iF = iF.inf){
            Celula i2 = mat.inicio;
            Celula iFtemp = iF;
            while(i2 != null){
                for(Celula j1 = i1, j2 = i2; j1 != null; j1 = j1.dir, j2 = j2.inf){
                    iFtemp.x += j1.x * j2.x;
                }
                i2 = i2.dir;
                iFtemp = iFtemp.dir;
            }
        }
        return resultado;
    }

    //Mostrar na tela
    public void print(){
        for(Celula l = inicio; l != null; l = l.inf){
            for(Celula c = l; c != null; c = c.dir){
                System.out.print(c.x + " ");
            }
            System.out.println();        }
    }
    public void printDiagonalPrincipal(){
        Celula i = inicio;
        while(i != null){
            System.out.print(i.x + " ");
            if(i.dir != null){
                i = i.dir;
                i = i.inf;
            } else {
                i = null;
            }
        }
        System.out.println();    }
    public void printDiagonalSecundaria(){
        Celula i = inicio;
        for(; i.dir != null; i = i.dir);
        while(i != null){
            System.out.print(i.x + " ");
            if(i.esq != null){
                i = i.esq;
                i = i.inf;
            } else {
                i = null;
            }
        }
        System.out.println();
    }

    //Guardar valores na matriz
    public int modificar(int x, int lin, int col) throws Exception{
        if(lin<this.lin || col<this.col)
            throw new Exception("Posicao invalida");
        int rem;
        Celula i = inicio;

        while(lin > 0){
            i = i.inf;
            lin--;
        }
        while(col > 0){
            i = i.dir;
            col--;
        }
        rem = i.x;
        i.x = x;
        return rem;
    }
    public void read(Scanner scanf){
        for(Celula l = inicio; l != null; l = l.inf){
            for(Celula c = l; c != null; c = c.dir){
                c.x = scanf.nextInt();
            }
        }
    }
}

class Main{
    public static void main(String[] args){

        Scanner scanf = new Scanner(System.in);
        int n = scanf.nextInt();
        try{
            while(n-- > 0){
                
                Matriz matriz1 = new Matriz(scanf.nextInt(), scanf.nextInt());
                matriz1.read(scanf);
                Matriz matriz2 = new Matriz(scanf.nextInt(), scanf.nextInt());            
                matriz2.read(scanf);

                matriz1.printDiagonalPrincipal();
                matriz1.printDiagonalSecundaria();
                (matriz1.soma(matriz2)).print();
                (matriz1.multiplicacao(matriz2)).print();
            }
        } catch (Exception e){
            System.out.println("ERRO: "+ e.getMessage());
            e.printStackTrace();
        }
    }
}
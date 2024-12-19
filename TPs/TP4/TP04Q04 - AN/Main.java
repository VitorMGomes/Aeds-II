import java.time.*;
import java.util.*;
import java.io.*;
import java.text.*;

class TP {
    private long startTime, endTime;

    public void startTime() {
        startTime = System.nanoTime();
    }

    public void endTime() {
        endTime = System.nanoTime();
    }

    public double Time() {
        return (endTime - startTime) / 1000000;
    }

    private int comp = 0, mov = 0;

    public void addMov(int i) {
        this.mov += i;
    }

    public void addComp(int i) {
        this.comp += i;
    }

    public void pesquisaFile(String name) {
        try {
            PrintWriter write = new PrintWriter(new FileWriter(name));

            write.printf("Matrícula: 800643\t");
            write.printf("Tempo de execução: " + Time() + "ms" + "\t");
            write.printf("Comparações: " + comp);

            write.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void ordenacaoFile(String name) {
        try {
            PrintWriter write = new PrintWriter(new FileWriter(name));

            write.printf("Matrícula: 800643\t");
            write.printf("Tempo de execução: " + Time() + "ms" + "\t");
            write.printf("Comparações: " + comp + "\t");
            write.printf("Movimentações: " + mov);

            write.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class Pokemon {

    private int id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private Double weight;
    private Double height;
    private int captureRate;
    private boolean legendary;
    private Date captureDate;
    // private String captureDate;

    // -------------------------------------------------------------- Construtor
    // Vazio -------------------------------------------------------------- //
    public Pokemon() {
        this.id = 0;
        this.generation = 0;
        this.name = "";
        this.description = "";
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.weight = 0.0;
        this.height = 0.0;
        this.captureRate = 0;
        this.legendary = false;
        this.captureDate = new Date();
    }

    // -------------------------------------------------------------- Construtor
    // -------------------------------------------------------------- //
    public Pokemon(int id, int generation, String name, String description, ArrayList<String> types,
            ArrayList<String> abilities,
            Double weight,
            Double height, int captureRate, boolean legendary, Date captureDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.types = types;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.legendary = legendary;
        this.captureDate = captureDate;
    }

    // -------------------------------------------------------------- Getters &
    // Setters -------------------------------------------------------------- //
    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getGeneration() {
        return generation;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getHeight() {
        return height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLegendary(boolean legendary) {
        this.legendary = legendary;
    }

    public boolean isLegendary() {
        return legendary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }
    // -------------------------------------------------------------- Getters &
    // Setters -------------------------------------------------------------- //

    public Pokemon myClone() {
        Pokemon clonado = new Pokemon(id, generation, name, description, types, abilities,
                weight,
                height, captureRate, legendary, captureDate);

        return clonado;
    }

    // -------------------------------------------------------------- Formatando
    // Types -------------------------------------------------------------- //
    private ArrayList<String> formatTypes(String type1, String type2) {
        ArrayList<String> types = new ArrayList<>();
        if (type1 != "") {
            String type1Fixed = "\'" + type1 + "\'";
            types.add(type1Fixed.trim());
        }
        if (type2 != "") {
            String type2Fixed = "\'" + type2 + "\'";
            types.add(type2Fixed.trim());
        }
        return types;
    }

    // -------------------------------------------------------------- Formatando
    // Abilidades -------------------------------------------------------------- //
    private ArrayList<String> formatAbilitieList(String listAbilities) {
        ArrayList<String> abilities = new ArrayList<>();

        String splitted[] = listAbilities.split(",");
        for (int i = 0; i < splitted.length; i++) {
            abilities.add(splitted[i].trim());
        }

        return abilities;
    }

    public void ler(String line) {
        String newLine = "";
        boolean insideQuotes = false;
        int tam = line.length();

        // substituindo as virgulas que separam o as colunas do csv por ";"
        for (int i = 0; i < tam; i++) {
            char currentChar = line.charAt(i);

            if (currentChar == '"') {
                insideQuotes = !insideQuotes;
            }

            if (!insideQuotes) {

                if (currentChar == ',') {
                    newLine += ';';
                } else if (currentChar != '\"') {
                    newLine += currentChar;
                }
            } else {

                if (currentChar != '"' && currentChar != '[' && currentChar != ']') {
                    newLine += currentChar;
                }
            }
        }

        line = newLine;

        String splitted[] = line.split(";");

        try {
            setId(Integer.parseInt(splitted[0]));
            setGeneration(Integer.parseInt(splitted[1]));
            setName(splitted[2]);
            setDescription(splitted[3]);

            setTypes(formatTypes(splitted[4], splitted[5]));

            setAbilities(formatAbilitieList(splitted[6]));

            if (!splitted[7].isEmpty()) {
                setWeight(Double.parseDouble(splitted[7]));
            }

            if (!splitted[8].isEmpty()) {
                setHeight(Double.parseDouble(splitted[8]));
            }

            setCaptureRate(Integer.parseInt(splitted[9]));

            setLegendary(splitted[10].equals("1"));

            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            setCaptureDate(formater.parse(splitted[11]));

        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter número: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Erro ao formatar data: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Erro: dados insuficientes. Verifique o número de campos.");
        } catch (NullPointerException e) {
            System.err.println("Erro: dado nulo encontrado.");
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }

    }

    public static Pokemon[] readDb() {

        Pokemon pokemons[] = new Pokemon[801];
        Scanner reader = null;

        try {
            reader = new Scanner(new FileReader("/tmp/pokemon.csv"));

            reader.nextLine();

            for (int i = 0; reader.hasNextLine(); i++) {
                String line = reader.nextLine();
                Pokemon pokemon = new Pokemon();

                try {
                    pokemon.ler(line);
                    pokemons[i] = pokemon;
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha " + (i + 1) + ": " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return pokemons;
    }

    public void mostrar() {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("[#" + this.id + " -> " + this.name + ": " + this.description + " - " + types + " - "
                + abilities + " - " + this.weight + "kg - " + this.height + "m - " + this.captureRate + "% - "
                + this.legendary + " - " + this.generation + " gen" + "] - " + formater.format(captureDate));
    }

}

class No {
    Pokemon personagem;
    No esq, dir;
    boolean cor;

    No(Pokemon personagem) {
        this.personagem = personagem;
        esq = dir = null;
        this.cor = true;
    }

}

class Arvore {
    No raiz;

    Arvore() {
        raiz = null;
    }

    public void inserir(Pokemon personagem) {
        if (raiz == null) {
            raiz = new No(personagem);
        } else if (raiz.dir == null && raiz.esq == null) {
            if (compare(personagem, raiz.personagem) < 0) {
                raiz.esq = new No(personagem);
            } else {
                raiz.dir = new No(personagem);
            }
        } else if (raiz.esq == null) {
            if (compare(personagem, raiz.personagem) < 0) {
                raiz.esq = new No(personagem);
            } else if (compare(personagem, raiz.dir.personagem) < 0) {
                raiz.esq = new No(raiz.personagem);
                raiz.personagem = personagem;
            } else {
                raiz.esq = new No(raiz.personagem);
                raiz.personagem = raiz.dir.personagem;
                raiz.dir.personagem = personagem;
            }

            raiz.esq.cor = raiz.dir.cor = false;
        } else if (raiz.dir == null) {
            if (compare(personagem, raiz.personagem) > 0) {
                raiz.dir = new No(personagem);
            } else if (compare(personagem, raiz.esq.personagem) > 0) {
                raiz.dir = new No(raiz.personagem);
                raiz.personagem = personagem;
            } else {
                raiz.dir = new No(raiz.personagem);
                raiz.personagem = raiz.esq.personagem;
                raiz.esq.personagem = personagem;
            }
        } else {
            inserir(personagem, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void inserir(Pokemon personagem, No bisAvo, No avo, No pai, No i) {
        if (i == null) {
            if (compare(personagem, pai.personagem) < 0) {
                i = pai.esq = new No(personagem);
            } else {
                i = pai.dir = new No(personagem);
            }

            if (pai.cor == true) {
                balancear(bisAvo, avo, pai, i);
            }
        } else {
            is4No(bisAvo, avo, pai, i);

            if (compare(personagem, i.personagem) < 0) {
                inserir(personagem, avo, pai, i, i.esq);
            } else if (compare(personagem, i.personagem) > 0) {
                inserir(personagem, avo, pai, i, i.dir);
            } else {
                System.out.println("Erro, elemento repetido");
            }
        }
    }

    public void caminharCentral() {
        caminharCentral(raiz);
    }

    public void pesquisa(String nome, TP tp) {
        System.out.print(nome + "\nraiz");
        pesquisa(raiz, nome, tp);
    }

    private void pesquisa(No i, String nome, TP tp) {
        if (i == null) {
            System.out.println(" NAO");
            tp.addComp(1);
        } else if (compare(nome, i.personagem) > 0) {
            tp.addComp(2);
            System.out.print(" dir");
            pesquisa(i.dir, nome, tp);
        } else if (compare(nome, i.personagem) < 0) {
            tp.addComp(3);
            System.out.print(" esq");
            pesquisa(i.esq, nome, tp);
        } else {
            System.out.println(" SIM");
        }
    }

    private int compare(Pokemon a, Pokemon b) {
        return a.getName().compareTo(b.getName());
    }

    private int compare(String nome, Pokemon a) {
        return nome.compareTo(a.getName());
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            i.personagem.mostrar();
            System.out.println();
            caminharCentral(i.dir);
        }
    }

    private void balancear(No bisAvo, No avo, No pai, No i) {
        if (pai.cor == true) {
            if (compare(pai.personagem, avo.personagem) > 0) {
                if (compare(i.personagem, pai.personagem) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo.dir = rotacaoDir(pai);
                    avo = rotacaoEsq(avo);
                }
            } else {
                if (compare(i.personagem, pai.personagem) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo.esq = rotacaoEsq(pai);
                    avo = rotacaoDir(avo);
                }
            }
        }

        if (bisAvo == null) {
            raiz = avo;
        } else if (compare(avo.personagem, bisAvo.personagem) < 0) {
            bisAvo.esq = avo;
        } else {
            bisAvo.dir = avo;
        }

        avo.cor = false;

        avo.esq.cor = avo.dir.cor = true;
    }

    private No rotacaoDir(No i) {
        No tmp = i.esq;
        i.esq = tmp.dir;
        tmp.dir = i;

        return tmp;
    }

    private No rotacaoEsq(No i) {
        No tmp = i.dir;
        i.dir = tmp.esq;
        tmp.esq = i;

        return tmp;
    }

    private void is4No(No bisAvo, No avo, No pai, No i) {
        if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;

            if (i == raiz) {
                i.cor = false;
            } else if (pai.cor == true) {
                balancear(bisAvo, avo, pai, i);
            }
        }
    }
}

public class Main {

    public static Pokemon[] fullDB = new Pokemon[801];

    public static void main(String args[]) {
        fullDB = Pokemon.readDb();

        Arvore arvore = new Arvore();

        TP tp = new TP();

        Scanner scanf = new Scanner(System.in);

        int numeros[] = new int[100];
        int len = 0;

        for (String input = scanf.nextLine(); !input.equals("FIM"); input = scanf.nextLine()) {
            numeros[len] = Integer.parseInt(input);
            len++;
        }

        for (int i = 0; i < len; i++) {
            arvore.inserir(fullDB[numeros[i] - 1].myClone());
        }


        String name = scanf.nextLine();

        tp.startTime();
        while(!name.equals("FIM"))
        {
            arvore.pesquisa(name, tp);

            name = scanf.nextLine();
        }
        tp.endTime();

        tp.pesquisaFile("800643_alvinegra.txt");

        scanf.close();

    }
}

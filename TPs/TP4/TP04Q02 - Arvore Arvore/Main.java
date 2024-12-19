import java.io.*;
import java.text.*;
import java.util.*;

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

class noSec{
	public String data;
	public noSec esq;
	public noSec dir;

	noSec(String x){
		data = x;
		esq = null;
		dir = null;
	}

}

class treeSec {
    public noSec root;

    treeSec() {
        root = null;
    }

    public void add(String x, TP tp) {
        root = add(x, root, tp);
    }

    private noSec add(String x, noSec i, TP tp) {
        if (i == null) {
            tp.addComp(1);
            i = new noSec(x);
        } else if (x.compareTo(i.data) < 0) {
            tp.addComp(1);
            i.esq = add(x, i.esq, tp);
        } else {
            tp.addComp(1);
            i.dir = add(x, i.dir, tp);
        }
        return i;
    }

    public boolean search(String x, TP tp) {
        return search(x, root, tp);
    }

    private boolean search(String x, noSec i, TP tp) {
        while (i != null) {
            if (i.data.equals(x)) {
                tp.addComp(1);
                return true;
            } else if (x.compareTo(i.data) > 0) {
                tp.addComp(1);
                System.out.print("dir ");
                i = i.dir;
            } else {
                tp.addComp(1);
                System.out.print("esq ");
                i = i.esq;
            }
        }
        return false;
    }
}


class Node{
	public int data;
	public treeSec sRoot;
	public Node esq;
	public Node dir;

	public Node(int x){
		sRoot = new treeSec();
		data = x;
		esq = null;
		dir = null;
	}

}

class Tree {
    public Node root;

    public Tree() {
        root = null;
    }

    public boolean walk(String str, TP tp) {
        System.out.println("=> " + str );
        System.out.print("raiz ");
        boolean[] found = new boolean[1]; // Usado para rastrear se o elemento foi encontrado
        found[0] = false;
        walk(root, str, found, tp);
        return found[0];
    }

    private void walk(Node i, String str, boolean[] found, TP tp) {
        if (i != null) {
            found[0] = i.sRoot.search(str, tp);
            if (!found[0]) {
                System.out.print(" ESQ ");
                walk(i.esq, str, found, tp);
            }
            if (!found[0]) {
                System.out.print(" DIR ");
                walk(i.dir, str, found, tp);
            }
        }
    }

    public void addStart(int x) {
        root = addStart(x, root);
    }

    private Node addStart(int x, Node i) {
        if (i == null) {
            i = new Node(x);
        } else if (x < i.data) {
            i.esq = addStart(x, i.esq);
        } else if (x > i.data) {
            i.dir = addStart(x, i.dir);
        }
        return i;
    }

    public void add(int x, String name, TP tp) {
        root = add(x, root, name, tp);
    }

    private Node add(int x, Node i, String name, TP tp) {
        if (i == null) {
            tp.addComp(1);
            i = new Node(x);
            i.sRoot.add(name, tp);
        } else if (x < i.data) {
            tp.addComp(1);
            i.esq = add(x, i.esq, name, tp);
        } else if (x > i.data) {
            tp.addComp(1);
            i.dir = add(x, i.dir, name, tp);
        } else {
            i.sRoot.add(name, tp);
        }
        return i;
    }
}

public class Main {
    public static void main(String[] args) {
        Tree arvore = new Tree();
        Pokemon[] pokemons = Pokemon.readDb(); // Carrega os pokemons do CSV
        TP tp = new TP();

        Scanner scanf = new Scanner(System.in);

        int[] numeros = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for (int num : numeros) {
            arvore.addStart(num);
        }

        // Inserir pokemons na árvore
        String input = scanf.nextLine();
        while (!input.equals("FIM")) {
            int id = Integer.parseInt(input) - 1;
            arvore.add((pokemons[id].getCaptureRate() % 15), pokemons[id].getName(), tp);
            input = scanf.nextLine();
        }

        input = scanf.nextLine();
        while (!input.equals("FIM")) {
            System.out.println(arvore.walk(input, tp) ? "SIM" : "NAO");
            input = scanf.nextLine();   
        }

        tp.pesquisaFile("800643_arvoreArvore.txt");

        scanf.close();
    }
}


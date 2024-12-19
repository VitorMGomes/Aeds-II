import java.time.*;
import java.util.*;
import java.io.*;
import java.text.*;

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

class Lista {

    private int tam;
    private Pokemon array[];

    Lista(int maxTam) {
        tam = 0;
        array = new Pokemon[maxTam];
    }

    public void inserirFim(Pokemon x) throws Exception {
        if (tam == array.length) {
            throw new Exception("Lista cheia! Erro ao inserir no fim");
        }

        array[tam] = x;
        tam++;

    }

    public void inserirInicio(Pokemon x) throws Exception {
        if (tam == array.length) {
            throw new Exception("Lista cheia! Erro ao inserir no inicio");
        }

        for (int i = tam; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = x;
        tam++;
    }

    public void inserir(Pokemon x, int pos) throws Exception {
        if (tam == array.length) {
            throw new Exception("Lista cheia! Erro ao inserir");
        }
        if (pos > tam || pos < 0) {
            throw new Exception("Posição inválida para inserir");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tam) {
            inserirFim(x);
        }
        else
        {
            for (int i = tam; i > pos; i--) {
                array[i] = array[i - 1];
            }

            array[pos] = x;
            tam++;
        }
    }

    public Pokemon remover(int pos) throws Exception {
        if (tam == 0) {
            throw new Exception("Lista vazia! Erro ao remover");
        } else if (pos < 0 || pos > tam) {
            throw new Exception("Posição inválida! Erro ao remover");
        }

        Pokemon resp = array[pos];
        tam--; // se fizer isso depois do shift de elementos a condicao tem de ser (i < tam -
               // 1)

        for (int i = pos; i < tam; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public Pokemon removerInicio() throws Exception {
        if (tam == 0) {
            throw new Exception("Lista vazia! Erro ao remover no inicio");
        }

        Pokemon resp = array[0];
        tam--;

        for (int i = 0; i < tam; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public Pokemon removerFim() throws Exception {
        if (tam == 0) {
            throw new Exception("Lista vazia! Erro ao remover no fim");
        }

        Pokemon resp = array[tam - 1];
        tam--;

        return resp;
    }

    public void mostrar() {
        for (int i = 0; i < tam; i++) {
            System.out.print("[" + i + "] ");
            array[i].mostrar();
        }
    }

}

public class Main {

    public static Pokemon[] fullDB = new Pokemon[801];

    public static void printRemovido(Pokemon x)
    {
        System.out.println("(R) " + x.getName());
    }

    public static void operacao(String[] input, Lista lista) {
        
        try
        {
            if (input[0].equals("RI"))
            {
                Pokemon resp = lista.removerInicio();
                printRemovido(resp);
                
            }
            else if (input[0].equals("RF"))
            {
                Pokemon resp = lista.removerFim();
                printRemovido(resp);
            }
            else if (input[0].equals("R*"))
            {
                int pos = Integer.parseInt(input[1]);
                Pokemon resp = lista.remover(pos);
                printRemovido(resp);
            }
            else if (input[0].equals("II"))
            {
                Pokemon x = fullDB[Integer.parseInt(input[1]) - 1].myClone();
                lista.inserirInicio(x);
            } 
            else if (input[0].equals("IF"))
            {
                Pokemon x = fullDB[Integer.parseInt(input[1]) - 1].myClone();
                lista.inserirFim(x);
            }
            else if (input[0].equals("I*"))
            {
                int pos = Integer.parseInt(input[1]);
                Pokemon x = fullDB[Integer.parseInt(input[2]) - 1].myClone();
                lista.inserir(x, pos);
            }
            else
            {
                System.out.println("Erro no input");
            }
        } catch(Exception e) {

            System.err.println(e);
        }
    }

    public static void main(String args[]) {
        Lista list = new Lista(100);

        fullDB = Pokemon.readDb();

        Scanner scanf = new Scanner(System.in);

        int numeros[] = new int[100];
        int len = 0;

        for (String input = scanf.nextLine(); !input.equals("FIM"); input = scanf.nextLine()) {
            numeros[len] = Integer.parseInt(input);
            len++;
        }

        try {

            for (int i = 0; i < len; i++) {
                list.inserirFim(fullDB[numeros[i] - 1].myClone());
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        int num = scanf.nextInt();
        scanf.nextLine();

        for (int i = 0; i < num; i++) {
            String input = scanf.nextLine();
            String[] splitted = input.split(" ");
            operacao(splitted, list);

        }

        list.mostrar();

        scanf.close();

    }
}

import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Celula {
    Character personagem;

    Celula prox;

    public Celula() {
        this(null);
    }

    public Celula(Character x) {
        this.personagem = x;

        this.prox = null;
    }

}

class ListaFlex {
    private Celula primeiro, ultimo;

    public ListaFlex() {
        primeiro = ultimo = new Celula();
    }

    public int getTamanho() {
        int tamanho = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, tamanho++)
            ;

        return tamanho;
    }

    public void inserirFim(Character x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public void inserirInicio(Character x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if (primeiro == ultimo) {
            ultimo = tmp;
        }

        tmp = null;
    }

    public void inserir(Character x, int pos) throws Exception {
        int tamanho = getTamanho();

        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir. Posição inválida");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tamanho) {
            inserirFim(x);
        } else {
            
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(x);

            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;

        }
    }

    public Character removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Lista vazia");
        }
        Celula i;

        for (i = primeiro.prox; i.prox != ultimo; i = i.prox)
            ;

        Character x = ultimo.personagem;
        ultimo = i;
        i = ultimo.prox = null;

        return x;
    }

    public Character removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Lista vazia");
        }

        Character x = primeiro.prox.personagem;

        primeiro.prox = primeiro.prox.prox;

        return x;

    }

    public Character remover(int pos) throws Exception {
        int tamanho = getTamanho();
        Character x;

        if (primeiro == ultimo || pos < 0 || pos >= tamanho) {
            throw new Exception("Posição fora da lista");
        } else if (pos == 0) {
            x = removerInicio();
        } else if (pos == tamanho - 1) {
            x = removerFim();
        } else {
            Celula i = primeiro;

            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = i.prox;
            x = tmp.personagem;

            i.prox = tmp.prox;

            tmp.prox = null;
            i = tmp = null;
        }

        return x;
    }

    public void mostrar() {
        int j = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, j++) {
            System.out.print("[");
            System.out.print(j);
            i.personagem.status();
            System.out.print("\n");
        }
    }
}

class Character {
    private String id;
    private String name;
    private String[] alternativeNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    private LocalDate dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    // ============ [Getters & Setters] ============ //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // ============ [Getters & Setters] ============ //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ============ [Getters & Setters] ============ //

    public String[] getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(String[] alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    // ============ [Getters & Setters] ============ //

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    // ============ [Getters & Setters] ============ //

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    // ============ [Getters & Setters] ============ //

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    // ============ [Getters & Setters] ============ //

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    // ============ [Getters & Setters] ============ //

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    // ============ [Getters & Setters] ============ //

    public boolean getHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    // ============ [Getters & Setters] ============ //

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    // ============ [Getters & Setters] ============ //

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // ============ [Getters & Setters] ============ //

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // ============ [Getters & Setters] ============ //

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    // ============ [Getters & Setters] ============ //

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    // ============ [Getters & Setters] ============ //

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // ============ [Getters & Setters] ============ //

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    // ============ [Getters & Setters] ============ //

    public boolean isWizard() {
        return wizard;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }

    // ============ [Constructor] ============ //
    public Character() {

        this.setId(" ");
        this.setName(" ");
        this.setAlternativeNames(new String[0]);
        this.setHouse(" ");
        this.setAncestry(" ");
        this.setSpecies(" ");
        this.setPatronus(" ");
        this.setHogwartsStaff(false);
        this.setHogwartsStudent(false);
        this.setActorName(" ");
        this.setAlive(false);
        this.setDateOfBirth(LocalDate.now());
        this.setYearOfBirth(0);
        this.setEyeColour(" ");
        this.setGender(" ");
        this.setHairColour(" ");
        this.setWizard(false);
    }

    public Character(String id, String name, String[] alternativeNames, String house, String ancestry,
            String species, String patronus, boolean hogwartsStaff, boolean hogwartsStudent, String actorName,
            boolean alive, LocalDate dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour,
            boolean wizard) {
        this.setId(id);
        this.setName(name);
        this.setAlternativeNames(alternativeNames);
        this.setHouse(house);
        this.setAncestry(ancestry);
        this.setSpecies(species);
        this.setPatronus(patronus);
        this.setHogwartsStaff(hogwartsStaff);
        this.setHogwartsStudent(hogwartsStudent);
        this.setActorName(actorName);
        this.setAlive(alive);
        this.setDateOfBirth(dateOfBirth);
        this.setYearOfBirth(yearOfBirth);
        this.setEyeColour(eyeColour);
        this.setGender(gender);
        this.setHairColour(hairColour);
        this.setWizard(wizard);
    }

    public void ler(String line) {

        String[] data = line.split(";");

        this.setId(data[0]);
        this.setName(data[1]);

        String[] splittedNames = data[2].split(",");
        ArrayList<String> tratedArray = new ArrayList<String>();
        for (int i = 0; i < splittedNames.length; i++) {
            String alternateNameCleaned = substituirChaves(splittedNames[i].trim());
            if (!alternateNameCleaned.isEmpty()) {
                tratedArray.add(alternateNameCleaned);
            }
        }
        this.setAlternativeNames(tratedArray.toArray(new String[tratedArray.size()]));

        this.setHouse(data[3]);
        this.setAncestry(data[4]);
        this.setSpecies(data[5]);
        this.setPatronus(data[6]);
        this.setHogwartsStaff(toBool(data[7]));
        this.setHogwartsStudent(toBool(data[8]));
        this.setActorName(data[9]);
        this.setAlive(toBool(data[10]));
        this.setDateOfBirth(parseDate(data[12]));
        this.setYearOfBirth(Integer.parseInt(data[13]));
        this.setEyeColour(data[14]);
        this.setGender(data[15]);
        this.setHairColour(data[16]);
        this.setWizard(toBool(data[17]));

    }

    private boolean toBool(String input) {
        boolean bool = false;
        if (input.equals("VERDADEIRO")) {
            bool = true;
        }

        return bool;
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-M-yyy"));
    }

    private String substituirChaves(String alternativeName) {
        String treated = new String();
        for (int i = 0; i < alternativeName.length(); i++) {
            if (alternativeName.charAt(i) != '[' && alternativeName.charAt(i) != ']'
                    && alternativeName.charAt(i) != '\'') {
                treated += alternativeName.charAt(i);
            }
        }

        return treated;
    }

    private String tratarString(String[] array) {
        String result = "{";
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                result += array[i] + ", ";
            } else {
                result += array[i];
            }
        }
        result += "}";
        return result;
    }

    public void status() {
        String date = getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.print(" ## " + getId() + " ## " + getName() + " ## " + tratarString(getAlternativeNames()) + " ## "
                + getHouse() + " ## "
                + getAncestry() + " ## " + getSpecies() + " ## " + getPatronus() + " ## " + isHogwartsStaff() + " ## "
                + getHogwartsStudent() + " ## " + getActorName() + " ## " + isAlive() + " ## " + date
                + " ## " + getYearOfBirth() + " ## " + getEyeColour() + " ## " + getGender() + " ## " + getHairColour()
                + " ## " + isWizard() + "]");
    }

    public Character clone() {
        Character clonado = new Character();
        clonado.id = this.id;
        clonado.name = this.name;
        clonado.alternativeNames = this.alternativeNames;
        clonado.house = this.house;
        clonado.ancestry = this.ancestry;
        clonado.species = this.species;
        clonado.patronus = this.patronus;
        clonado.hogwartsStaff = this.hogwartsStaff;
        clonado.hogwartsStudent = this.hogwartsStudent;
        clonado.actorName = this.actorName;
        clonado.alive = this.alive;
        clonado.dateOfBirth = this.dateOfBirth;
        clonado.yearOfBirth = this.yearOfBirth;
        clonado.eyeColour = this.eyeColour;
        clonado.gender = this.gender;
        clonado.hairColour = this.hairColour;
        clonado.wizard = this.wizard;
        return clonado;
    }

}
// javac Q3.java; java Q3 <pub.in> saida.out -- "/tmp/characters.csv"

public class Q5 {

    public static ArrayList<Character> csv = new ArrayList<Character>();

    public static ListaFlex lista = new ListaFlex();

    public static void readDB() throws Exception {

        Scanner reader = new Scanner(new FileReader("characters.csv"));

        reader.nextLine();// pulando a primeira linha do csv

        while (reader.hasNextLine()) {

            String line = reader.nextLine();

            Character personagem = new Character();

            personagem.ler(line);

            csv.add(personagem);
        }

        reader.close();
    }

    public static void build(String id) throws Exception {
        int n = csv.size();
        for (int i = 0; i < n; i++) {
            Character a = csv.get(i);

            if (a.getId().equals(id)) {

                lista.inserirFim(a);
            }
        }

    }

    public static Character encontrar(String id) throws Exception {
        int n = csv.size();

        Character resp = new Character();

        for (int i = 0; i < n; i++) {
            Character x = csv.get(i);

            if (x.getId().equals(id)) {
                resp = x;
            }
        }

        return resp;
    }

    public static void main(String args[]) throws Exception {

        readDB();

        Scanner scanf = new Scanner(System.in);

        for (String id = scanf.nextLine(); !id.equals("FIM"); id = scanf.nextLine()) {
            build(id);
        }

        int operacoes = scanf.nextInt();

        scanf.nextLine();

        for (int i = 0; i < operacoes; i++) {
            String line = scanf.nextLine();
            int pos;

            // IF, II, I*, RF, RI, R*

            if (line.charAt(1) == '*') {
                String[] data = line.split(" ");

                pos = Integer.parseInt(data[1]);

                if (data[0].equals("R*")) {
                    Character removido = lista.remover(pos);

                    System.out.println("(R) " + removido.getName());
                } else if (data[0].equals("I*")) {
                    lista.inserir(encontrar(data[2]), pos);
                }
            } else {
                String[] data = line.split(" ");

                if (data[0].equals("RI")) {
                    Character removido = lista.removerInicio();

                    System.out.println("(R) " + removido.getName());
                }

                else if (data[0].equals("RF")) {
                    Character removido = lista.removerFim();

                    System.out.println("(R) " + removido.getName());
                } else if (data[0].equals("II")) {
                    lista.inserirInicio(encontrar(data[1]));
                } else {
                    lista.inserirFim(encontrar(data[1]));
                }
            }

        }

        lista.mostrar();

        scanf.close();

    }
}
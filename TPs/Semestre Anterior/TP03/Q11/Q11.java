import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class TP
{
    private Instant start, end;

    public void startTime()
    {
        this.start = Instant.now();
    }

    public void endTime()
    {
        this.end = Instant.now();
    }

    public double Time()
    {
        return Duration.between(start, end).getNano() / 1000000000.0;
    }

    private int comp = 0, mov = 0;

    public void addMov(int i)
    {
        this.mov += i;
    }

    public void addComp(int i)
    {
        this.comp += i;
    }

    public void pesquisaFile(String name) throws Exception
    {
        PrintWriter write = new PrintWriter(new FileWriter(name));

        write.printf("Matrícula: 800643\t");
        write.printf("Tempo de execução: " + Time() + "\t");
        write.printf("Comparações: " + comp);

        write.close();
    }

    public void ordenacaoFile(String name) throws Exception
    {
        PrintWriter write = new PrintWriter(new FileWriter(name));

        write.printf("Matrícula: 800643\t");
        write.printf("Tempo de execução: " + Time() + "\t");
        write.printf("Comparações: " + comp + "\t");
        write.printf("Movimentações: " + mov);

        write.close();
    }
}

class CelulaDupla {
    public CelulaDupla ant;
    public Character x;
    public CelulaDupla prox;

    CelulaDupla() {
        this(null, new Character(), null);
    }

    CelulaDupla(Character x) {
        this(null, x, null);
    }

    CelulaDupla(CelulaDupla ant, Character x, CelulaDupla prox) {
        this.ant = ant;
        this.prox = prox;
        this.x = x;
    }

    public CelulaDupla clone() {
        return new CelulaDupla(ant, x, prox);
    }

    public void relink(CelulaDupla ant, CelulaDupla prox) {
        this.ant = ant;

        if (ant != null) {
            ant.prox = this;
        }

        this.prox = prox;

        if (prox != null) {
            prox.ant = this;
        }
    }

}

class ListaDupla {
    CelulaDupla primeiro;
    CelulaDupla ultimo;
    int tam;

    ListaDupla() {
        tam = 0;
        primeiro = ultimo = new CelulaDupla();
    }

    public void inserirFim(CelulaDupla add) {
        add.prox = null;
        add.ant = ultimo;
        ultimo.prox = add;
        ultimo = add;

        tam++;
    }

    void swap(CelulaDupla a, CelulaDupla b, TP tp) {
        Character tmp = a.x.clone();
        a.x = b.x;
        b.x = tmp;

        tp.addMov(3);
    }

    int compValores(Character a, Character b, TP tp) {
        int resultado = a.getHouse().compareTo(b.getHouse());
        tp.addComp(1);
        if (resultado == 0) {
            resultado = a.getName().compareTo(b.getName());
            tp.addComp(1);
        }
        return resultado;
    }

    void quickSort(CelulaDupla esq, CelulaDupla dir, TP tp) {
        CelulaDupla pivo = esq;
        CelulaDupla i = esq.prox, j = dir;
        while (i.ant != j && i.ant != j.prox) {
            while (compValores(i.x, pivo.x, tp) < 0) {
                i = i.prox;
            }
            while (compValores(j.x, pivo.x, tp) > 0) {
                j = j.ant;
            }

            if (i.ant != j) {
                swap(i, j, tp);
                i = i.prox;
                j = j.ant;
            }
        }
        swap(pivo, j, tp);
        if (j != esq)
            quickSort(esq, j, tp);
        if (i != dir)
            quickSort(i, dir, tp);
    }

    public void ordenaFilaDupla(TP tp) {
        if (primeiro.prox != null && primeiro.prox != ultimo) {
            quickSort(primeiro.prox, ultimo, tp);
        }
    }

    public void mostrar()
    {
        for(CelulaDupla i = primeiro.prox; i != null; i = i.prox){
            i.x.status();
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
        System.out.print("[" + getId() + " ## " + getName() + " ## " + tratarString(getAlternativeNames()) + " ## "
                + getHouse() + " ## "
                + getAncestry() + " ## " + getSpecies() + " ## " + getPatronus() + " ## " + isHogwartsStaff() + " ## "
                + getHogwartsStudent() + " ## " + getActorName() + " ## " + isAlive() + " ## " + date
                + " ## " + getYearOfBirth() + " ## " + getEyeColour() + " ## " + getGender() + " ## " + getHairColour()
                + " ## " + isWizard() + "]\n");
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
// javac Q1.java; java Q1 <pub.in> saida.out -- "/tmp/characters.csv"

public class Q11 {

    public static ArrayList<Character> csv = new ArrayList<Character>();

    public static ListaDupla lista = new ListaDupla();

    public static void readDB() throws Exception {

        Scanner reader = new Scanner(new FileReader("/tmp/characters.csv"));

        reader.nextLine();// pulando a primeira linha do csv

        while (reader.hasNextLine()) {

            String line = reader.nextLine();

            Character personagem = new Character();

            personagem.ler(line);

            csv.add(personagem);
        }

        reader.close();
    }
a
    public static void build(String id) throws Exception {
        int n = csv.size();
        for (int i = 0; i < n; i++) {
            Character a = csv.get(i);

            if (a.getId().equals(id)) {
                CelulaDupla celula = new CelulaDupla(a);
                lista.inserirFim(celula);
                celula = null;
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
        TP tp = new TP();

        readDB();

        Scanner scanf = new Scanner(System.in);

        for (String id = scanf.nextLine(); !id.equals("FIM"); id = scanf.nextLine()) {
            build(id);
        }

        tp.startTime();

        lista.ordenaFilaDupla(tp);

        tp.endTime();

        tp.ordenacaoFile("800643_quicksort2.txt");

        lista.mostrar();

        scanf.close();

    }
}
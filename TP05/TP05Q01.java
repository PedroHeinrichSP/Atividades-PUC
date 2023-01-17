import java.io.*;
import java.text.*;
import java.util.*;

public class TP05Q01 {
    static InputStreamReader iS = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(iS);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Tree tree = new Tree();
        String entrada;
        String FIM = "FIM";
        char comChar;
        int comando;
        int index = 0;
        int stupid = 0;

        int[] codes = new int[100];
        try {
            while (true) {
                entrada = br.readLine();
                if (!entrada.equals(FIM)) {
                    codes[index] = intS(entrada);
                    index++;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no código");
        }
        index = 0;

        entrada = br.readLine();

        String[] targets = new String[100];
        try {
            while (true) {
                entrada = br.readLine();
                if (entrada != null && !entrada.equals(FIM)) {
                    targets[index] = entrada;
                    index++;
                    if (entrada.charAt(1) == ' ') {
                        stupid++;
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no target");
        }

        searchName(tree, codes);
        index = stupid;

        try {
            while (targets[index] != null && index < targets.length) {
                comChar = targets[index].charAt(0);
                String temp = "";

                for (int i = 2; i < targets[index].length(); i++) {
                    temp += targets[index].charAt(i);
                }
                if (comChar == 'I') {
                    comando = intS(temp);
                    searchName(tree, comando);

                } else if (comChar == 'R') {
                    tree.remover(temp);

                }
                index++;

            }
            index = 0;

        } catch (Exception e) {
            System.out.println("Erro no comando");
        }

        for (int index1 = stupid; index1 < targets.length; index1++) {
            if (targets[index1] != null && !targets[index1].equals(FIM)) {
                printStr(targets[index1]);
                tree.searchPrint(targets[index1]);
                jumpLine();
            }
        }

    }

    public static String readInput() throws IOException {
        return br.readLine();
    }

    public static int intS(String s) {
        return Integer.parseInt(s);
    }

    public static int[] resetArray(int[] array) {
        array = new int[array.length];
        return array;
    }

    /**
     * Printa o elemento STRING
     * 
     * @param element
     */
    static void printStr(String element) {
        System.out.println(element);
    }

    static void printInt(int element) {
        System.out.println(element);
    }

    static void printStrSl(String element) {
        System.out.print(element);
    }

    static void printCharSl(char element) {
        System.out.print(element);
    }

    static void jumpLine() {
        printStr("");
    }

    static String removeWhiteSpaces(String s) {
        return s.replaceAll(" ", "");
    }

    static int readInt()
            throws IOException {
        return intS(removeWhiteSpaces(readInput()));
    }

    static void searchName(Tree tree, int[] codes) throws Exception {
        int index = 0;
        String line = "";
        Game games = new Game();
        Scanner ac = new Scanner(new FileReader("/tmp/games.csv"));
        if (ac.hasNext()) {
            line = ac.nextLine();
            while (index < codes.length && codes[index] != 0) {
                games = new Game();
                games.ler(line);
                if (!(games.getAppId() == codes[index]) && ac.hasNext()) {
                    line = ac.nextLine();
                } else if (games.getAppId() == codes[index]) {
                    tree.inserir(games.getName());
                    index++;
                    ac = new Scanner(new FileReader("/tmp/games.csv"));
                }
                if (index == codes.length) {
                    break;
                }
            }
        }
        ac.close();
    }

    static void searchName(Tree tree, int codes) throws Exception {
        String line = "";
        Game games = new Game();
        Scanner ac = new Scanner(new FileReader("/tmp/games.csv"));
        if (ac.hasNext()) {
            line = ac.nextLine();
            while (ac.hasNext()) {
                games = new Game();
                games.ler(line);
                if (!(games.getAppId() == codes) && ac.hasNext()) {
                    line = ac.nextLine();
                } else if (games.getAppId() == codes) {
                    tree.inserir(games.getName());
                    ac = new Scanner(new FileReader("/tmp/games.csv"));
                    break;
                }
            }

        }
        ac.close();
    }

}

class Node {
    public String name;
    public Node dir;
    public Node esq;

    Node() {
        name = "";
        dir = null;
        esq = null;
    }

    Node(String name, Node dir, Node esq) {
        this.name = name;
        this.dir = dir;
        this.esq = esq;
    }

    Node(String name) {
        this.name = name;
        dir = null;
        esq = null;
    }
}

class Tree {
    private Node root;
    char letra;
    Tree dir;
    Tree esq;

    public String getRoot() {
        return this.root.name;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    Tree() {
        this(null, null, null, '0');
    }

    Tree(Node root, Tree dir, Tree esq, char letra) {
        this.root = root;
        this.dir = dir;
        this.esq = esq;
        this.letra = letra;
    }

    Tree(char letra) {
        this.root = null;
        this.dir = null;
        this.esq = null;
        this.letra = letra;
    }

    /**
     * Insere o "element" elemento na arvore.
     * 
     * @param element
     */
    void insert(String element) {
        if (root == null) {
            root = new Node(element);
        } else {
            Node temp = new Node(element);
            Node i = root;
            Boolean done = false;
            while (done != true) {

                if (temp.name.compareTo(i.name) > 0) {
                    if (i.dir == null) {
                        i.dir = temp;
                        done = true;
                    } else if (i.dir != null) {
                        i = i.dir;
                    }
                } else if (temp.name.compareTo(i.name) < 0) {
                    if (i.esq == null) {
                        i.esq = temp;
                        done = true;
                    } else if (i.esq != null) {
                        i = i.esq;
                    }
                } else if (temp.name.equals(i.name)) {
                    printStr("Elemento existe!");
                    break;
                }
            }
        }
    }

    boolean search(String target) {
        Node i = root;
        boolean found = false;
        boolean done = false;

        while (done != true) {
            if (i.name.equals(target)) {
                found = true;
                done = true;

            } else if (target.compareTo(i.name) > 0) {
                if (i.dir == null) {
                    break;
                } else {
                    i = i.dir;

                }
            } else if (target.compareTo(i.name) < 0) {
                if (i.esq == null) {
                    break;
                } else {
                    i = i.esq;

                }
            }
        }
        return found;
    }

    void searchPrint(String target) {
        if (searchPrint(target, root)) {
            printStrSl("SIM");
        } else {
            printStrSl("NAO");
        }
    }

    boolean searchPrint(String target, Node i) {
        boolean found = false;
        boolean done = false;
        printStrSl("=>raiz ");

        while (done != true) {
            if (i.name.equals(target)) {
                found = true;
                done = true;

            } else if (target.compareTo(i.name) > 0) {
                if (i.dir == null) {
                    break;
                } else {
                    i = i.dir;
                    printStrSl("dir ");

                }
            } else if (target.compareTo(i.name) < 0) {
                if (i.esq == null) {
                    break;
                } else {
                    i = i.esq;
                    printStrSl("esq ");

                }
            }
        }
        return found;
    }

    String remove(String target) {
        String value = "0";

        if (search(target)) {
            Node i = root;
            boolean done = false;
            while (done != true) {
                if ((target.equals(i.name)) && (i == root)) {
                    printStr("Impossivel remover root!");
                    break;
                } else if (target.compareTo(i.name) < 0) {

                    if (i.esq != null) {
                        if (i.esq.name.equals(target)) {

                            if (i.esq.esq != null && i.esq.dir != null) {
                                Node tempesq = i.esq.esq;
                                Node tempdir = i.esq.dir;

                                value = i.esq.name;
                                tempesq.dir = tempdir;
                                i.esq = tempesq;

                                done = true;

                            } else if (i.esq.esq != null) {
                                Node tempesq = i.esq.esq;

                                value = i.esq.name;
                                i.esq = tempesq;

                                done = true;

                            } else if (i.esq.dir != null) {

                                Node tempdir = i.esq.dir;

                                value = i.esq.name;
                                i.esq = tempdir;

                                done = true;

                            } else {
                                value = i.esq.name;
                                i.esq = null;
                                done = true;
                            }

                        } else {
                            i = i.esq;
                        }
                    }
                } else if (target.compareTo(i.name) > 0) {

                    if (i.dir != null) {

                        if (i.dir.name.equals(target)) {

                            if (i.dir.esq != null && i.dir.dir != null) {
                                Node tempesq = i.dir.esq;
                                Node tempdir = i.dir.dir;

                                value = i.dir.name;
                                tempdir.esq = tempesq;
                                i.dir = tempdir;

                                done = true;

                            } else if (i.dir.esq != null) {
                                Node tempesq = i.dir.esq;

                                value = i.dir.name;
                                i.dir = tempesq;

                                done = true;

                            } else if (i.dir.dir != null) {
                                Node tempdir = i.dir.dir;

                                value = i.dir.name;
                                i.dir = tempdir;

                                done = true;

                            } else {
                                value = i.dir.name;
                                i.dir = null;
                                done = true;
                            }
                        } else {

                            i = i.dir;
                        }
                    }
                }
            }
        } else {
            printStr("ELEMENTO NAO EXISTE!");
            return value;
        }
        return value;
    }

    /**
     * Chama o printTree
     */
    void printTree() {
        printTree(root);
    }

    /**
     * Printa o elemento do Nó, e anda pela arvore,
     * 
     * @param i
     */
    void printTree(Node i) {
        if (i != null) {
            if (i.esq != null) {
                printTree(i.esq);
            }
            if (i.dir != null) {
                printTree(i.dir);
            }
            printStr(i.name);

        }

    }

    char charAtZ(String str) {
        return str.charAt(0);
    }

    /**
     * Printa o elemento INTEGER
     * 
     * @param element
     */
    void printInt(int element) {
        System.out.println(element);
    }

    /**
     * Printa o elemento STRING
     * 
     * @param element
     */
    void printStr(String element) {
        System.out.println(element);
    }

    void printStrSl(String element) {
        System.out.print(element);
    }

    void jumpLine() {
        System.out.println("");
    }

    public void inserir(String x) throws Exception {
        root = inserir(x, root);
    }

    private Node inserir(String x, Node i) throws Exception {
        if (i == null) {
            i = new Node(x);
        } else if (x.compareTo(i.name) < 0) {
            i.esq = inserir(x, i.esq);

        } else if (x.compareTo(i.name) > 0) {
            i.dir = inserir(x, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    public void remover(String x) throws Exception {
        root = remover(x, root);
    }

    private Node remover(String x, Node i) throws Exception {

        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (x.compareTo(i.name) < 0) {
            i.esq = remover(x, i.esq);

        } else if (x.compareTo(i.name) > 0) {
            i.dir = remover(x, i.dir);

            // Sem no a direita.
        } else if (i.dir == null) {
            i = i.esq;

            // Sem no a esquerda.
        } else if (i.esq == null) {
            i = i.dir;

            // No a esquerda e no a direita.
        } else {
            i.esq = maiorEsq(i, i.esq);
        }

        return i;
    }

    private Node maiorEsq(Node i, Node j) {

        // Encontrou o maximo da subarvore esquerda.
        if (j.dir == null) {
            i.name = j.name; // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.

            // Existe no a direita.
        } else {
            // Caminha para direita.
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    public boolean pesquisar(String x) {
        return pesquisar(x, root);
    }

    private boolean pesquisar(String x, Node i) {
        boolean resp;
        if (i == null) {
            resp = false;

        } else if (x == i.name) {
            System.out.println("SIM ");
            resp = true;

        } else if (x.compareTo(i.name) < 0) {
            System.out.print("esq ");
            resp = pesquisar(x, i.esq);

        } else if (x.compareTo(i.name) > 0) {
            System.out.print("dir ");
            resp = pesquisar(x, i.dir);
        } else {
            System.out.print("NAO ");
            resp = true;
        }
        return resp;
    }
}

class Game {
    public static void printS(String string) {
        System.out.println(string);
    }

    public static int intV(String in) {
        return Integer.valueOf(in);
    }

    static SimpleDateFormat default_dateFormat = new SimpleDateFormat(
            "MMM/yyyy",
            Locale.ENGLISH);

    private String name, owners, website, developers;
    private ArrayList<String> languages, genres;
    private Date release_date;
    protected int app_id, age, dlcs, avg_playtime;
    private float price, upvotes;
    private boolean windows, mac, linux;
    public Game prox;

    public Game() {
        this.name = this.owners = this.website = this.developers = null;
        this.languages = new ArrayList<String>();
        this.genres = new ArrayList<String>();
        this.release_date = null;
        this.app_id = this.age = this.dlcs = this.avg_playtime = -1;
        this.price = this.upvotes = -1;
        this.windows = this.mac = this.linux = false;
        this.prox = null;
    }

    public Game(
            String name,
            String owners,
            String website,
            String developers,
            ArrayList<String> languages,
            ArrayList<String> genres,
            Date release_date,
            int app_id,
            int age,
            int dlcs,
            float upvotes,
            int avg_playtime,
            float price,
            boolean windows,
            boolean mac,
            boolean linux) {
        this.name = name;
        this.owners = owners;
        this.website = website;
        this.developers = developers;
        this.languages = languages;
        this.genres = genres;
        this.release_date = release_date;
        this.app_id = app_id;
        this.age = age;
        this.dlcs = dlcs;
        this.upvotes = upvotes;
        this.avg_playtime = avg_playtime;
        this.price = price;
        this.windows = windows;
        this.mac = mac;
        this.linux = linux;
        this.prox = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public void setReleaseDate(Date release_date) {
        this.release_date = release_date;
    }

    public void setAppId(int app_id) {
        this.app_id = app_id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDlcs(int dlcs) {
        this.dlcs = dlcs;
    }

    public void setAvgPlaytime(int avg_playtime) {
        this.avg_playtime = avg_playtime;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setUpvotes(float upvotes) {
        this.upvotes = upvotes;
    }

    public void setWindows(boolean windows) {
        this.windows = windows;
    }

    public void setMac(boolean mac) {
        this.mac = mac;
    }

    public void setLinux(boolean linux) {
        this.linux = linux;
    }

    public String getName() {
        return this.name;
    }

    public String getOwners() {
        return this.owners;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getDevelopers() {
        return this.developers;
    }

    public ArrayList<String> getLanguages() {
        return this.languages;
    }

    public ArrayList<String> getGenres() {
        return this.genres;
    }

    public Date getReleaseDate() {
        return this.release_date;
    }

    public int getAppId() {
        return this.app_id;
    }

    public int getAge() {
        return this.age;
    }

    public int getDlcs() {
        return this.dlcs;
    }

    public int getAvgPlaytime() {
        return this.avg_playtime;
    }

    public float getPrice() {
        return this.price;
    }

    public float getUpvotes() {
        return this.upvotes;
    }

    public boolean getWindows() {
        return this.windows;
    }

    public boolean getMac() {
        return this.mac;
    }

    public boolean getLinux() {
        return this.linux;
    }

    public Game clone() {
        Game cloned = new Game();
        cloned.name = this.name;
        cloned.owners = this.owners;
        cloned.website = this.website;
        cloned.developers = this.developers;
        cloned.languages = this.languages;
        cloned.genres = this.genres;
        cloned.release_date = this.release_date;
        cloned.app_id = this.app_id;
        cloned.age = this.age;
        cloned.dlcs = this.dlcs;
        cloned.avg_playtime = this.avg_playtime;
        cloned.price = this.price;
        cloned.upvotes = this.upvotes;
        cloned.windows = this.windows;
        cloned.mac = this.mac;
        cloned.linux = this.linux;
        cloned.prox = this.prox;
        return cloned;
    }

    public void ler(String line) {
        char c_search;
        int index = 0, atr_index = 0;

        // ---------------------------------- //
        // Find "AppID"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.app_id = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Name"
        if (line.charAt(atr_index) != ',') {
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                c_search = '\"';
            } else
                c_search = ',';
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    this.name = line.substring(atr_index, index);
                    if (c_search == ',')
                        index++;
                    else if (c_search == '\"')
                        index += 2;
                    atr_index = index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // ---------------------------------- //
        // Find release date
        if (line.charAt(atr_index) != ',') {
            SimpleDateFormat df;
            if (line.charAt(atr_index) == '\"') {
                df = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
                atr_index++;
                c_search = '\"';
            } else {
                df = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
                c_search = ',';
            }
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    try {
                        this.release_date = df.parse(line.substring(atr_index, index));
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                    if (c_search == ',')
                        index++;
                    else if (c_search == '\"')
                        index += 2;
                    atr_index = index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // ---------------------------------- //
        // Find "Owners"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.owners = line.substring(atr_index, index);
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Age"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.age = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Price"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.price = Float.parseFloat(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "DLCs"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.dlcs = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Languages"
        while (true) {
            index++;
            if (line.charAt(index) == ']') {
                index++;
                if (line.charAt(index) == ',')
                    index++;
                else if (line.charAt(index) == '\"')
                    index += 2;
                atr_index = index;
                break;
            } else if (line.charAt(index) == '\'') {
                int wordStart = index + 1;
                while (true) {
                    index++;
                    if (line.charAt(index) == '\'') {
                        this.languages.add(line.substring(wordStart, index));
                        break;
                    }
                }
            }
        }

        // ---------------------------------- //
        // Find "Website"
        if (line.charAt(atr_index) != ',') {
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                c_search = '\"';
            } else
                c_search = ',';

            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    this.website = line.substring(atr_index, index);
                    atr_index = ++index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // ---------------------------------- //

        // Find "Windows"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.windows = Boolean.parseBoolean(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // Find "Mac"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.mac = Boolean.parseBoolean(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // Find "Linux"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.linux = Boolean.parseBoolean(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Upvotes"
        int positives, negatives;
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                positives = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                negatives = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }
        this.upvotes = (float) (positives * 100) / (float) (positives + negatives);

        // ---------------------------------- //
        // Find "AVG Playtime"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.avg_playtime = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Developers"
        if (line.charAt(atr_index) != ',') {
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                c_search = '\"';
            } else
                c_search = ',';
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    this.developers = line.substring(atr_index, index);
                    atr_index = ++index;
                    break;
                }
            }
        } else
            atr_index = ++index;
        // ---------------------------------- //

        // Find "Genres"
        if (index < line.length() - 1) {
            if (line.charAt(index) == ',')
                atr_index = ++index;
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                while (true) {
                    index++;
                    if (line.charAt(index) == ',') {
                        this.genres.add(line.substring(atr_index, index));
                        atr_index = ++index;
                    } else if (line.charAt(index) == '\"') {
                        this.genres.add(line.substring(atr_index, line.length() - 1));
                        break;
                    }
                }
            } else
                this.genres.add(line.substring(atr_index, line.length()));
        }
    }

    public void imprimir() {
        String avg_pt = null;
        if (this.avg_playtime == 0)
            avg_pt = "null ";
        else if (this.avg_playtime < 60)
            avg_pt = this.avg_playtime + "m ";
        else {
            if (this.avg_playtime % 60 == 0)
                avg_pt = this.avg_playtime / 60 + "h ";
            else
                avg_pt = (this.avg_playtime / 60) + "h " + (this.avg_playtime % 60) + "m ";
        }

        DecimalFormat df = new DecimalFormat("##");
        System.out.println(
                this.app_id +
                        " " +
                        this.name +
                        " " +
                        default_dateFormat.format(this.release_date) +
                        " " +
                        this.owners +
                        " " +
                        this.age +
                        " " +
                        String.format("%.2f", this.price) +
                        " " +
                        this.dlcs +
                        " " +
                        this.languages +
                        " " +
                        this.website +
                        " " +
                        this.windows +
                        " " +
                        this.mac +
                        " " +
                        this.linux +
                        " " +
                        (Float.isNaN(this.upvotes) ? "0.0% " : df.format(this.upvotes) + "% ") +
                        avg_pt +
                        this.developers +
                        " " +
                        this.genres);
    }

    public String tString() {
        String avg_pt = null;
        if (this.avg_playtime == 0)
            avg_pt = "null ";
        else if (this.avg_playtime < 60)
            avg_pt = this.avg_playtime + "m ";
        else {
            if (this.avg_playtime % 60 == 0)
                avg_pt = this.avg_playtime / 60 + "h ";
            else
                avg_pt = (this.avg_playtime / 60) + "h " + (this.avg_playtime % 60) + "m ";
        }

        DecimalFormat df = new DecimalFormat("##");
        String aux = (this.app_id +
                " " +
                this.name +
                " " +
                default_dateFormat.format(this.release_date) +
                " " +
                this.owners +
                " " +
                this.age +
                " " +
                String.format("%.2f", this.price) +
                " " +
                this.dlcs +
                " " +
                this.languages +
                " " +
                this.website +
                " " +
                this.windows +
                " " +
                this.mac +
                " " +
                this.linux +
                " " +
                (Float.isNaN(this.upvotes) ? "0.0% " : df.format(this.upvotes) + "% ") +
                avg_pt +
                this.developers +
                " " +
                this.genres);
        return aux;
    }
}
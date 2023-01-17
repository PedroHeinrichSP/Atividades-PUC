import java.io.File;
import java.util.*;

class Games2 {

    private Games2 next;

    public Games2 elemento;
    public Games2 prox;
    private int dia = 0;

    private int app_id = 0;
    private int age;
    private float price;
    private int dlcs;
    private float upvotes;
    private int avg_pt;
    private String name;
    private String release_date;
    private String owner;
    private String developers;
    private String[] genres;
    private String[] languages;
    private String website;
    private boolean windows;
    private boolean mac;
    private boolean linux;

    // set
    public void setAppid(int app_id) {
        this.app_id = app_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDlcs(int dlcs) {
        this.dlcs = dlcs;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public void setUpvotes(float upvotes) {
        this.upvotes = upvotes;
    }

    public void setAvgPt(int avg_pt) {
        this.avg_pt = avg_pt;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setNext(Games2 next) {
        this.next = next;
    }

    // get
    public int getAppid() {
        return this.app_id;
    }

    public String getName() {
        return this.name;
    }

    public String getReleaseDate() {
        return this.release_date;
    }

    public String getOwner() {
        return this.owner;
    }

    public int getAge() {
        return this.age;
    }

    public float getPrice() {
        return this.price;
    }

    public int getDlcs() {
        return this.dlcs;
    }

    public String[] getLanguages() {
        return this.languages;
    }

    public String getWebsite() {
        return this.website;
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

    public float getUpvotes() {
        return this.upvotes;
    }

    public int getAvg_pt() {
        return this.avg_pt;
    }

    public String getDevelopers() {
        return this.developers;
    }

    public String[] getGenres() {
        return this.genres;
    }

    public int getDia() {
        return this.dia;
    }

    public Games2 getNext() {
        return this.next;
    }

    public boolean has_next() {
        boolean resp = true;
        if (this.next == null) {
            resp = false;
        }
        return resp;
    }

    Games2() {
        this.app_id = 0;
        this.name = null;
        this.release_date = null;
        this.owner = null;
        this.age = 0;
        this.price = 0;
        this.dlcs = 0;
        this.website = null;
        this.windows = false;
        this.mac = false;
        this.linux = false;
        this.upvotes = 0;
        this.avg_pt = 0;
        this.developers = null;
        this.dia = 0;
        this.next = null;
    }

    public Games2(Games2 elemento) {
        this.elemento = elemento;
        this.prox = null;
    }

    Games2(int app_id, String name, String release_date, String owner, int age, float price, int dlcs,
            String[] languages,
            String website, boolean windows, boolean mac, boolean linux, float upvotes, int avg_pt, String developers,
            String[] genres) {
        this.app_id = app_id;
        this.name = name;
        this.release_date = release_date;
        this.owner = owner;
        this.age = age;
        this.price = price;
        this.dlcs = dlcs;
        this.languages = languages;
        this.website = website;
        this.windows = windows;
        this.mac = mac;
        this.linux = linux;
        this.upvotes = upvotes;
        this.avg_pt = avg_pt;
        this.developers = developers;
        this.genres = genres;
        this.dia = 0;
        this.next = null;
    }

    public void filtro(String str) {
        String aux;
        String name;
        String release_date;
        String owner;
        String auxdia = "";
        String[] languages;
        String website;
        String developers;
        String[] genres;
        float price;
        float totalUpVotes;
        float totalOtherVotes;
        float upvotes;
        int age;
        int dlcs;
        int app_id;
        int avg_pt;
        int k_tmp;
        int dia;
        int k = 0;
        int cont = 0;
        boolean windows;
        boolean mac;
        boolean linux;

        // app_id
        aux = "";
        for (; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        app_id = Integer.parseInt(aux);
        this.setAppid(app_id);

        // name
        aux = "";
        if (str.charAt(k + 1) == '\"') {
            for (k += 2; str.charAt(k) != '\"'; k++) {
                aux += str.charAt(k);
            }
            name = aux;
            for (k++; str.charAt(k) != ','; k++)
                ;
        } else {
            for (k++; str.charAt(k) != ','; k++) {
                aux += str.charAt(k);
            }
            name = aux;
        }
        this.setName(name);

        // release_date
        aux = "";
        if (str.charAt(k + 1) == '\"') {
            for (k++; str.charAt(k) != ','; k++) {
                aux += str.charAt(k);
            }
            release_date = "";
            release_date += aux.charAt(1);
            release_date += aux.charAt(2);
            release_date += aux.charAt(3);
            release_date += "/";
            auxdia += aux.charAt(5);
            if (aux.length() == 7) {
                auxdia += aux.charAt(6);
            }
            dia = Integer.parseInt(auxdia);
            aux = "";
            for (k++; str.charAt(k) != ','; k++) {
                aux += str.charAt(k);
            }
            release_date += aux.charAt(1);
            release_date += aux.charAt(2);
            release_date += aux.charAt(3);
            release_date += aux.charAt(4);
        } else {
            for (k++; str.charAt(k) != ','; k++) {
                aux += str.charAt(k);
            }
            release_date = "";
            release_date += aux.charAt(0);
            release_date += aux.charAt(1);
            release_date += aux.charAt(2);
            release_date += "/";
            release_date += aux.charAt(4);
            release_date += aux.charAt(5);
            release_date += aux.charAt(6);
            release_date += aux.charAt(7);
            dia = 1;
        }
        this.setReleaseDate(release_date);
        this.setDia(dia);

        // owner
        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        owner = aux;
        this.setOwner(owner);

        // age
        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        age = Integer.parseInt(aux);
        this.setAge(age);

        // price
        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        price = Float.parseFloat(aux);
        this.setPrice(price);

        // dlcs
        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        dlcs = Integer.parseInt(aux);
        this.setDlcs(dlcs);

        // languages
        aux = "";
        k_tmp = k;
        if (str.charAt(k + 2) != ']') {
            for (k_tmp++; str.charAt(k_tmp) != ']'; k_tmp++) {
                if (str.charAt(k_tmp) == ',') {
                    cont++;
                }
            }
            cont++;
            if (str.charAt(k_tmp + 1) == '[') {
                cont = 5;
            }
            languages = new String[cont];
            for (int i = 0; i < cont; i++) {
                aux = "";
                for (k++; str.charAt(k) != '\''; k++) {
                }
                for (k++; str.charAt(k) != '\''; k++) {
                    aux += str.charAt(k);
                }
                languages[i] = "";
                for (int j = 0; j < aux.length(); j++) {
                    languages[i] += aux.charAt(j);
                }
            }
        } else {
            languages = null;
        }
        for (k++; str.charAt(k) != ','; k++)
            ;
        this.setLanguages(languages);

        // website
        if (str.charAt(k + 1) == '\"') {
            aux = "";
            for (k += 2; str.charAt(k) != '\"'; k++) {
                aux += str.charAt(k);
            }
            website = aux;
            for (k++; str.charAt(k) != ','; k++)
                ;
        } else {
            aux = "";
            for (k++; str.charAt(k) != ','; k++) {
                aux += str.charAt(k);
            }
            website = aux;
        }
        this.setWebsite(website);

        // windows
        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        windows = aux.charAt(0) != 'F';
        this.setWindows(windows);

        // mac
        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        mac = aux.charAt(0) != 'F';
        this.setMac(mac);

        // linux
        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        linux = aux.charAt(0) != 'F';
        this.setLinux(linux);

        // upvotes
        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        totalUpVotes = Float.parseFloat(aux);

        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        totalOtherVotes = Float.parseFloat(aux);
        upvotes = totalUpVotes / (totalOtherVotes + totalUpVotes);
        this.setUpvotes(upvotes);

        // avg_pt
        aux = "";
        for (k++; str.charAt(k) != ','; k++) {
            aux += str.charAt(k);
        }
        avg_pt = Integer.parseInt(aux);
        this.setAvgPt(avg_pt);

        // developers
        aux = "";
        cont = 0;
        k++;
        k_tmp = k;
        if (str.charAt(k_tmp) == '\"') {
            for (k_tmp++; str.charAt(k_tmp) != '\"'; k_tmp++) {
                if (str.charAt(k_tmp) == ',') {
                    cont++;
                }
            }
        }
        cont++;
        developers = "";
        for (int i = 0; i < cont; i++) {
            aux = "";
            for (k += (str.charAt(k_tmp) == '\"') ? 1 : 0; str.charAt(k) != ',' && str.charAt(k) != '\"'; k++) {
                aux += str.charAt(k);
            }
            developers += aux;
            if (i < cont - 1) {
                developers += ",";
            }
        }
        if (cont > 1) {
            for (k++; str.charAt(k) != ','; k++)
                ;
        }

        this.setDevelopers(developers);

        // genres
        aux = "";
        if (k + 1 < str.length()) {
            cont = 0;
            k++;
            k_tmp = k;
            if (str.charAt(k_tmp) == '\"') {
                for (k_tmp++; str.charAt(k_tmp) != '\"'; k_tmp++) {
                    if (str.charAt(k_tmp) == ',') {
                        cont++;
                    }
                }
            }
            cont++;
            genres = new String[cont];
            for (int i = 0; i < cont; i++) {
                aux = "";
                for (k += (str.charAt(k_tmp) == '\"') ? 1 : 0; k < str.length() && str.charAt(k) != ','
                        && str.charAt(k) != '\"'; k++) {
                    aux += str.charAt(k);
                }
                genres[i] = "";
                for (int j = 0; j < aux.length(); j++) {
                    genres[i] += aux.charAt(j);
                }
            }
        } else {
            genres = new String[cont];
        }
        this.setGenres(genres);

    }

    public String toString() {
        String auxLang = "[";
        String auxGenero = "[";
        String auxHours = "";
        String price_tmp = "";
        String release_date_tmp = "";
        String owner_tmp = "";
        String website_tmp = "";
        String auxName = name;
        int percentUpVotes = 0;

        if (languages != null) {
            for (int i = 0; i < languages.length; i++) {
                auxLang += languages[i];
                if (i < languages.length - 1) {
                    auxLang += ", ";
                }
            }
            auxLang += "]";
        } else {
            auxLang += "]";
        }
        percentUpVotes = Math.round(upvotes * 100);

        for (int i = 0; i < genres.length; i++) {
            auxGenero += genres[i];
            if (i < genres.length - 1) {
                auxGenero += ", ";
            } else {
                auxGenero += "]";
            }
        }

        if (name == "") {
            auxName = "null";
        }

        release_date_tmp = release_date;
        if (release_date == "") {
            release_date_tmp = "null";
        }

        owner_tmp = owner;
        if (owner == "") {
            owner_tmp = "null";
        }

        website_tmp = website;
        if (website == "") {
            website_tmp = "null";
        }
        String developers_tmp = developers;
        if (developers == "") {
            developers_tmp = "null";
        }

        price_tmp += Float.toString(price);
        if (price_tmp.charAt(price_tmp.length() - 1) == '0') {
            price_tmp += '0';
        }

        if (avg_pt != 0) {
            int avg_pt_min = avg_pt % 60;
            int avg_pt_hour = (avg_pt - avg_pt_min) / 60;
            if (avg_pt_hour > 0) {
                auxHours += avg_pt_hour + "h";
            }
            if (avg_pt_hour > 0 && avg_pt_min > 0) {
                auxHours += " ";
            }
            if (avg_pt_min > 0) {
                auxHours += avg_pt_min + "m";
            }
        } else {
            auxHours = "null";
        }

        return app_id + " " + auxName + " " + release_date_tmp + " " + owner_tmp + " " + age + " " + price_tmp + " "
                + dlcs + " " + auxLang + " " +
                website_tmp + " " + windows + " " + mac + " " + linux + " " + percentUpVotes + "% " + auxHours + " "
                + developers_tmp + " " + auxGenero;
    }
}

class NoAN {
    public boolean cor;
    public Games2 elemento;
    public NoAN esq, dir;

    public NoAN() {
        this(null);
    }

    public NoAN(Games2 elemento) {
        this(elemento, false, null, null);
    }

    public NoAN(Games2 elemento, boolean cor) {
        this(elemento, cor, null, null);
    }

    public NoAN(Games2 elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

public class TP05Q04 {
    private NoAN root; // root da arvore.

    public TP05Q04() {
        root = null;
    }

    public boolean search(Games2 elemento) {
        return search(elemento, root);
    }

    private boolean search(Games2 elemento, NoAN i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (elemento == i.elemento) {
            resp = true;
        } else if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
            System.out.print("esq ");
            resp = search(elemento, i.esq);
        } else {
            System.out.print("dir ");
            resp = search(elemento, i.dir);
        }
        return resp;
    }

    public void insert(Games2 elemento) throws Exception {
        // Se a arvore estiver vazia
        if (root == null) {
            root = new NoAN(elemento);

            // Senao, se a arvore tiver um elemento
        } else if (root.esq == null && root.dir == null) {
            if (elemento.getName().compareTo(root.elemento.getName()) < 0) {
                root.esq = new NoAN(elemento);
            } else {
                root.dir = new NoAN(elemento);
            }

            // Senao, se a arvore tiver dois elementos (root e dir)
        } else if (root.esq == null) {
            if (elemento.getName().compareTo(root.elemento.getName()) < 0) {
                root.esq = new NoAN(elemento);

            } else if (elemento.getName().compareTo(root.dir.elemento.getName()) < 0) {
                root.esq = new NoAN(root.elemento);
                root.elemento = elemento;

            } else {
                root.esq = new NoAN(root.elemento);
                root.elemento = root.dir.elemento;
                root.dir.elemento = elemento;
            }
            root.esq.cor = root.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (root e esq)
        } else if (root.dir == null) {
            if (elemento.getName().compareTo(root.elemento.getName()) > 0) {
                root.dir = new NoAN(elemento);

            } else if (elemento.getName().compareTo(root.esq.elemento.getName()) > 0) {
                root.dir = new NoAN(root.elemento);
                root.elemento = elemento;

            } else {
                root.dir = new NoAN(root.elemento);
                root.elemento = root.esq.elemento;
                root.esq.elemento = elemento;
            }
            root.esq.cor = root.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            insert(elemento, null, null, null, root);
        }
        root.cor = false;
    }

    private void insert(Games2 elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {
            if (elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }
            if (pai.cor) {
                balance(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor && i.dir.cor) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == root) {
                    i.cor = false;
                } else if (pai.cor) {
                    balance(bisavo, avo, pai, i);
                }
            }
            if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
                insert(elemento, avo, pai, i, i.esq);
            } else if (elemento.getName().compareTo(i.elemento.getName()) > 0) {
                insert(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro insert (elemento repetido)!");
            }
        }
    }

    private void balance(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor) {
            // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento.getName().compareTo(avo.elemento.getName()) > 0) { // rotacao a esquerda ou
                                                                                // direita-esquerda
                if (i.elemento.getName().compareTo(pai.elemento.getName()) > 0) {
                    avo = rotateEsq(avo);
                } else {
                    avo = rotateDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                    avo = rotateDir(avo);
                } else {
                    avo = rotateEsqDir(avo);
                }
            }
            if (bisavo == null) {
                root = avo;
            } else if (avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    private NoAN rotateDir(NoAN no) {
        // System.out.println("Rotacao DIR(" + no.elemento + ")");
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    public void walkPre() {
        System.out.print("Pre.: ");
        walkPre(root);
        System.out.println("");
    }

    private void walkPre(NoAN i) {
        if (i != null) {
            System.out.print(i.elemento.getName() + " "); // Conteudo do no.
            walkPre(i.esq); // Elementos da esquerda.
            walkPre(i.dir); // Elementos da direita.
        }
    }

    private NoAN rotateEsq(NoAN no) {
        // System.out.println("Rotacao ESQ(" + no.elemento + ")");
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotateDirEsq(NoAN no) {
        no.dir = rotateDir(no.dir);
        return rotateEsq(no);
    }

    private NoAN rotateEsqDir(NoAN no) {
        no.esq = rotateEsq(no.esq);
        return rotateDir(no);
    }

    public static Boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {

        Games2[] games = new Games2[4403];
        TP05Q04 arvore = new TP05Q04();
        int n = 0;
        int m = 0;

        Scanner sc = new Scanner(new File("/tmp/games.csv"));
        Scanner ler = new Scanner(System.in);

        // -----lendo arquivo
        while (sc.hasNext()) {
            String aux = sc.nextLine();
            games[n] = new Games2();
            games[n].filtro(aux);
            n++;
        }

        // entrada
        String id = MyIO.readLine();
        while (!isFim(id)) {
            int aux = Integer.parseInt(id);
            for (m = 0; m < games.length; m++) {
                if (Objects.equals(games[m].getAppid(), aux)) {
                    // INSERINDO OS ELEMENTOS LIDOS NA ARVORE
                    arvore.insert(games[m]);
                    break;
                }
            }
            id = MyIO.readLine();
        }

        int tamanho = MyIO.readInt();
        for (int k = 0; k < tamanho; k++) {
            String aux1 = MyIO.readLine();

            // insert
            if (aux1.charAt(0) == 'I') {
                String numero = "";
                for (int l = 2; l < aux1.length(); l++) {
                    numero = numero + aux1.charAt(l);
                }

                // COLOCANDO OS ELEMENTOS DO ID NA ARVORE
                for (m = 0; m < games.length; m++) {
                    if (games[m].getAppid() == Integer.parseInt(numero)) {
                        // INSERINDO OS ELEMENTOS NA ARVORE
                        arvore.insert(games[m]);
                        break;
                    }
                }

                // REMOVER
            } else if (aux1.charAt(0) == 'R') {
                String numero = "";
                for (int l = 2; l < aux1.length(); l++) {
                    numero = numero + aux1.charAt(l);
                }
            }
            m++;
        }
        // entrada
        String name = MyIO.readLine();
        while (!isFim(name)) {
            for (m = 0; m < games.length; m++) {
                if (Objects.equals(games[m].getName(), name)) {
                    System.out.println(games[m].getName());
                    System.out.print("root ");
                    if (arvore.search(games[m])) {
                        System.out.println("SIM");
                    } else {
                        System.out.println("NAO");
                    }
                    break;
                }
            }
            name = MyIO.readLine();
        }
    }
}
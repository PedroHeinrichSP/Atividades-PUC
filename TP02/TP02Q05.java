import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

class TP02Q05 {
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    static class GameList {
        private Game[] array;
        private int n;

        public GameList() {
            this(10);
        }

        public GameList(int tamanho) {
            array = new Game[tamanho];
            n = 0;
        }

        public void inserirInicio(Game x) throws Exception {
            if (n >= array.length) {
                throw new Exception("Erro ao inserir!");
            }
            for (int i = n; i > 0; i--) {
                array[i] = array[i - 1].clone();
            }
            array[0] = x;
            n++;
        }

        public void inserirFim(Game x) throws Exception {
            if (n >= array.length) {
                throw new Exception("Erro ao inserir!");
            }
            array[n] = x;
            n++;
        }

        public void inserir(Game x, int pos) throws Exception {
            System.out.println(pos > 0);
            if (n >= array.length || pos < 0 || pos > n) {
                throw new Exception("Erro ao inserir!");
            }
            for (int i = n; i > pos; i--) {
                array[i] = array[i - 1].clone();
            }
            array[pos] = x;
            n++;
        }

        public Game removerInicio() throws Exception {
            if (n == 0) {
                throw new Exception("Erro ao remover!");
            }
            Game resp = array[0].clone();
            n--;
            for (int i = 0; i < n; i++) {
                array[i] = array[i + 1].clone();
            }
            return resp;
        }

        public Game removerFim() throws Exception {
            if (n == 0) {
                throw new Exception("Erro ao remover!");
            }
            return array[--n];
        }

        public Game remover(int pos) throws Exception {
            if (n == 0 || pos < 0 || pos >= n) {
                throw new Exception("Erro ao remover!");
            }
            Game resp = array[pos];
            n--;
            for (int i = pos; i < n; i++) {
                array[i] = array[i + 1];
            }
            return resp;
        }

        public void mostrar() {
            for (int i = 0; i < n; i++) {
                System.out.print("[" + i + "] ");
                System.out.print(array[i]);
            }
        }
    }

    static class Game {

        // Atributos
        private int app_id;
        private String name;
        private Date release_date;
        private String owners;
        private int age;
        private float price;
        private int dlcs;
        private ArrayList<String> languages;
        private String website;
        private boolean windowsOS;
        private boolean macOS;
        private boolean linuxOS;
        private float upvotes;
        private int avg_pt;
        private String developers;
        private ArrayList<String> genres;

        // Construtores
        public Game() {
            this.name = this.owners = this.website = this.developers = null;
            this.languages = new ArrayList<String>();
            this.genres = new ArrayList<String>();
            this.release_date = null;
            this.app_id = this.age = this.dlcs = this.avg_pt = -1;
            this.price = this.upvotes = -1;
            this.windowsOS = this.macOS = this.linuxOS = false;
        }

        public Game(String name, String owners, String website, String developers, ArrayList<String> languages,
                ArrayList<String> genres, Date release_date, int app_id, int age, int dlcs, int upvotes,
                int avg_pt, float price, boolean windowsOS, boolean macOS, boolean linuxOS) {
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
            this.avg_pt = avg_pt;
            this.price = price;
            this.windowsOS = windowsOS;
            this.macOS = macOS;
            this.linuxOS = linuxOS;
        }

        // Getters
        public int getApp_id() {
            return app_id;
        }

        public String getName() {
            return name;
        }

        public Date getRelease_date() {
            return release_date;
        }

        public String getOwners() {
            return owners;
        }

        public int getAge() {
            return age;
        }

        public float getPrice() {
            return price;
        }

        public int getDlcs() {
            return dlcs;
        }

        public ArrayList<String> getLanguages() {
            return languages;
        }

        public String getWebsite() {
            return website;
        }

        public boolean getWindowsOS() {
            return windowsOS;
        }

        public boolean getMacOS() {
            return macOS;
        }

        public boolean getLinuxOS() {
            return linuxOS;
        }

        public float getUpvotes() {
            return upvotes;
        }

        public int getAvg_pt() {
            return avg_pt;
        }

        public String getDevelopers() {
            return developers;
        }

        public ArrayList<String> getGenres() {
            return genres;
        }

        // Setters
        public void setApp_id(int app_id) {
            this.app_id = app_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRelease_date(Date release_date) {
            this.release_date = release_date;
        }

        public void setOwners(String owners) {
            this.owners = owners;
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

        public void setLanguages(ArrayList<String> languages) {
            this.languages = languages;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public void setWindowsOS(boolean windowsOS) {
            this.windowsOS = windowsOS;
        }

        public void setMacOS(boolean macOS) {
            this.macOS = macOS;
        }

        public void setLinuxOS(boolean linuxOS) {
            this.linuxOS = linuxOS;
        }

        public void setUpvotes(float upvotes) {
            this.upvotes = upvotes;
        }

        public void setAvg_pt(int avg_pt) {
            this.avg_pt = avg_pt;
        }

        public void setDevelopers(String developers) {
            this.developers = developers;
        }

        public void setGenres(ArrayList<String> genres) {
            this.genres = genres;
        }

        // Cloner
        public Game clone() {
            Game retornoGame = new Game();
            retornoGame.app_id = this.app_id;
            retornoGame.name = this.name;
            retornoGame.release_date = this.release_date;
            retornoGame.owners = this.owners;
            retornoGame.age = this.age;
            retornoGame.price = this.price;
            retornoGame.dlcs = this.dlcs;
            retornoGame.languages = this.languages;
            retornoGame.website = this.website;
            retornoGame.windowsOS = this.windowsOS;
            retornoGame.macOS = this.macOS;
            retornoGame.linuxOS = this.linuxOS;
            retornoGame.upvotes = this.upvotes;
            retornoGame.avg_pt = this.avg_pt;
            retornoGame.developers = this.developers;
            retornoGame.genres = this.genres;
            return retornoGame;
        }

        // Leitura
        public void readID(String entry) throws Exception {
            // Split dos dados
            /*
             * EXEMPLO
             * 0- 427520,Factorio,
             * 1- Aug 14, 2020
             * 2- ,5000000 - 10000000,0,30.0,1,
             * 3- ['English', 'French', 'Italian', 'German', 'Spanish - Spain', 'Hungarian',
             * 'Dutch', 'Norwegian', 'Polish', 'Portuguese', 'Portuguese - Brazil',
             * 'Romanian', 'Finnish', 'Swedish', 'Czech', 'Russian', 'Ukrainian',
             * 'Japanese', 'Simplified Chinese', 'Traditional Chinese', 'Korean', 'Turkish']
             * 4- ,https://www.factorio.com,True,True,True,145186,5216,5383,
             * 5- Wube Software LTD.
             * 6- ,
             * 7- Casual,Indie,Simulation,Strategy
             */
            String[] dataEntry = entry.split("\"");

            // Split e Set da linha 0 em ID e Nome
            /*
             * EXEMPLO - 427520,Factorio,
             * 0-- 427520
             * 1-- Factorio
             */
            String[] data = dataEntry[0].split(",");
            setApp_id(Integer.parseInt(data[0]));
            // System.out.println(getApp_id());
        }

        public void readEntry(String entry) throws Exception {
            /*
             * EXEMPLO
             * 0- 730
             * 1- Counter-Strike: Global Offensive
             * 2- "Aug 21
             * 3- 2012"
             * 4- 50000000 - 100000000
             * 5- 0,0.0,
             * 1,"['Czech', 'Danish', 'Dutch', 'English', 'Finnish', 'French', 'German', 'Hungarian', 'Italian', 'Japanese', 'Korean', 'Norwegian', 'Polish', 'Portuguese', 'Portuguese - Brazil', 'Romanian', 'Russian', 'Simplified Chinese', 'Spanish - Spain', 'Swedish', 'Thai', 'Traditional Chinese', 'Turkish', 'Bulgarian', 'Ukrainian', 'Greek', 'Spanish - Latin America', 'Vietnamese']"
             * ,http://blog.counter-strike.net/,True,True,True,5764420,766677,
             * 30484,"Valve,Hidden Path Entertainment","Action,Free to Play"
             */
            String[] dataEntry = entry.split(",");
            // Set do nome
            setApp_id(Integer.parseInt(dataEntry[0]));

            setName(dataEntry[1]);
            // Set da linha 1 em Data(Date)
            dataEntry = entry.split("\"");
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                setRelease_date(dateFormat.parse(dataEntry[1]));
            } catch (Exception e) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                    setRelease_date(dateFormat.parse(dataEntry[3]));
                } catch (Exception a) {
                    dataEntry = entry.split(",");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy", Locale.US);
                    setRelease_date(dateFormat.parse(dataEntry[2]));
                }
            }
            try {
                dataEntry = entry.split(",");
                setOwners(dataEntry[4]);
            } catch (Exception e) {

            }
            // setOwners();
            /*
             * private int age;
             * private float price;
             * private int dlcs;
             * private String[] languages;
             * private String website;
             * private boolean windowsOS;
             * private boolean macOS;
             * private boolean linuxOS;
             * private float upvotes;
             * private int avg_pt;
             * private String developers;
             * private String[] genres;
             */
        }

        // Leitura retirada do código do Pedro Costa recomendado pelo professor
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
                    this.windowsOS = Boolean.parseBoolean(line.substring(atr_index, index));
                    atr_index = ++index;
                    break;
                }
            }

            // Find "Mac"
            while (true) {
                index++;
                if (line.charAt(index) == ',') {
                    this.macOS = Boolean.parseBoolean(line.substring(atr_index, index));
                    atr_index = ++index;
                    break;
                }
            }

            // Find "Linux"
            while (true) {
                index++;
                if (line.charAt(index) == ',') {
                    this.linuxOS = Boolean.parseBoolean(line.substring(atr_index, index));
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
                    this.avg_pt = Integer.parseInt(line.substring(atr_index, index));
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

        // Imprimir
        private String getFormatDate() {
            StringBuilder resp = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM/yyyy", Locale.US);
            resp.append(dateFormat.format(release_date));
            return resp.toString();
        }

        private String getFormatLanguages() {
            StringBuilder resp = new StringBuilder();
            resp.append('[');
            if (languages.size() == 0) {
                resp.append(']');
                return resp.toString();
            }
            int i = 0;
            for (i = 0; i < (languages.size() - 1); i++) {
                resp.append(languages.get(i) + ", ");
            }
            resp.append(languages.get(i) + ']');
            return resp.toString();
        }

        private String getFormatGenres() {
            StringBuilder resp = new StringBuilder();
            resp.append('[');
            if (genres.size() == 0) {
                resp.append(']');
                return resp.toString();
            }
            int i = 0;
            for (i = 0; i < (genres.size() - 1); i++) {
                resp.append(genres.get(i) + ", ");
            }
            resp.append(genres.get(i) + ']');
            return resp.toString();
        }

        private String getRoundedUpvote() {
            float nota = getUpvotes();
            StringBuilder resp = new StringBuilder();
            resp.append((int) Math.ceil(nota));
            resp.append('%');
            return resp.toString();
        }

        private String getFormatAvgPT() {
            StringBuilder resp = new StringBuilder();
            double minutes = this.getAvg_pt();
            int horas = (int) minutes / 60;
            resp.append(horas + "h ");
            int minutos = (int) (((minutes / 60) - horas) * 60);
            resp.append(minutos + "m");
            return resp.toString();
        }

        public void Imprimir() {
            System.out.println(app_id + " " + name + " " + getFormatDate() + " " + owners + " " + age + " " + price
                    + " " + dlcs + " " + getFormatLanguages() + " " + website + " " + windowsOS + " " + macOS + " "
                    + linuxOS + " " + getRoundedUpvote() + " " + getFormatAvgPT() + " " + developers + " "
                    + getFormatGenres());
        }

    }

    public static void main(String Args[]) throws Exception {
        GameList listaJogos = new GameList();
        String[] listaIds = new String[200];
        String aux = new String();
        Scanner csv = new Scanner(new File("games.csv"));
        Scanner teclado = new Scanner(System.in);
        int idCont = -1;
        do {
            idCont++;
            listaIds[idCont] = teclado.nextLine();
        } while (!isFim(listaIds[idCont]));
        int csvCont = 0;
        while (csvCont < idCont) {
            aux = csv.nextLine();
            gameArray[csvCont] = new Game();
            gameArray[csvCont].readID(aux);
            for (int i = 0; i < idCont; i++) {
                if (gameArray[csvCont].getApp_id() == Integer.parseInt(listaIds[i])) {
                    gameArray[csvCont].readEntry(aux);
                    csvCont++;
                    i = idCont;
                }
            }
        }
        // mexer com lista
        do {
                Game auxiliar = new Game();
                aux = teclado.nextLine();
                String[] auxStrings = aux.split("\\s+");
                    if (auxStrings[0].compareTo("II")==0) {
                        if (auxStrings.length == 2){
                            for (int i = 0; i < csvCont; i++) {
                                if (gameArray[i].getApp_id() == Integer.parseInt(auxStrings[1])) {
                                    auxiliar = gameArray[i].clone();
                                    i = csvCont;
                                }
                            }
                        } else if (auxStrings.length==3){
                            for (int i = 0; i < csvCont; i++) {
                                if (gameArray[i].getApp_id() == Integer.parseInt(auxStrings[2])) {
                                    auxiliar = gameArray[i].clone();
                                    i = csvCont;
                                }
                            }
                        }
                        listaJogos.inserirInicio(auxiliar);
                    } else if (auxStrings[0].compareTo("I*")==0) {
                        if (auxStrings.length == 2){
                            for (int i = 0; i < csvCont; i++) {
                                if (gameArray[i].getApp_id() == Integer.parseInt(auxStrings[1])) {
                                    auxiliar = gameArray[i].clone();
                                    i = csvCont;
                                }
                            }
                        } else if (auxStrings.length==3){
                            for (int i = 0; i < csvCont; i++) {
                                if (gameArray[i].getApp_id() == Integer.parseInt(auxStrings[2])) {
                                    auxiliar = gameArray[i].clone();
                                    i = csvCont;
                                }
                            }
                        }
                        listaJogos.inserir(auxiliar, Integer.parseInt(auxStrings[1]));
                    } else if (auxStrings[0].compareTo("IF")==0) {
                        if (auxStrings.length == 2){
                            for (int i = 0; i < csvCont; i++) {
                                if (gameArray[i].getApp_id() == Integer.parseInt(auxStrings[1])) {
                                    auxiliar = gameArray[i].clone();
                                    i = csvCont;
                                }
                            }
                        } else if (auxStrings.length==3){
                            for (int i = 0; i < csvCont; i++) {
                                if (gameArray[i].getApp_id() == Integer.parseInt(auxStrings[2])) {
                                    auxiliar = gameArray[i].clone();
                                    i = csvCont;
                                }
                            }
                        }
                        listaJogos.inserirFim(auxiliar);
                    } else if (auxStrings[0].compareTo("RI")==0) {
                        Game removido = listaJogos.removerInicio();
                        System.out.println("(R)" + removido.getName());
                    } else if (auxStrings[0].compareTo("R*")==0) {
                        Game removido = listaJogos.remover(Integer.parseInt(auxStrings[1]));
                        System.out.println("(R)" + removido.getName());
                    } else if (auxStrings[0].compareTo("RF")==0) {
                        Game removido = listaJogos.removerFim();
                        System.out.println("(R)" + removido.getName());
                    }
            } while(teclado.hasNext());
        listaJogos.mostrar();
        teclado.close();
        csv.close();
}
}
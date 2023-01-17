import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.*;

class TP02Q04 {
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    public static String existe(String nome, Game[] listaJogo, int jogoTam){
        String resp = "NAO";
        for (int i=0; i<jogoTam; i++){
            if(nome.compareTo(listaJogo[i].getName())==0)
                resp = "SIM";
        }
        return resp;
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
        private String[] languages;
        private String website;
        private boolean windowsOS;
        private boolean macOS;
        private boolean linuxOS;
        private float upvotes;
        private int avg_pt;
        private String developers;
        private String[] genres;

        // Construtores
        public Game() {
            app_id = 0;
            name = "";
            release_date = new Date();
            owners = "";
            age = 0;
            price = 0;
            dlcs = 0;
            languages = new String[] { "" };
            website = "";
            windowsOS = false;
            macOS = false;
            linuxOS = false;
            upvotes = 0;
            avg_pt = 0;
            developers = "0";
            genres = new String[] { "" };
        }

        public Game(int app_id, String name, Date release_date, String players, String owners, int age, float price,
                int dlcs, String[] languages, String website, boolean windowsOS, boolean macOS, boolean linuxOS,
                float upvotes, int avg_pt, String developers, String[] genres) {
            this.app_id = app_id;
            this.name = name;
            this.release_date = release_date;
            this.owners = owners;
            this.age = age;
            this.price = price;
            this.dlcs = dlcs;
            this.languages = languages;
            this.website = website;
            this.windowsOS = windowsOS;
            this.macOS = macOS;
            this.linuxOS = linuxOS;
            this.upvotes = upvotes;
            this.avg_pt = avg_pt;
            this.developers = developers;
            this.genres = genres;
        }

        // Getters
        public int getApp_id() {
            return app_id;
        }
        public String getStringId() {
            return Integer.toString(app_id);
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

        public String[] getLanguages() {
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

        public String[] getGenres() {
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

        public void setLanguages(String[] languages) {
            this.languages = languages;
        }

        public void setLanguages(String languages) {
            this.languages[0] = languages;
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

        public void setGenres(String[] genres) {
            this.genres = genres;
        }

        // Cloner *SUJEITO A MUDANÇA AINDA NAO TA PRONTO*
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
        }

        public void readEntry(String entry) throws Exception {
            /*
             * EXEMPLO
             * 0- 730
             * 1- Counter-Strike: Global Offensive
             * 2- "Aug 21
             * 3-  2012"
             * 4- 50000000 - 100000000
             * 5- 0,0.0,1,"['Czech', 'Danish', 'Dutch', 'English', 'Finnish', 'French', 'German', 'Hungarian', 'Italian', 'Japanese', 'Korean', 'Norwegian', 'Polish', 'Portuguese', 'Portuguese - Brazil', 'Romanian', 'Russian', 'Simplified Chinese', 'Spanish - Spain', 'Swedish', 'Thai', 'Traditional Chinese', 'Turkish', 'Bulgarian', 'Ukrainian', 'Greek', 'Spanish - Latin America', 'Vietnamese']",http://blog.counter-strike.net/,True,True,True,5764420,766677,30484,"Valve,Hidden Path Entertainment","Action,Free to Play"
             */
            String[] dataEntry = entry.split(",");
            // Set do nome
            setApp_id(Integer.parseInt(dataEntry[0]));
            
            setName(dataEntry[1]);
            // Set da linha 1 em Data(Date)
            dataEntry = entry.split("\"");  
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
            int i = 0;
            for (i = 0; i < (languages.length - 1); i++) {
                resp.append(languages[i] + ", ");
            }
            resp.append(languages[i] + ']');
            return resp.toString();
        }

        private String getFormatGenres() {
            StringBuilder resp = new StringBuilder();
            resp.append('[');
            if(genres.length==0){ return resp.toString();}
            int i = 0;
            for (i = 0; i < (genres.length - 1); i++) {
                resp.append(genres[i] + ", ");
            }
            resp.append(genres[i] + ']');
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
        TP02Q03.Game[] gamelist = new Game[100];
        String[] listaIds = new String[5000];
        String[] listaNomes = new String[5000];
        String aux = new String();
        Scanner csv = new Scanner(new File("/tmp/games.csv"));
        RandomAccessFile pesquisa = new RandomAccessFile("781664_sequencial.txt", "rw");
        Scanner teclado = new Scanner(System.in);
        int comparacoes=0;
        long startTime = System.nanoTime();
        //Leitura dos Ids de entrada
        int idCont = -1;
        do {
            idCont++;
            listaIds[idCont] = teclado.nextLine();
        } while (!isFim(listaIds[idCont]));
        //Leitura do Id e Nome do CSV
        int csvCont = 0;
        //Ler a linha do csv, tirar o id, se o id for igual a algum dos ids em listaIds, escrever o resto, se não pular pra próxima linha
        while (csvCont<idCont) {
            aux = csv.nextLine();
            gamelist[csvCont] = new Game();
            gamelist[csvCont].readID(aux);
            for(int i=0; i<idCont; i++){
                comparacoes++;
                if( gamelist[csvCont].getApp_id() == Integer.parseInt(listaIds[i]) ){
                    gamelist[csvCont].readEntry(aux);
                    csvCont++;
                    i=idCont;
                }
            }
        }

        //Leitura dos Nomes a serem comparados
        int cont = -1;
        do{
            cont++;
            listaNomes[cont] = teclado.nextLine();
        } while(!isFim(listaNomes[cont]));

        //Pesquisa
        //Comparar o nome de listaNomes com a lista de csv
        for(int i=0;i<cont; i++){
            comparacoes = comparacoes + csvCont;
            System.out.println(existe(listaNomes[i], gamelist, csvCont));
        }
        long elapsedTime = System.nanoTime() - startTime;
        pesquisa.writeChars("Matricula: 781664   Número de comparações: " + comparacoes + "   Tempo: "+ elapsedTime/1000000000 +" segundos");
        teclado.close();
        csv.close();
    }
}
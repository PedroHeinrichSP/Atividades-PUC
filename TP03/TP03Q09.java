/**
 
 * @file Game.java
 * @author Pedro Lopes
 * @version 0.2
 * @date 2022-10-02
 * @copyright Copyright (c) 2022
 
**/

// ----------------------------------------------------------------------------------------------------------------- //

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

// ----------------------------------------------------------------------------------------------------------------- //
class TP03Q09 {

    static class GameLista {
        int comparacoes = 0;
        int movimenta = 0;
        private Game[] array;
        private int n;

        /**
         * Construtor da classe.
         */
        public GameLista() {
            this(4500);
        }

        /**
         * Construtor da classe.
         * 
         * @param tamanho Tamanho da lista.
         */
        public GameLista(int tamanho) {
            array = new Game[tamanho];
            n = 0;
        }

        /**
         * Insere um elemento na primeira posicao da lista e move os demais
         * elementos para o fim da lista.
         * 
         * @param x Game elemento a ser inserido.
         * @throws Exception Se a lista estiver cheia.
         */
        public void inserirInicio(Game x) throws Exception {

            // validar insercao
            if (n >= array.length) {
                throw new Exception("Erro ao inserir!");
            }

            // levar elementos para o fim do array
            for (int i = n; i > 0; i--) {
                array[i] = array[i - 1].clone();
            }

            array[0] = x.clone();
            n++;
        }

        /**
         * Insere um elemento na ultima posicao da lista.
         * 
         * @param x Game elemento a ser inserido.
         * @throws Exception Se a lista estiver cheia.
         */
        public void inserirFim(Game x) throws Exception {

            // validar insercao
            if (n >= array.length) {
                throw new Exception("Erro ao inserir!");
            }

            array[n] = x.clone();
            n++;
        }

        /**
         * Insere um elemento em uma posicao especifica e move os demais
         * elementos para o fim da lista.
         * 
         * @param x   Game elemento a ser inserido.
         * @param pos Posicao de insercao (int).
         * @throws Exception Se a lista estiver cheia ou a posicao invalida.
         */
        public void inserir(Game x, int pos) throws Exception {

            // validar insercao
            if (n >= array.length || pos < 0 || pos > n) {
                throw new Exception("Erro ao inserir!");
            }

            // levar elementos para o fim do array
            for (int i = n; i > pos; i--) {
                array[i] = array[i - 1].clone();
            }

            array[pos] = x.clone();
            n++;
        }

        /**
         * Remove um elemento da primeira posicao da lista e movimenta
         * os demais elementos para o inicio da mesma.
         * 
         * @return resp Game elemento a ser removido.
         * @throws Exception Se a lista estiver vazia.
         */
        public Game removerInicio() throws Exception {

            // validar remocao
            if (n == 0) {
                throw new Exception("Erro ao remover!");
            }

            Game resp = array[0];
            n--;

            for (int i = 0; i < n; i++) {
                array[i] = array[i + 1].clone();
            }

            return resp;
        }

        /**
         * Remove um elemento da ultima posicao da lista.
         * 
         * @return resp Game elemento a ser removido.
         * @throws Exception Se a lista estiver vazia.
         */
        public Game removerFim() throws Exception {

            // validar remocao
            if (n == 0) {
                throw new Exception("Erro ao remover!");
            }

            return array[--n];
        }

        /**
         * Remove um elemento de uma posicao especifica da lista e
         * movimenta os demais elementos para o inicio da mesma.
         * 
         * @param pos Posicao de remocao (int).
         * @return resp Game elemento a ser removido.
         * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
         */
        public Game remover(int pos) throws Exception {

            // validar remocao
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

        /**
         * Mostra os elementos da lista separados por espacos.
         */
        public void mostrar() {
            for (int i = 0; i < n; i++) {
                array[i].print();
            }
        }

        /**
         * Troca os elementos para ordenação
         */
        public void swap(int i, int j) {
            Game temp = array[i].clone();
            array[i] = array[j].clone();
            array[j] = temp;
        }

        /**
         * Usando o algoritmo de ordenação por mergesort ordena os jogos com a chave de
         * pesquisa sendo os upvotes
         * 
         * @throws FileNotFoundException
         */

        public void ordenar() throws FileNotFoundException {
            RandomAccessFile pesquisa = new RandomAccessFile("781664_mergesort.txt", "rw");
            long startTime = System.nanoTime();
            // ----------------------------------------------------------------------------------------------------//
            mergesort(0, n-1);
            // ---------------------------------------------------------------------------------------------------//
            long elapsedTime = System.nanoTime() - startTime;
            try {
                pesquisa.writeChars(
                        "Matricula: 781664   Número de comparações: " + comparacoes + "   Número de movimentações: "
                                + movimenta + "   Tempo: " + elapsedTime / 1000000000 + " segundos");
                pesquisa.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void mergesort(int esq, int dir) {
            if (esq < dir){
                int meio = (esq + dir) / 2;
                mergesort(esq, meio);
                mergesort(meio + 1, dir);
                intercalar(esq, meio, dir);
            }
        }

        public void intercalar(int esq, int meio, int dir){
            int n1, n2, i, j, k;

            //Definir tamanho dos dois subarrays
            n1 = meio-esq+1;
            n2 = dir - meio;

            Game[] a1 = new Game[n1+1];
            Game[] a2 = new Game[n2+1];

            //Inicializar primeiro subarray
            for(i = 0; i < n1; i++){
                a1[i] = array[esq+i].clone();
            }

            //Inicializar segundo subarray
            for(j = 0; j < n2; j++){
                a2[j] = array[meio+j+1].clone();
            }

            //Sentinela no final dos dois arrays
            a1[i] = a2[j] = new Game(Float.MAX_VALUE);

            //Intercalacao propriamente dita
            for(i = j = 0, k = esq; k <= dir; k++){
                array[k] = (a1[i].getUpvotes() <= a2[j].getUpvotes()) ? a1[i++] : a2[j++];
                comparacoes++;
                movimenta++;
            }
        }
    }

    static class Game {

        static SimpleDateFormat default_dateFormat = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);

        private String name, owners, website, developers;
        private ArrayList<String> languages, genres;
        private Date release_date;
        private int app_id, age, dlcs, avg_playtime;
        private float price, upvotes;
        private boolean windows, mac, linux;

        public Game() {

            this.name = this.owners = this.website = this.developers = null;
            this.languages = new ArrayList<String>();
            this.genres = new ArrayList<String>();
            this.release_date = null;
            this.app_id = this.age = this.dlcs = this.avg_playtime = -1;
            this.price = this.upvotes = -1;
            this.windows = this.mac = this.linux = false;
        }

        public Game(String name, String owners, String website, String developers, ArrayList<String> languages,
                ArrayList<String> genres, Date release_date, int app_id, int age, int dlcs, int upvotes,
                int avg_playtime, float price, boolean windows, boolean mac, boolean linux) {

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
        }

        public Game(float upvotes) {
            this.upvotes = upvotes;
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

            return cloned;
        }

        public void read(String line) {

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

            // --------------------------------------------------------------------------------
            // //
        }

        public void print() {

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

            System.out.println(this.app_id + " " + this.name + " " + default_dateFormat.format(this.release_date) + " "
                    + this.owners + " " + this.age + " " + String.format(Locale.ENGLISH, "%.2f", this.price) + " "
                    + this.dlcs + " " + this.languages + " " + this.website + " " + this.windows + " " + this.mac + " "
                    + this.linux + " " + (Float.isNaN(this.upvotes) ? "0% " : df.format(this.upvotes) + "% ") + avg_pt
                    + this.developers + " " + this.genres);
        }

    }

    public static boolean isFim(String line) {
        return line.compareTo("FIM") == 0;
    }

    public static void main(String[] args) throws Exception {

        ArrayList<Game> games = new ArrayList<Game>();
        GameLista list = new GameLista(77);

        try {

            // Read CSV file
            String basefile = "/tmp/games.csv";

            FileInputStream fstream = new FileInputStream(basefile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            // ------------------------------------ //

            // Explode CSV file reading games
            String line;

            while ((line = br.readLine()) != null) {

                Game game = new Game();

                game.read(line);
                games.add(game);
            }

            // Close CSV file
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ----------------------------------------------------------------------------------------------
        // //

        // Read .in file
        Scanner scr = new Scanner(System.in);
        String line = scr.nextLine();

        while (true) {

            if (isFim(line))
                break;

            int app_id = Integer.parseInt(line);

            // Search game with .in id
            for (Game game : games) {
                if (game.getAppId() == app_id)
                    list.inserirInicio(game);
            }

            line = scr.nextLine();
        }
        list.ordenar();
        list.mostrar();
        // ----------------------------------------------------------------------------------------------
        // //

        scr.close();
    }
}
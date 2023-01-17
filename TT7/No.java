class No{
    /*----------------------------------------ATRIBUTOS-----------------------------------------------*/
    public char letra;
    public No esq, dir;
    public Celula primeiro, ultimo;
    /*----------------------------------------CONSTRUTORES--------------------------------------------*/
    public No (char letra){
        this.letra = letra;
        this.esq = this.dir = null;
		primeiro = ultimo = new Celula();
    }
    /*----------------------------------------GETTER/SETTER-------------------------------------------*/
    public No getDir() {
        return dir;
    }
    public No getEsq() {
        return esq;
    }
    public char getLetra() {
        return letra;
    }
    public Celula getPrimeiro() {
        return primeiro;
    }
    public Celula getUltimo() {
        return ultimo;
    }
    public void setDir(No dir) {
        this.dir = dir;
    }
    public void setEsq(No esq) {
        this.esq = esq;
    }
    public void setLetra(char letra) {
        this.letra = letra;
    }
    public void setPrimeiro(Celula primeiro) {
        this.primeiro = primeiro;
    }
    public void setUltimo(Celula ultimo) {
        this.ultimo = ultimo;
    }
    /*-------------------------------------------------------------------------------------------------*/
}
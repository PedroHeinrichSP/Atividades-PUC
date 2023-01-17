class Celula{
    /*----------------------------------------ATRIBUTOS-----------------------------------------------*/
    public Contato contato;
    public Celula prox;
    /*----------------------------------------CONSTRUTORES--------------------------------------------*/
    public Celula (){
        this(null, null);
    }

    public Celula(Contato contato){
        this(contato, null);
    }

    public Celula(Contato contato, Celula prox){
        this.contato = contato;
        this.prox = prox;
    }

    /*----------------------------------------GETTER/SETTER-------------------------------------------*/
    public Contato getContato() {
        return contato;
    }
    public Celula getProx() {
        return prox;
    }
    public void setContato(Contato contato) {
        this.contato = contato;
    }
    public void setProx(Celula prox) {
        this.prox = prox;
    }
    /*-------------------------------------------------------------------------------------------------*/
}
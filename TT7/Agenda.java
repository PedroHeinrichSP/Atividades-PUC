class Agenda {
    /*------------------------------------------ATRIBUTOS---------------------------------------------*/
    private No raiz;

    /*----------------------------------------CONSTRUTORES--------------------------------------------*/
    // Usando um vizualizador de árvores
    // (https://www.cs.usfca.edu/~galles/visualization/RedBlack.html) e inserindo na
    // ordem indicada no canvas
    // foi montado a ordem abaixo
    public Agenda(char letra) {
        raiz = new No('J');
        raiz.esq = new No('F');
        raiz.esq.esq = new No('B');
        raiz.esq.esq.esq = new No('A');
        raiz.esq.esq.dir = new No('D');
        raiz.esq.esq.dir.esq = new No('C');
        raiz.esq.esq.dir.dir = new No('E');
        raiz.esq.dir = new No('H');
        raiz.esq.dir.esq = new No('G');
        raiz.esq.dir.dir = new No('I');
        raiz.dir = new No('O');
        raiz.dir.esq = new No('M');
        raiz.dir.esq.esq = new No('K');
        raiz.dir.esq.esq.dir = new No('L');
        raiz.dir.esq.dir = new No('N');
        raiz.dir.dir = new No('T');
        raiz.dir.dir.esq = new No('Q');
        raiz.dir.dir.esq.esq = new No('P');
        raiz.dir.dir.esq.dir = new No('R');
        raiz.dir.dir.esq.dir.dir = new No('S');
        raiz.dir.dir.dir = new No('V');
        raiz.dir.dir.dir.esq = new No('U');
        raiz.dir.dir.dir.dir = new No('X');
        raiz.dir.dir.dir.dir.esq = new No('W');
        raiz.dir.dir.dir.dir.dir = new No('Y');
        raiz.dir.dir.dir.dir.dir.dir = new No('Z');
    }

    /*----------------------------------------GETTER/SETTER-------------------------------------------*/
    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    /*-------------------------------------------METODOS----------------------------------------------*/
    // 4.3) Método boolean inserir(Contato contato);
    public void inserir(Contato contato) throws Exception {
        if (Character.isLetter(contato.nome.charAt(0))) {
            raiz = inserir(raiz, contato);
        } else {
            throw new Exception("Erro ao inserir!");
        }
    }

    private No inserir(No no, Contato contato) throws Exception {
        if (no == null) {
            no = new No(Character.toUpperCase(contato.nome.charAt(0)));
            no.ultimo.prox = new Celula(contato);
            no.ultimo = no.ultimo.prox;
        } else if (Character.toUpperCase(contato.nome.charAt(0)) == no.letra) {
            no.ultimo.prox = new Celula(contato);
            no.ultimo = no.ultimo.prox;
        } else if (Character.toUpperCase(contato.nome.charAt(0)) < no.letra) {
            no.esq = inserir(no.esq, contato);
        } else {
            no.dir = inserir(no.dir, contato);
        }
        return no;
    }

    // 4.4) Método boolean remover(String nome);
    public Contato remover(String nome) {
        return remover(raiz, nome);
    }

    private Contato remover(No no, String nome){
        Contato resp;
        if (Character.toUpperCase(nome.charAt(0)) == no.letra) { 
            for(Celula i = no.primeiro.prox; ; i = i.prox){
                if(i.contato.nome.equals(nome) == true){
                    resp = no.primeiro.contato;
                }
            }
        }
        return resp;
    }

    // 4.5) Método boolean pesquisar(String nome);
    public boolean pesquisarNome(String nome) {
        return pesquisarNome(raiz, nome);
    }

    private boolean pesquisarNome(No no, String nome) {
        boolean resp;
        if (no == null) {
            resp = false;
        } else if (Character.toUpperCase(nome.charAt(0)) == no.letra) {
            resp = false;
            for (Celula i = no.primeiro.prox; (!resp && i != null); i = i.prox) {
                if (i.contato.nome.equals(nome) == true) {
                    resp = true;
                }
            }
        } else if (Character.toUpperCase(nome.charAt(0)) < no.letra) {
            resp = pesquisarNome(no.esq, nome);

        } else {
            resp = pesquisarNome(no.dir, nome);
        }
        return resp;
    }

    // 4.6) Método boolean pesquisar(int cpf)
    public boolean pesquisarCPF(int cpf) {
        return pesquisarCPF(raiz, cpf);
    }

    private boolean pesquisarCPF(No no, int cpf) {
        boolean resp = false;
        if (no != null) {
            resp = pesquisarCPF(no.primeiro.prox, cpf);
            if (resp == false) {
                resp = pesquisarCPF(no.esq, cpf);
                if (resp == false) {
                    resp = pesquisarCPF(no.dir, cpf);
                }
            }
        }
        return resp;
    }

    private boolean pesquisarCPF(Celula i, int cpf) {
        boolean resp = false;
        if (i != null) {
            resp = i.contato.cpf == cpf;
            if (resp == false) {
                resp = pesquisarCPF(i.prox, cpf);
            }
        }
        return resp;
    }
}
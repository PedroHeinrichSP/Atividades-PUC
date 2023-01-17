class Contato{
    /*----------------------------------------ATRIBUTOS-----------------------------------------------*/
    public String nome;
    public String telefone;
    public String email;
    public int cpf;
    /*----------------------------------------CONSTRUTORES--------------------------------------------*/
    public Contato (){
        this("nome", "telefone", "email", 0);
    }

    public Contato(String nome, String telefone, String email, int cpf){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }
    /*----------------------------------------GETTER/SETTER-------------------------------------------*/
    public int getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    /*-------------------------------------------------------------------------------------------------*/
}
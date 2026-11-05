public class Posto {
    //Atributos
    private String nome;
    private String estado;
    private String senha;
    private boolean ativo;

    //Construtor
    public Posto(String nome) {
        this.nome = nome;
        this.estado = "Livre";
        this.ativo = true;
    }

    //Getters e Setters
    public String getNome() {return nome;}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public String getEstado() {return estado;}

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    //To string
    public String toString(){
        return nome + " " + estado;
    }

}

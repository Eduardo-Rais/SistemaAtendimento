public class Posto {
    //Atributos
    private String nome;
    private String estado;
    private boolean ativo;

    //Construtor
    public Posto(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
        this.ativo = true;
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    //Métodos
    public void ativarPosto(){
        this.ativo = true;
    }

    public String toString(){
        return nome + " " + estado;
    }

}

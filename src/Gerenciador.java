import filas.FilaDinamica;

public class Gerenciador {
    //Atributos
    private FilaDinamica<Posto> fila = new FilaDinamica<>();
    private FilaDinamica<String> senhasNormais;
    private FilaDinamica<String> senhasPrioritarias;

    public Gerenciador(FilaDinamica<String> senhaNormais, FilaDinamica<String> senhasPrioritarias) {
        this.senhasNormais = senhaNormais;
        this.senhasPrioritarias = senhasPrioritarias;
    }

    //Getters & Setters
    public FilaDinamica<Posto> getFila() {
        return fila;
    }
    public void setFila(FilaDinamica<Posto> fila) {
        this.fila = fila;
    }


    //Insere postos na filaDinâmica do Gerenciador
    public boolean inserirPosto(Posto posto) {
        if (fila.size() < 5 || fila.isEmpty()) {
            fila.enqueue(posto);
            return true;
        }
        System.out.printf("Erro ao inserir posto %s, limite de 5 postos atingido", posto.getNome());
        return false;
    }

    //Método para imprimir o menu
    public void menu(){
        System.out.println("O que você deseja fazer?");
        System.out.println("1. Senha normal");
        System.out.println("2. Senha prioritária");
        System.out.println("0. Sair");
    }

    public void aguardandoAtendimento(){
        int esperando = senhasNormais.size() + senhasPrioritarias.size();
        System.out.printf("Tem %d senhas arguadando atendimento", esperando);
    }
}

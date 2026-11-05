import filas.FilaDinamica;
import pilha.PilhaDinamica;

import java.util.Random;

public class Gerenciador {
    //Atributos
    private FilaDinamica<Posto> fila = new FilaDinamica<>();
    private FilaDinamica<String> senhasNormais;
    private FilaDinamica<String> senhasPrioritarias;
    private PilhaDinamica<String> atendidos = new PilhaDinamica<>();
    private int postosAtivos = 0;
    private int contador = 0;
    private int numeradorN = 0;
    private int numeradorP = 0;
    private Random random = new Random();

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

    public FilaDinamica<String> getSenhasNormais() {
        return senhasNormais;
    }

    public void setSenhasNormais(FilaDinamica<String> senhasNormais) {
        this.senhasNormais = senhasNormais;
    }

    public FilaDinamica<String> getSenhasPrioritarias() {
        return senhasPrioritarias;
    }

    public void setSenhasPrioritarias(FilaDinamica<String> senhasPrioritarias) {
        this.senhasPrioritarias = senhasPrioritarias;
    }

    public PilhaDinamica<String> getAtendidos() {
        return atendidos;
    }

    public void setAtendidos(PilhaDinamica<String> atendidos) {
        this.atendidos = atendidos;
    }

    public int getPostosAtivos() {
        return postosAtivos;
    }

    public void setPostosAtivos(int postosAtivos) {
        this.postosAtivos = postosAtivos;
    }


    //Insere postos na filaDinâmica do Gerenciador
    public boolean inserirPosto(Posto posto) {
        if (fila.size() < 5 || fila.isEmpty()) {
            fila.enqueue(posto);
            this.postosAtivos++;
            return true;
        }
        System.out.printf("Erro ao inserir posto %s, limite de 5 postos atingido", posto.getNome());
        return false;
    }

    public void aguardandoAtendimento(){
        int esperando = senhasNormais.size() + senhasPrioritarias.size();
        System.out.printf("Tem %d senhas arguadando atendimento", esperando);
        System.out.println();
        System.out.printf("%d normais e %d prioritárias",  senhasNormais.size(), senhasPrioritarias.size() );
    }

    public void menu(){
        System.out.println("----- Menu do Sistema -----");
        System.out.println("O que deseja fazer?");
        System.out.println("1. Sortear senha");
        System.out.println("2. Atender senha");
        System.out.println("3. Desativar Posto");
        System.out.println("4. Ativar Posto");
        System.out.println("0. Sair");
    }

    public void verificarAtivos(){
        this.postosAtivos = 0;
        for (int i = 0; i < fila.size(); i++) {
            if(fila.search(i).isAtivo()){
                postosAtivos++;
            }
        }
        System.out.printf("Tem %d postos ativos", postosAtivos);
        System.out.println();
    }

    public void desativarPosto(int posicao){
       if(postosAtivos >3) {
           fila.search(posicao).setEstado("Desativado");
           postosAtivos--;
       } else{ System.out.println("Erro não podemos ter menos de 3 postos ativos!"); }
    }

    public void ativarPosto(int posicao){
        if(fila.search(posicao).getEstado().equals("Desativado")){
            fila.search(posicao).setEstado("ativado");
        }
    }

    public void inserirSenhaNormal(String senha){
        this.senhasNormais.enqueue(senha);
        System.out.println(senha);
    }

    public void inserirSenhaPrioritario(String senha){
        this.senhasPrioritarias.enqueue(senha);
        System.out.println(senha);
    }

    public void sortearSenha(){
        boolean probabilidade = random.nextInt(4) == 0;
        String senha = "";
        if(!probabilidade){
            numeradorN++;
            senha = String.format("N%02d", numeradorN);
            inserirSenhaNormal(senha);
        } else {
            numeradorP++;
            senha = String.format("P%02d", numeradorP);
            inserirSenhaPrioritario(senha);
        }
    }

    public void desistencia(){
        boolean probabilidade = random.nextInt(10) == 0;
        if(probabilidade){
            boolean sortearTipo = random.nextInt(2) == 0;
            if(sortearTipo){
                String senha = senhasPrioritarias.front();
                senhasPrioritarias.dequeue();
                System.out.printf("A senha %s desistiu",  senha);
            } else {
                String senha = senhasNormais.front();
                senhasNormais.dequeue();
                System.out.printf("A senha %s desistiu",  senha);
            }
        }
    }

    public void removerSenhaNormal() {
        String senha = this.senhasNormais.dequeue();
        System.out.println(senha);
        this.atendidos.push(senha);
    }

    public void removerSenhaPrioritario() {
        String senha = this.senhasPrioritarias.dequeue();
        System.out.println(senha);
        this.atendidos.push(senha);
    }

    public void atenderSenha() {
        if (contador < 2) {
            removerSenhaNormal();
            contador++;
        } else {
            removerSenhaPrioritario();
            contador = 0;
        }
    }
}

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
    private int desistencias = 0;
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
        Posto proximo = primeiroPostoLivre();
        System.out.printf("Tem %d senhas arguadando atendimento: %d normais e %d prioritárias\n", esperando, senhasNormais.size(), senhasPrioritarias.size());
        if (proximo != null) {
            System.out.printf("A próxima senha irá para o posto: %s\n", proximo.getNome());
        } else {
            System.out.println("A próxima senha irá aguardar: todos os postos estão ocupados");
        }
        System.out.println("Postos:" + fila);
        String[] prox = proximasDuas();
        System.out.println("Próximas duas: "
                + (prox[0] != null ? prox[0] : "-") + ", "
                + (prox[1] != null ? prox[1] : "-"));
        System.out.printf("Atendidos: %d | Desistentes: %d\n", atendidos.size(), desistencias);
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
           fila.search(posicao).setAtivo(false);
           fila.search(posicao).setEstado("Ocupado");
           postosAtivos--;
       } else{ System.out.println("Erro não podemos ter menos de 3 postos ativos!"); }
    }

    public void ativarPosto(int posicao){
        if(!fila.search(posicao).isAtivo()){
            fila.search(posicao).setAtivo(true);
            fila.search(posicao).setEstado("Livre");
        }
    }

    public Posto primeiroPostoLivre() {
        for (int i = 0; i < fila.size(); i++) {
            Posto p = fila.search(i);
            if (p.isAtivo() && p.getEstado().equals("Livre")) {
                return p;
            }
        }
        return null; // nenhum posto livre
    }

    public Posto primeiroPostoOcupado() {
        for (int i = 0; i < fila.size(); i++) {
            Posto p = fila.search(i);
            if (p.isAtivo() && p.getEstado().equals("Ocupado")) {
                return p;
            }
        }
        return null; // nenhum posto livre
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
                desistencias++;
            } else {
                String senha = senhasNormais.front();
                senhasNormais.dequeue();
                System.out.printf("A senha %s desistiu",  senha);
                desistencias++;
            }
        }
    }

    public void removerSenhaNormal() {
        String senha = this.senhasNormais.dequeue();
        System.out.println(senha);
        if(senha != null)
            this.atendidos.push(senha);
        else
            System.out.println("Nenhuma senha encontrada!");
    }

    public void removerSenhaPrioritario() {
        String senha = this.senhasPrioritarias.dequeue();
        System.out.println(senha);
        if(senha != null)
            this.atendidos.push(senha);
        else
            System.out.println("Nenhum senha encontrada!");
    }

    private boolean usarNormal() {
        boolean temN = !senhasNormais.isEmpty();
        boolean temP = !senhasPrioritarias.isEmpty();
        return (contador < 2 && temN) || !temP;
    }

    public boolean atenderSenha() {
        // Verifica se tem senha para atender
        if (senhasNormais.isEmpty() && senhasPrioritarias.isEmpty()) return false;

        // 2. Verifica se há um posto livre
        Posto p = primeiroPostoLivre();
        if (p == null) return false;

        // Se 1 e 2 passarem aí atende a senha
        String senha;
        if (usarNormal()) {
            senha = senhasNormais.dequeue();
            contador++;
        } else {
            senha = senhasPrioritarias.dequeue();
            contador = 0;
        }

        atendidos.push(senha);
        p.setEstado("Ocupado");
        p.setSenha(senha);
        System.out.printf("Senha: %s será atendida pelo posto %s\n", senha, p.getNome());
        return true;
    }

    public String[] proximasDuas() {
        String[] proximas = new String[2];
        int c = contador;      // cópia: não mexe no contador real
        int idxN = 0;          // quantas normais "já foram vistas"
        int idxP = 0;          // quantas prioritárias "já foram vistas"

        for (int i = 0; i < 2; i++) {
            boolean temN = idxN < senhasNormais.size();
            boolean temP = idxP < senhasPrioritarias.size();

            if (!temN && !temP) break;   // acabaram as senhas

            // mesma regra do atenderSenha()
            boolean usaNormal = (c < 2 && temN) || !temP;

            if (usaNormal) {
                proximas[i] = senhasNormais.search(idxN++);
                c++;
            } else {
                proximas[i] = senhasPrioritarias.search(idxP++);
                c = 0;
            }
        }
        return proximas;
    }
}

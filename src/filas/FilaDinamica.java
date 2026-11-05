package filas;

public class FilaDinamica<T> implements QueueADT<T>{
    private No comeco;
    private No fim;
    private int tamanho;

    public FilaDinamica() {
        this.comeco = null;
        this.fim = null;
        this.tamanho = 0;
    }

    @Override
    public boolean enqueue(T item) {
        No novo = new No(item);
        if(this.fim == null) {
            this.comeco = this.fim = novo;
        } else {
            this.fim.setProximo(novo);
            this.fim = novo;
        }
        this.tamanho++;
        return true;
    }

    @Override
    public T dequeue() {
        if(this.comeco == null){return null;}
        T atendido = (T) this.comeco.getElemento();
        this.comeco = this.comeco.getProximo();
        if(this.comeco == null) {this.fim = null;}
        this.tamanho--;
        return atendido;
    }


    @Override
    public T front() {
        return (this.comeco == null) ? null : (T)this.comeco.getElemento();
    }

    @Override
    public T rear() {
        return (this.fim == null) ? null : (T)this.fim.getElemento();
    }

    @Override
    public boolean isEmpty() {
        return this.comeco == null;
    }

    @Override
    public int size() {
        return this.tamanho;
    }

    @Override
    public void clear() {
        this.comeco = null;                 // nós sem referência são coletados
        this.fim = null;
        this.tamanho = 0;
    }

    @Override
    public int search(T item){
        int posicao = 1;
        for(No<T> atual = this.comeco; atual != null; atual = atual.getProximo()){
            if(atual.getElemento().equals(item)){
                return posicao;
            }
            posicao++;
        }
        return -1;
    }

    public T search(int posicao){
        No<T> atual = this.comeco;
        for (int i = 0; i < this.tamanho; i++) {
            if(i == posicao){
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        System.out.println("Posição não encontrada!");
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[início] ");
        for (No atual = this.comeco; atual != null; atual = atual.getProximo()) {
            sb.append(atual.getElemento());
            if (atual.getProximo() != null) sb.append(", ");
        }
        return sb.append(" [fim]").toString();
    }
}
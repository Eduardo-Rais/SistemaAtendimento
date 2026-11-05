package pilha;

public class PilhaDinamica<T> implements StackADT<T> {
    private No<T> topo;
    private int tamanho;

    public PilhaDinamica(){
        this.topo = null;
        this.tamanho = 0;
    }

    public No<T> getTopo() {
        return topo;
    }

    @Override
    public boolean push(T elemento){
        No<T> novo = new No<T>(elemento);
        if (isEmpty()){
            this.topo = novo;
        } else {
            topo.setProximo(novo);
            novo.setAnterior(topo);
            topo = novo;
        }
        this.tamanho++;
        return true;
    }

    @Override
    public T pop(){
        if(isEmpty()){return null;}
        else {
            T atendido = this.topo.getElemento();
            this.topo = this.topo.getAnterior();
            this.topo.setProximo(null);
            this.tamanho--;
            return atendido;
        }
    }

    @Override
    public T peek(){return (this.topo == null) ? null : this.topo.getElemento();}

    public boolean isEmpty(){ return (this.tamanho == 0) ? true: false;}

    @Override
    public int size(){return this.tamanho;}

    @Override
    public void clear(){
        if (isEmpty()){return;}
        while(this.topo.getAnterior()!=null){
            this.topo = this.topo.getAnterior();
            this.topo.setProximo(null);
        }
        topo = null;
        this.tamanho = 0;
    }

    @Override
    public int search(T item){
        int posicao = 1;
        for(No<T> atual = this.topo; atual != null; atual = atual.getAnterior()){
            if(atual.getElemento().equals(item))
                return posicao;
            posicao++;
        }
        return -1;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[Topo] ");
        for (No atual = this.topo; atual != null; atual = atual.getAnterior()){
            sb.append(atual.getElemento());
            if(atual.getAnterior() != null) sb.append(", ");
        }
        return sb.append(" [Fim]").toString();
    }
}

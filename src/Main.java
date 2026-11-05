import filas.FilaDinamica;
import pilha.PilhaDinamica;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        FilaDinamica<String> senhaNormal =  new FilaDinamica<>();
        FilaDinamica<String> senhaPrioritaria = new FilaDinamica<>();
        PilhaDinamica<String> atendidos = new PilhaDinamica<>();

        Gerenciador g = new Gerenciador(senhaNormal, senhaPrioritaria);
        g.menu();

        //criando postos, não deixa inserir mais que 5
        Posto posto1 = new Posto("Ipanema", "Livre");
        Posto posto2 = new Posto("Ipiranga", "Ocupado");
        Posto posto3 = new Posto("Cruzeiro", "Livre");
        Posto posto4 = new Posto("Alvorada", "Ocupado");
        Posto posto5 = new Posto("Restinga", "Livre");


        //Botando postos dentro do gerenciador
        g.inserirPosto(posto1);
        g.inserirPosto(posto2);
        g.inserirPosto(posto3);
        g.inserirPosto(posto4);
        g.inserirPosto(posto5);

        //ativando postos, eles inicializam desativados
        posto2.ativarPosto();
        posto3.ativarPosto();
        posto4.ativarPosto();


    }
}

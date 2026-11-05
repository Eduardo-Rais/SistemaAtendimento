import filas.FilaDinamica;


public class Main {
    public static void main(String[] args) {

        FilaDinamica<String> senhaNormal =  new FilaDinamica<>();
        FilaDinamica<String> senhaPrioritaria = new FilaDinamica<>();

        Gerenciador g = new Gerenciador(senhaNormal, senhaPrioritaria);

        //criando postos, não deixa inserir mais que 5
        Posto posto1 = new Posto("Ipanema", "Livre");
        Posto posto2 = new Posto("Ipiranga", "Livre");
        Posto posto3 = new Posto("Cruzeiro", "Livre");
        Posto posto4 = new Posto("Alvorada", "Livre");
        Posto posto5 = new Posto("Restinga", "Livre");


        //Botando postos dentro do gerenciador
        System.out.println(g.inserirPosto(posto1));
        System.out.println(g.inserirPosto(posto2));
        System.out.println(g.inserirPosto(posto3));
        System.out.println(g.inserirPosto(posto4));
        System.out.println(g.inserirPosto(posto5));


        for (int i = 0; i < 3; i++) {
            g.atenderSenha();
        }
        System.out.println();

        System.out.println(g.getAtendidos());
        g.aguardandoAtendimento();
    }
}

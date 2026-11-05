import filas.FilaDinamica;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random gerador = new Random();
        int limite = 40;
        int repeticoes = 0;

        FilaDinamica<String> senhaNormal =  new FilaDinamica<>();
        FilaDinamica<String> senhaPrioritaria = new FilaDinamica<>();

        Gerenciador g = new Gerenciador(senhaNormal, senhaPrioritaria);

        //criando postos, não deixa inserir mais que 5
        Posto posto1 = new Posto("Ipanema");
        Posto posto2 = new Posto("Ipiranga");
        Posto posto3 = new Posto("Cruzeiro");
        Posto posto4 = new Posto("Alvorada");
        Posto posto5 = new Posto("Restinga");


        //Botando postos dentro do gerenciador
        System.out.println(g.inserirPosto(posto1));
        System.out.println(g.inserirPosto(posto2));
        System.out.println(g.inserirPosto(posto3));
        System.out.println(g.inserirPosto(posto4));
        System.out.println(g.inserirPosto(posto5));

        while (repeticoes < limite || senhaNormal.size() + senhaPrioritaria.size() != 0) {

            //Chance de 1/3 de sortear uma senha normal ou prioritária
            boolean sortear = gerador.nextInt(3) == 0;
            if (sortear)
                g.sortearSenha();

            //Chance de 1/10 do cliente desistir
            sortear = gerador.nextInt(5) == 0;
            if (sortear)
                g.desistencia();

            //Chance de 1/4 de liberar um posto
            sortear = gerador.nextInt(4) == 0;
            if (sortear) {
                Posto p = g.primeiroPostoOcupado();
                if (p != null) {          //só libera se tiver um ocupado
                    p.setEstado("Livre");
                    p.setSenha(null);
                }
            }

            //chance de atender senha
            sortear = gerador.nextInt(2) == 0;
            if (sortear){
                g.atenderSenha();
            }

            g.aguardandoAtendimento();
            System.out.println();
            System.out.println();
            repeticoes++;
        }

        System.out.println(g.getAtendidos());
    }
}

package Main;

import Voo.Aviao;
import Voo.Espera;
import Voo.Pista;

public class Main {
    public static void main(String[] args) {
        Espera espera = new Espera();
        Pista pista = new Pista();
        espera.setPista(pista);
        Aviao aviao1 = new Aviao(2, 2);
        Aviao aviao2 = new Aviao(4, 5);
        Aviao aviao3 = new Aviao(6, 1);
        Aviao aviao4 = new Aviao(8, 5);
        Aviao aviao5 = new Aviao(10, 8);
        Aviao aviao6 = new Aviao(12, 3);
        Aviao aviao7 = new Aviao(14, 3);

        espera.inserirInicio(aviao1);
        espera.inserirFim(aviao2);
        espera.inserirFim(aviao3);
        espera.inserirFim(aviao4);
        espera.pousar();
        espera.inserirFim(aviao5);
        espera.inserirFim(aviao6);
        espera.inserirFim(aviao7);
        System.out.println(espera.mostraLista());
        espera.gastarCombustivel();

        System.out.println(espera.mostraLista());

        espera.pousar();
        System.out.println("pousado");

        System.out.println(espera.mostraLista());
        System.out.println(pista.mostraLista());


    }
}

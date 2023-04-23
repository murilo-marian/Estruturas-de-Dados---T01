package Main;

import Voo.Aviao;
import Voo.Espera;
import Voo.Pista;

public class Main {
    public static void main(String[] args) {
        int decolagens = 0;
        int pousos = 0;


        Espera espera = new Espera();
        Pista pista = new Pista();
        espera.setPista(pista);
        Aviao aviao1 = new Aviao(2);
        Aviao aviao2 = new Aviao(5);
        Aviao aviao3 = new Aviao(1);
        Aviao aviao4 = new Aviao(5);
        Aviao aviao5 = new Aviao(8);
        Aviao aviao6 = new Aviao(3);
        Aviao aviao7 = new Aviao(3);

        espera.inserirInicio(aviao1);
        espera.inserirFim(aviao2);
        espera.inserirFim(aviao3);
        espera.inserirFim(aviao4);
        espera.pousar();
        espera.inserirFim(aviao5);
        System.out.println(espera.mostraLista());
        espera.gastarCombustivel();

        espera.inserirFim(aviao6);
        espera.inserirFim(aviao7);
        System.out.println(espera.mostraLista());

        espera.pousar();
        System.out.println("pousado");

        System.out.println(espera.mostraLista());
        System.out.println(pista.mostraLista());

        /*if () {

        } else if (decolagens > 0) {
            espera.pousar();
            pousos++;
            decolagens--;
        } else {
            pista.decolar();
            decolagens++;
            decolagens--;
        }*/


    }
}

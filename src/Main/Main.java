package Main;

import Voo.Aviao;
import Voo.Espera;

public class Main {
    public static void main(String[] args) {
        Espera espera = new Espera();
        Aviao aviao1 = new Aviao(2, 5);
        Aviao aviao2 = new Aviao(4, 2);
        Aviao aviao3 = new Aviao(6, 5);
        Aviao aviao4 = new Aviao(8, 7);
        Aviao aviao5 = new Aviao(10, 8);
        Aviao aviao6 = new Aviao(12, 1);
        Aviao aviao7 = new Aviao(14, 22);

        espera.setInicio(aviao1);
    }
}

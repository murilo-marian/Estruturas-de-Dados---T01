package Voo;

public class Espera {
    private Aviao inicio;

    public Espera() {
    }

    public Espera(Aviao inicio) {
        this.inicio = inicio;
    }

    public boolean vazia() {
        return inicio == null;
    }

    public Aviao getInicio() {
        return inicio;
    }

    public void setInicio(Aviao inicio) {
        this.inicio = inicio;
    }

    public void inserirInicio(Aviao aviao) {
        aviao.setProx(inicio);
        inicio = aviao;
    }

    public void mostraLista() {
        if (vazia()) {
            System.out.println("Lista Vazia");
            return;
        }
        Aviao aux = inicio;
        while (aux != null) {
            System.out.println(aux + "  ");
            aux = aux.getProx();
        }
        System.out.println();
    }

    public void deleteComValor(int valor) {
        if (vazia()) {
            return;
        }
        if (inicio.getId() == valor) {
            inicio = inicio.getProx();
            return;
        }
        Aviao aux = inicio;
        while (aux.getProx() != null) {
            if (aux.getProx().getId() == valor) {
                aux.setProx(aux.getProx().getProx());
                return;
            }
            aux = aux.getProx();
        }
    }

    public void inserirFim(Aviao aviao) {
        Aviao aux = inicio;
        while (aux.getProx() != null) {
            aux = aux.getProx();
        }
        aux.setProx(aviao);
    }

    public void pousar(Pista pista) {

        Aviao aux = inicio;
        Aviao emergencia = aux;
        while (aux.getProx().getProx() != null) {
            emergencia = checarEmergencia(aux, emergencia); //checa se há algum pouso de emergencia a ser realizado
            aux = aux.getProx();
            System.out.println(emergencia.getProx().toString());
        }

        if (emergencia.getProx().getGas() < 3) {
            System.out.println("emergencia");
            System.out.println(emergencia.getProx().toString());

            if (emergencia.getProx().getProx() != null) {
                emergencia.setProx(emergencia.getProx().getProx());
                pista.inserir(emergencia.getProx());
            } else {
                emergencia.setProx(null);
                pista.inserir(emergencia.getProx());
            }
        } else {
            pista.inserir(aux.getProx());
            aux.setProx(null);
        }
    }

    public Aviao checarEmergencia(Aviao aux, Aviao emergencia) {
            if (aux.getGas() < emergencia.getProx().getGas()) {
                emergencia = aux;
                return emergencia;
            }
        return emergencia;
    }
}

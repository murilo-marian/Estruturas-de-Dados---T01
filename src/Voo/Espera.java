package Voo;

public class Espera {
    private Aviao inicio;
    private Pista pista;

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

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    public void inserirInicio(Aviao aviao) {
        aviao.setProx(inicio);
        inicio = aviao;
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

    public void pousar() {
        Aviao aux = inicio;
        Aviao emergencia = aux;

        while (aux.getProx() != null) {
            emergencia = checarEmergencia(aux, emergencia); //checa se há algum pouso de emergencia a ser realizado
            aux = aux.getProx();
        }

        if (emergencia.getGas() < 3) {
            System.out.println("emergencia");
            deleteComValor(emergencia.getId());
            pista.inserir(emergencia);
        } else {
            System.out.println("delete final");
            deleteComValor(aux.getId());
            pista.inserir(aux);
        }
    }

    public Aviao checarEmergencia(Aviao aux, Aviao emergencia) {
            if (aux.getGas() < emergencia.getGas()) {
                emergencia = aux;
                return emergencia;
            }
        return emergencia;
    }

    public String mostraLista(){
        StringBuilder teste = new StringBuilder();
        if (vazia()) {
            return "Lista Vazia";
        }
        Aviao aux = inicio;
        while (aux != null) {
            teste.append(aux.toString() + "  ");
            aux =  aux.getProx();
        }
        return teste.toString();
    }

    public void gastarCombustivel() {
        Aviao aux = inicio;
        while (aux != null) {
            aux.setGas(aux.getGas() - 1);
            aux = aux.getProx();
        }
    }
}

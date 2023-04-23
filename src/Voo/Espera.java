package Voo;

public class Espera {
    private Aviao inicio;
    private Pista pista;
    private int lastId;
    private int tamanho;

    public Espera() {
        tamanho = 0;
    }

    public Espera(Aviao inicio) {
        this.inicio = inicio;
        tamanho = 1;
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
        lastId = 00;
        aviao.setId(lastId);
        tamanho++;
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
        tamanho--;
    }

    public void inserirFim(Aviao aviao) {
        Aviao aux = inicio;
        while (aux.getProx() != null) {
            aux = aux.getProx();
        }
        aux.setProx(aviao);

        lastId = lastId + 2;
        aviao.setId(lastId);
        tamanho++;
    }

    public void pousar() {
        Aviao aux = inicio;
        Aviao emergencia = aux;
        int tempo = 0;

        while (aux.getProx() != null) {
            emergencia = checarEmergencia(aux, emergencia); //checa se há algum pouso de emergencia a ser realizado
            aux = aux.getProx();
            tempo += somarTempoDeEspera(aux);
            tempo = tempo / tamanho;
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

        gastarCombustivel();
    }

    private int somarTempoDeEspera(Aviao aux) {
        return aux.getTempoDeEspera();
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
            aux.setTempoDeEspera(aux.getTempoDeEspera() + 1);
            aux = aux.getProx();
        }
    }
}

package Voo;

public class Pista {

    private Aviao inicio;
    private Aviao fim;
    private int tamanho = 0;
    private int lastId = 0;

    public boolean estaVazio() {
        return tamanho == 0;
    }

    public Aviao getInicio() {
        if (!estaVazio()) {
            return inicio;
        } else {
            return null;
        }
    }

    public void setInicio(Aviao inicio) {
        this.inicio = inicio;
    }

    public Aviao getFim() {
        return fim;
    }

    public void setFim(Aviao fim) {
        this.fim = fim;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void inserir(Aviao aviao) {
        if (estaVazio()) {
            inicio = aviao;
        } else {
            fim.setProx(aviao);
        }
        fim = aviao;
        aviao.setProx(null);
        aviao.setTempoDeEspera(0);
        tamanho++;
        lastId += 2;
        aviao.setId(lastId);
    }

    public String decolar() {
        if (!estaVazio()) {
            int id = inicio.getId();
            inicio = inicio.getProx();
            tamanho--;
            return "Decolado o avião de ID: " + id;
        } else {
            return "Fila Vazia";
        }
    }

    public int calcularTempoEspera() {
        Aviao aux = inicio;
        int soma = 0;
        while (aux != null) {
            soma += aux.getTempoDeEspera();
            aux = aux.getProx();
        }
        int media = soma / tamanho;
        return media;
    }

    public String mostraLista() {
        StringBuilder teste = new StringBuilder();
        if (estaVazio()) {
            return "Lista Vazia";
        }
        Aviao aux = inicio;
        while (aux != null) {
            teste.append(aux.toString() + "  ");
            aux = aux.getProx();
        }
        return teste.toString();
    }

    public void passarTempo() {
        Aviao aux = inicio;
        while (aux != null) {
            aux.setTempoDeEspera(aux.getTempoDeEspera() + 1);
            aux = aux.getProx();
        }
    }

}

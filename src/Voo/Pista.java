package Voo;

import Util.Node;

public class Pista {

    private Aviao inicio;
    private Aviao fim;
    private int tamanho = 0;

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
        aviao.setId(2); //gerar id
        if (estaVazio()) {
            inicio = aviao;
        } else {
            fim.setProx(aviao);
        }
        fim = aviao;
        tamanho++;
    }

    public void decolar() {
        if (!estaVazio()) {
            System.out.println("Decolado o voo: " + inicio.getId());
            inicio = inicio.getProx();
            tamanho--;
        } else {
            System.out.println("A fila está vazia");
        }
    }
}

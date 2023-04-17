package Voo;

import Util.Node;

public class Pista {

    private Node inicio;
    private Node fim;
    private int tamanho = 0;

    public boolean estaVazio() {
        return tamanho == 0;
    }

    public Aviao getInicio() {
        if (!estaVazio()) {
            return inicio.getData();
        } else {
            return null;
        }
    }

    public void setInicio(Node inicio) {
        this.inicio = inicio;
    }

    public Node getFim() {
        return fim;
    }

    public void setFim(Node fim) {
        this.fim = fim;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void inserir(Aviao aviao) {
        Node novo = new Node(aviao);
        novo.getData().setId(2); //gerar id
        if (estaVazio()) {
            inicio = novo;
        } else {
            fim.setProx(novo);
        }
        fim = novo;
        tamanho++;
    }

    public void decolar() {
        if (!estaVazio()) {
            System.out.println("Decolado o voo: " + inicio.getData().getId());
            inicio = inicio.getProx();
            tamanho--;
        } else {
            System.out.println("A fila está vazia");
        }
    }
}

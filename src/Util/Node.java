package Util;

import Voo.Aviao;

public class Node {
    private Aviao aviao;
    private Node prox;

    //contrutor
    public Node(Aviao aviao) {
        this.aviao = aviao;
        this.prox = null;
    }

    //getter e setter

    public Aviao getData() {
        return aviao;
    }

    public void setData(Aviao aviao) {
        this.aviao = aviao;
    }

    public Node getProx() {
        return prox;
    }

    public void setProx(Node prox) {
        this.prox = prox;
    }
}

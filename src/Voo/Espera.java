package Voo;
import java.util.Random;

public class Espera {
    private Aviao inicio;
    private Pista pista;
    private int lastId = -1;
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
        lastId = lastId + 2;
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
        if (inicio == null) {
            inserirInicio(aviao);
            return;
        }

        Aviao aux = inicio;
        while (aux.getProx() != null) {
            aux = aux.getProx();
        }
        aux.setProx(aviao);

        lastId = lastId + 2;
        aviao.setId(lastId);
        tamanho++;
    }

    public Aviao pousar() {
        Aviao aux = inicio;

        while (aux.getProx() != null) {
            aux = aux.getProx();
        }

        System.out.println("delete final");
        deleteComValor(aux.getId());
        Aviao aviao = new Aviao(aux);
        pista.inserir(aux);

        gastarCombustivel();

        return aviao;
    }

    public Aviao pousarEmergencia() {
        Aviao aux = inicio;
        Aviao emergencia = aux;

        while (aux != null) {
            emergencia = pegarEmergencia(aux, emergencia); //checa se há algum pouso de emergencia a ser realizado
            aux = aux.getProx();
        }

        if (emergencia.getGas() == 0) {
            System.out.println("BOOM");
        }

        deleteComValor(emergencia.getId());
        Aviao aviao = new Aviao(emergencia);

        pista.inserir(emergencia);

        gastarCombustivel();

        return aviao;
    }

    public Aviao pegarEmergencia(Aviao aux, Aviao emergencia) {
        if (aux.getGas() < emergencia.getGas()) {
            emergencia = aux;
            return emergencia;
        }
        return emergencia;
    }

    public Boolean checarEmergencia() {
        Aviao aux = inicio;
        while (aux != null) {
            if (aux.getGas() < 4) {
                return true;
            }

            aux = aux.getProx();
        }
        return false;
    }

    public String mostraLista() {
        StringBuilder teste = new StringBuilder();
        if (vazia()) {
            return "Lista Vazia";
        }
        Aviao aux = inicio;
        while (aux != null) {
            teste.append(aux.toString() + "  ");
            aux = aux.getProx();
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

    public void gerarAviao(int n) {
        Random rand = new Random();
        Aviao aviao = new Aviao();
        for (int i = 0; i < n; i++) {
            aviao.setGas(rand.nextInt(1, 21));
            inserirFim(aviao);
        }
    }
}

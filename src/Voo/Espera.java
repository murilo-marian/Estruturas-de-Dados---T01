package Voo;

import java.util.Random;

public class Espera {
    private Aviao inicio;
    private Pista pista;
    private int id;
    private int lastId = -1;
    private int tamanho;
    private int pousosRatio;

    public Espera() {
        tamanho = 0;
    }

    public boolean vazia() {
        return inicio == null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getPousosRatio() {
        return pousosRatio;
    }

    public void setPousosRatio(int pousosRatio) {
        this.pousosRatio = pousosRatio;
    }

    public void inserirInicio(Aviao aviao) {
        aviao.setProx(inicio);
        inicio = aviao;
        lastId = lastId + 2;
        aviao.setId(lastId);
        setTamanho(getTamanho() + 1);
    }

    public void inserir(Aviao aviao) {
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
        setTamanho(getTamanho() + 1);
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

    public Aviao pousar() {
        setTamanho(getTamanho() - 1);
        Aviao aux = inicio;

        while (aux.getProx() != null) {
            aux = aux.getProx();
        }

        deleteComValor(aux.getId());
        Aviao aviao = new Aviao(aux);
        pista.inserir(aux);

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
            return null;
        }

        setTamanho(getTamanho() - 1);
        deleteComValor(emergencia.getId());
        Aviao aviao = new Aviao(emergencia);

        pista.inserir(emergencia);
        return aviao;
    }

    public Aviao pegarEmergencia(Aviao aux, Aviao emergencia) {
        if (aux.getGas() < emergencia.getGas()) {
            emergencia = aux;
            return emergencia;
        }
        return emergencia;
    }

    public Boolean checarEmergencia(int n) {
        Aviao aux = inicio;
        while (aux != null) {
            if (aux.getGas() < n) {
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
        pista.passarTempo();
    }
}

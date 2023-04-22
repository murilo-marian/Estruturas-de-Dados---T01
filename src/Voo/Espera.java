package Voo;

public class Espera {
    private Aviao inicio;
    private Aviao fim;
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

    public void inserirInicio(Aviao novo) {
        if (inicio != null) {
            inicio.setAnt(novo);
            novo.setProx(inicio);
        }
        inicio = novo;
        if (fim == null) {
            fim = novo;
        }
    }
    
    public void inserirFim(Aviao novo) {
        if (fim != null) {
            fim.setProx(novo);
            novo.setAnt(fim);
        }
        fim = novo;
        if (inicio == null) {
            inicio = novo;
        }
    }

    public void removeInicio() {
        Aviao nodoRemovido = inicio;
        if (inicio == null) return;

        inicio = inicio.getProx();
        if (inicio != null) {
            inicio.setAnt(null);
        }

        if (nodoRemovido == fim) {
            removeFinal();
        }
    }

    public void removeFinal() {
        Aviao nodoRemovido = fim;
        if (fim == null) {
            return;
        }

        fim = fim.getAnt();
        if (fim != null) {
            fim.setProx(null);
        }
        if (nodoRemovido == inicio) {
            removeInicio();
        }
    }
    
    public void removeId(int id) {
        Aviao nodoRemovido = null;
        Aviao aux = inicio;

        while (aux != null) {
            if (aux.getId() == id) {
                nodoRemovido = aux;
                break;
            }
            aux = aux.getProx();
        }

        if (nodoRemovido == null) return;

        if (nodoRemovido == inicio) {
            removeInicio();
        } else if (nodoRemovido == fim) {
            removeFinal();
        } else {
            nodoRemovido.getAnt().setProx(nodoRemovido.getProx());
            nodoRemovido.getProx().setAnt(nodoRemovido.getAnt());
        }
    }

    

    public void pousar() {

        Aviao aux = inicio;
        Aviao emergencia = aux;
        while (aux.getProx() != null) {
            System.out.println(aux.toString());
            emergencia = checarEmergencia(aux, emergencia); //checa se há algum pouso de emergencia a ser realizado
            aux = aux.getProx();
        }
        System.out.println("loop end");

        if (emergencia.getGas() < 3) {
            System.out.println("emergencia");
            System.out.println(emergencia.toString());

            removeId(emergencia.getId());
        } else {
            removeFinal();
        }
    }

    public Aviao checarEmergencia(Aviao aux, Aviao emergencia) {
            if (aux.getGas() < emergencia.getGas()) {
                System.out.println("menor " + aux.toString());
                emergencia = aux;
                return emergencia;
            }
        return emergencia;
    }
}

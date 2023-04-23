package Voo;

public class Aviao {
    private int id;
    private int gas;
    private Aviao prox;
    private int tempoDeEspera;

    public Aviao() {
    }

    public Aviao(int gas) {
        this.id = id;
        this.gas = gas;
        this.tempoDeEspera = 0;
    }

    public Aviao(Aviao aviao) {
        this.id = aviao.getId();
        this.gas = aviao.getGas();
        this.tempoDeEspera = aviao.getTempoDeEspera();
    }

    public Aviao getProx() {
        return prox;
    }

    public void setProx(Aviao prox) {
        this.prox = prox;
    }

    public int getTempoDeEspera() {
        return tempoDeEspera;
    }

    public void setTempoDeEspera(int tempoDeEspera) {
        this.tempoDeEspera = tempoDeEspera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Aviao{");
        sb.append("id=").append(id);
        sb.append(", gas=").append(gas);
        sb.append('}');
        return sb.toString();
    }
}

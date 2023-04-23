package Voo;

public class Aviao {
    private int id;
    private int gas;
    private Aviao prox;

    public Aviao() {
    }

    public Aviao(int id, int gas) {
        this.id = id;
        this.gas = gas;
    }

    public Aviao getProx() {
        return prox;
    }

    public void setProx(Aviao prox) {
        this.prox = prox;
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

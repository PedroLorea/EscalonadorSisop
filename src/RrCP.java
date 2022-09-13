public class RrCP {

    private int quantum;
    private int tempoChegada;

    public RrCP(int quantum, int tempoChegada){
        this.quantum = quantum;
        this.tempoChegada = tempoChegada;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }
}

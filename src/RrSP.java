import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RrSP {

    private int quantum;
    private int tempoChegada;
    private int pc = 0;
    private String nomeArquivo;
    private ArrayList<String> programa;
    private Map<String, String> dados;
    private Map<String, Integer> mapa;

    public RrSP(ArrayList<String> programa, Map<String, String> dados, Map<String, Integer> mapa, int quantum, int tempoChegada, String nomeArquivo){
        this.quantum = quantum;
        this.tempoChegada = tempoChegada;
        this.programa = programa;
        this.dados = dados;
        this.mapa = mapa;
        this.nomeArquivo = nomeArquivo;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getPc(){
        return pc;
    }

    public void setPc(int pc){
        this.pc = pc;
    }

    public ArrayList<String> getPrograma() {
        return programa;
    }

    public void setPrograma(ArrayList<String> programa) {
        this.programa = programa;
    }

    public Map<String, String> getDados() {
        return dados;
    }

    public void setDados(Map<String, String> dados) {
        this.dados = dados;
    }

    public Map<String, Integer> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, Integer> mapa) {
        this.mapa = mapa;
    }


}

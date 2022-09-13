import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Execucao {

    private ArrayList<RrSP> prontos = new ArrayList<RrSP>();
    private RrSP executando;
    private ArrayList<RrSP> bloqueados = new ArrayList<RrSP>();
    private ArrayList<RrSP> finalizados = new ArrayList<RrSP>();

    private Map<String, Integer> memoria = new HashMap<>();

    private int tempo = 0;
    private int acc;

    private ComandosAritmeticos comandos = new ComandosAritmeticos();

    public Execucao(ArrayList<RrSP> programas) { //Main -> lerPrograma -> Main -> Execucao, com os programas no arraylist
        this.prontos = programas;
        prontos = organizaTempo(prontos);
        executar();

    }

    public ArrayList<RrSP> organizaTempo(ArrayList<RrSP> organizaTempo) { //Organiza tempo de chegada em ordem, sendo o 1º o menor tempo de chegada
        if (organizaTempo.size() > 1)
            organizaTempo.sort((p1, p2) -> Integer.compare(p1.getTempoChegada(), p2.getTempoChegada()));
        return organizaTempo;
    }

    public void executar() { //Chama o executar2 caso exista no pronto algum programa no tempoChegada == tempo

        for (int i = 0; i < prontos.size(); i++) {
            if (prontos.get(i).getTempoChegada() == tempo && executando == null) {
                executando = prontos.get(i);
                prontos.remove(i);
                executar2();
            } else {
                tempo++;
            }
        }
    }

    public void executar2() { //Pega os dados do programa: mapa, dados, programa e faz as operações.

        ArrayList<String> auxPrograma = executando.getPrograma();
        Map<String, String> dados = executando.getDados();
        Map<String, Integer> auxMapa = executando.getMapa();
        String auxInstrucao;
        int auxIntrucaoInt;
        int pc;


        while (executando.getPc() < auxPrograma.size() / 2) {
            int tempoEntrou = tempo;
            if ((tempo - tempoEntrou) == executando.getQuantum()) {   // Falta ver se o Quantum e Tempo de chegada estão operando corretamente
                System.out.println("Acabou o quantum!");
                break;
            }
            pc = executando.getPc();

            if(pc==0) {
                auxInstrucao = auxPrograma.get(0);
                auxIntrucaoInt = 0;
            }
            else if(pc == 1) {
                auxInstrucao = auxPrograma.get(2);
                auxIntrucaoInt = 2;
            }
            else {
                auxInstrucao = auxPrograma.get(pc * 2);
                auxIntrucaoInt = pc*2;
            }

            System.out.println("PC = " + pc); //Teste
            System.out.println("ACC = " + acc); //Teste


            if (auxInstrucao.equalsIgnoreCase("add")) {
                if ((auxPrograma.get(pc * 2 + 1)).contains("#")) {
                    int indice = auxPrograma.get(pc * 2 + 1).indexOf("#");
                    acc = comandos.add(acc, indice + 1);
                } else acc = comandos.add(acc, Integer.parseInt(auxPrograma.get(pc * 2 + 1)));
            }

            if (auxInstrucao.equalsIgnoreCase("sub")) {
                if (auxInstrucao.contains("#")) {
                    int indice = auxInstrucao.indexOf("#");
                    acc = comandos.sub(acc, indice + 1);
                } else acc = comandos.sub(acc, Integer.parseInt(auxPrograma.get(pc * 2 + 1)));
            }

            if (auxInstrucao.equalsIgnoreCase("mult")) {
                if (auxInstrucao.contains("#")) {
                    int indice = auxInstrucao.indexOf("#");
                    acc = comandos.mult(acc, indice + 1);
                } else acc = comandos.mult(acc, Integer.parseInt(auxPrograma.get(pc * 2 + 1)));
            }

            if (auxInstrucao.equalsIgnoreCase("div")) {
                if (auxInstrucao.contains("#")) {
                    int indice = auxInstrucao.indexOf("#");
                    acc = comandos.div(acc, indice + 1);
                } else acc = comandos.div(acc, Integer.parseInt(auxPrograma.get(pc * 2)));
            }

            if (auxInstrucao.equalsIgnoreCase("load")) {
                acc = Integer.parseInt(dados.get(auxPrograma.get(auxIntrucaoInt+1)));
            }

            if (auxInstrucao.equalsIgnoreCase("store")) {
                dados.replace(String.valueOf(auxPrograma.get(auxIntrucaoInt+1)), String.valueOf(acc));  
            }

            if (auxInstrucao.equalsIgnoreCase("brany")) {
                executando.setPc(auxMapa.get(auxPrograma.get(pc * 2 + 1)));
            }

            if (auxInstrucao.equalsIgnoreCase("brpos")) {
                if (acc > 0) executando.setPc(auxMapa.get(auxPrograma.get(pc * 2)));
            }

            if (auxInstrucao.equalsIgnoreCase("brzero")) {
                if (acc == 0) executando.setPc(auxMapa.get(auxPrograma.get(pc * 2)));
            }
            if (auxInstrucao.equalsIgnoreCase("brneg")) {
                if (acc < 0) executando.setPc(auxMapa.get(auxPrograma.get(pc * 2)));
            }

            if (auxInstrucao.equalsIgnoreCase("syscall")) {
                if (auxPrograma.get(1).equals("0")) {
                    finalizados.add(executando);
                    System.out.println("Finalizou");
                }
                if (auxPrograma.get(1).equals("1")) {  //FALTA FAZER SYSCALL 1 E SYSCALL 2

                }
                if (auxPrograma.get(1).equals("2")) {

                }

            }
            executando.setPc(pc + 1);
            tempo++;
        }
    }
}



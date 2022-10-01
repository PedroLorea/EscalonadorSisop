import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExecucaoCP {

    private ArrayList<RrCP> prontos = new ArrayList<RrCP>();
    private Map<RrCP, Integer> bloqueados = new HashMap<RrCP, Integer>();
    private ArrayList<RrCP> finalizados = new ArrayList<RrCP>();

    private ArrayList<String> auxPrograma = new ArrayList<>();
    private Map<String, String> dados = new HashMap<String, String>();
    private Map<String, Integer> auxMapa = new HashMap<String, Integer>();

    private int tempo = 0;
    private int acc;
    private int qntProgramas;

    private ComandosAritmeticos comandos = new ComandosAritmeticos();
    Scanner in = new Scanner(System.in);

    public ExecucaoCP(ArrayList<RrCP> programas) { //Main -> lerPrograma -> Main -> Execucao, com os programas no arraylist
        this.prontos = programas;
        qntProgramas = programas.size();
        prontos = organizaTempo(prontos);
        for (int i = 0; i < prontos.size(); i++) { //Teste
            System.out.println("Número de instruções no programa: ");
            System.out.println(prontos.get(i).getPrograma().size());
        }
        executar();

    }

    public ArrayList<RrCP> organizaTempo(ArrayList<RrCP> organizaTempo) { //Organiza tempo de chegada em ordem, sendo o 1º o menor tempo de chegada
        if (organizaTempo.size() >= 0)
            organizaTempo.sort((p1, p2) -> Integer.compare(p1.getTempoChegada(), p2.getTempoChegada()));
        return organizaTempo;
    }

    public ArrayList<RrCP> organizaPrioridade(ArrayList<RrCP> organizaPrioridade) { //Organiza Prioridade em ordem, sendo o 1º o menor tempo de chegada
        if (organizaPrioridade.size() >= 0)
            organizaPrioridade.sort((p1, p2) -> Integer.compare(p1.getPrioridade(), p2.getPrioridade()));
        return organizaPrioridade;
    }

    public void bloqueados(){
        if(bloqueados.size() >= 1){
            RrCP rrCP;
            int contador;

            for (Map.Entry<RrCP, Integer> entrada : bloqueados.entrySet()) {
                rrCP = entrada.getKey();
                contador = entrada.getValue();
                if(contador == 0){
                    rrCP.setTempoChegada(tempo+1);
                    prontos.add(rrCP);
                    bloqueados.remove(rrCP);
                } else {
                    bloqueados.replace(rrCP, contador - 1);
                }
            }
        }
    }

    public int gerador(){
        int max = 15;
        int min = 10;
        int intervalo = max - min + 1;
        return (int)(Math.random() * intervalo) + min;
    }

    public void executar() { //Chama o executar2(), caso exista no pronto algum programa no tempoChegada == tempo

        boolean executou;
        RrCP executando;

        while(qntProgramas != finalizados.size()) {
            if(prontos.size() >= 1) {
                for (int i = 0; i < prontos.size(); i++) {
                    System.out.println("Qnt Fila Prontos: " + prontos.size());
                    if (prontos.get(i).getTempoChegada() == tempo) {
                        executando = prontos.get(i);
                        System.out.println("executando" + executando.getPrioridade());
                        prontos.remove(i);
                        executou = executar2(executando);
                        if (executou == true) {
                            for(int j=0; j<prontos.size(); j++) {
                                if(prontos.get(i).getTempoChegada() <= tempo) prontos.get(j).setTempoChegada(tempo+1);
                            }
                            break;
                        }
                        else {
                            bloqueados.put(executando, gerador());
                        }
                    }
                }
            }
            bloqueados();
            tempo++;
        }
        System.out.println("Finalizado: QuantidadeProgramas == QuantidadeFinalizados");
        System.exit(0);
    }


    public boolean executar2(RrCP executando) { //Pega os dados do programa: mapa, dados, programa(intruções) e faz as operações.
        auxPrograma = executando.getPrograma();
        dados = executando.getDados();
        auxMapa = executando.getMapa();
        acc = executando.getAccProg();



        String auxInstrucao;
        int auxIntrucaoInt;
        int pc;
        int tempoEntrou = tempo;

        while (executando.getPc() < auxPrograma.size() / 2) {

            if ((tempo - tempoEntrou) == executando.getQuantum()) {
                System.out.println("Acabou o quantum!");
                finalizados.add(executando);
                return true;
            }
            pc = executando.getPc();
            if(pc==0) {
                auxInstrucao = auxPrograma.get(0); // Pc==0 pc==1, por questão da maneira como ta o código lá em baixo, a multiplicação da errada, então tem que fazer os if's
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

            System.out.println("PC: " + pc + " | ACC = " + acc + " | TEMPO = " + tempo +" | Executando: " + executando.getNomeArquivo() + " || Instrução: " + auxInstrucao + " " + auxPrograma.get(pc*2+1));
            System.out.println(executando.getTempoChegada() + " || " + tempo);

            if (auxInstrucao.equalsIgnoreCase("add")) {
                if(auxPrograma.get(pc*2+1).matches("[a-z]*")){
                    acc = comandos.add(acc, Integer.parseInt(dados.get(auxPrograma.get(pc*2+1))));
                }
                else if ((auxPrograma.get(pc * 2 + 1)).contains("#")) {
                    int indice = auxPrograma.get(pc * 2 + 1).indexOf("#");
                    acc = comandos.add(acc, indice + 1);
                }
                else acc = comandos.add(acc, Integer.parseInt(auxPrograma.get(pc * 2 + 1)));
            }

            if (auxInstrucao.equalsIgnoreCase("sub")) {
                if(auxPrograma.get(pc*2+1).matches("[a-z]*")){
                    acc = comandos.sub(acc, Integer.parseInt(dados.get(auxPrograma.get(pc*2+1))));
                }
                else if ((auxPrograma.get(pc * 2 + 1)).contains("#")) {
                    int indice = auxPrograma.get(pc * 2 + 1).indexOf("#");
                    acc = comandos.sub(acc, indice + 1);
                }
                else acc = comandos.sub(acc, Integer.parseInt(auxPrograma.get(pc * 2 + 1)));
            }

            if (auxInstrucao.equalsIgnoreCase("mult")) {
                if(auxPrograma.get(pc*2+1).matches("[a-z]*")){
                    acc = comandos.mult(acc, Integer.parseInt(dados.get(auxPrograma.get(pc*2+1))));
                }
                else if (auxInstrucao.contains("#")) {
                    int indice = auxInstrucao.indexOf("#");
                    acc = comandos.mult(acc, indice + 1);
                } else acc = comandos.mult(acc, Integer.parseInt(auxPrograma.get(pc * 2 + 1)));
            }

            if (auxInstrucao.equalsIgnoreCase("div")) {
                if(auxPrograma.get(pc*2+1).matches("[a-z]*")){
                    acc = comandos.div(acc, Integer.parseInt(dados.get(auxPrograma.get(pc*2+1))));
                }
                else if (auxInstrucao.contains("#")) {
                    int indice = auxInstrucao.indexOf("#");
                    acc = comandos.div(acc, indice + 1);
                } else acc = comandos.div(acc, Integer.parseInt(auxPrograma.get(pc * 2 + 1)));
            }

            if (auxInstrucao.equalsIgnoreCase("load")) {
                acc = Integer.parseInt(dados.get(auxPrograma.get(auxIntrucaoInt+1)));
            }

            if (auxInstrucao.equalsIgnoreCase("store")) {
                dados.replace(String.valueOf(auxPrograma.get(auxIntrucaoInt+1)), String.valueOf(acc));
            }


            if (auxInstrucao.equalsIgnoreCase("brany")) {
                executando.setPc(auxMapa.get(auxPrograma.get(pc * 2 + 1)+ ":"));
                pc = executando.getPc()-1;
            }
            if (auxInstrucao.equalsIgnoreCase("brpos")) {
                if (acc > 0) {
                    executando.setPc(auxMapa.get(auxPrograma.get(pc * 2 + 1) + ":"));
                    pc = executando.getPc() - 1;
                }
            }

            if (auxInstrucao.equalsIgnoreCase("brzero")) {
                if (acc == 0) {
                    executando.setPc(auxMapa.get(auxPrograma.get(pc * 2 + 1)+":"));
                    pc = executando.getPc()-1;
                }
            }
            if (auxInstrucao.equalsIgnoreCase("brneg")) {
                if (acc < 0) {
                    executando.setPc(auxMapa.get(auxPrograma.get(pc * 2 + 1)+":"));
                    pc = executando.getPc()-1;
                }
            }

            if (auxInstrucao.equalsIgnoreCase("syscall")) {
                if (auxPrograma.get(auxIntrucaoInt+1).equals("0")) {
                    finalizados.add(executando);
                    return true;
                }
                if (auxPrograma.get(auxIntrucaoInt+1).equals("1")) {
                    System.out.println("SAÍDA DE TELA: " + acc);
                    executando.setPc(pc+1);
                    return false;
                }
                if (auxPrograma.get(auxIntrucaoInt+1).equals("2")) {
                    System.out.println("DIGITAR DADO:");
                    int a = in.nextInt();
                    acc = a;
                    executando.setAccProg(acc);
                    executando.setPc(pc+1);
                    return false;
                }
            }
            executando.setAccProg(acc);
            executando.setPc(pc + 1);
            bloqueados();
            tempo++;
        }
        System.out.println("Acabou instruções programa.");
        return true;
    }
}



import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<RrSP> programasSP = new ArrayList<RrSP>();
    static ArrayList<RrCP> programasCP = new ArrayList<RrCP>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int op;
        int quantum;
        int tempoChegada;
        int prioridade;
        int op1;
        int opPrioridade = 0;
        String nomePrograma;

        System.out.println("Bem-vindo :). \n1-> Sem Prioridade \n2-> Com Prioridade");
        op1 = in.nextInt();
        switch (op1) {
            case 1:
                opPrioridade = 1;
                break;
            case 2:
                opPrioridade = 2;
                break;
        }


        if (opPrioridade == 1) {
            do {
                System.out.println("Bem-vindo ao Escalonador. \n0- Sair \n1- Adicionar programa SEM prioridade \n2- Inicializar Escalonador SEM prioridade");

                op = in.nextInt();

                switch (op) {

                    case 1: //ADICIONAR PROGRAMA SEM PRIORIDADE
                        LerArquivo lerArquivo = new LerArquivo();
                        System.out.println("Digite o nome do programa:.");
                        in.nextLine();
                        nomePrograma = in.nextLine();
                        System.out.println("Digite o quantum do programa.");
                        quantum = in.nextInt();
                        System.out.println("Digite o tempo de chegada do programa.");
                        tempoChegada = in.nextInt();

                        RrSP prog1 = lerArquivo.Armazena(nomePrograma, quantum, tempoChegada);
                        programasSP.add(prog1);
                        break;

                    case 2:
                        Execucao exe = new Execucao(programasSP);
                        break;

                    case 0:
                        System.exit(0);
                        break;
                }

            } while (op != 0);
        }

        if (opPrioridade == 2) {
            do {
                System.out.println("Bem-vindo ao Escalonador. \n0- Sair \n1- Adicionar programa COM prioridade \n2- Inicializar Escalonador COM prioridade");

                op = in.nextInt();

                switch (op) {

                    case 1: //ADICIONAR PROGRAMA COM PRIORIDADE
                        LerArquivoCP lerArquivocp = new LerArquivoCP();
                        System.out.println("Digite o nome do programa:");
                        in.nextLine();
                        nomePrograma = in.nextLine();
                        System.out.println("Digite o quantum do programa:");
                        quantum = in.nextInt();
                        System.out.println("Digite o tempo de chegada do programa:");
                        tempoChegada = in.nextInt();
                        System.out.println("Digite a prioridade: (Sendo 0- Alta, 1- Média, 2- Baixa");
                        prioridade = in.nextInt();
                        if (prioridade != 0 && prioridade != 1 && prioridade != 2) prioridade = 2;
                        RrCP prog2 = lerArquivocp.Armazena(nomePrograma, quantum, tempoChegada, prioridade);
                        System.out.println(prioridade);
                        programasCP.add(prog2);
                        break;

                    case 2:
                        ExecucaoCP exec = new ExecucaoCP(programasCP);

                    case 3:
                        //APENSAS PARA TESTAR E NÃO TER QUE FICAR ESCREVENDO TODA VEZ
                        LerArquivoCP lerArquivocp2 = new LerArquivoCP();
                        RrCP prog3 = lerArquivocp2.Armazena("prog1.txt", 20, 16, 0);
                        programasCP.add(prog3);
                        LerArquivoCP lerArquivocp3 = new LerArquivoCP();
                        RrCP prog4 = lerArquivocp3.Armazena("prog.txt", 40, 0, 1);
                        programasCP.add(prog4);
                        //LerArquivoCP lerArquivocp4 = new LerArquivoCP();
                        //RrCP prog5 = lerArquivocp4.Armazena("prog.txt", 20, 5, 1);
                        //programasCP.add(prog5);
                        break;

                    case 0:
                        System.exit(0);
                        break;
                }

            } while (op != 0);
        }
    }
}


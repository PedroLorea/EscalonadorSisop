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
            case 2:
                opPrioridade = 2;
        }


        if (opPrioridade == 1) {
            do {
                System.out.println("Bem-vindo ao Escalonador. \n0- Sair \n1- Adicionar programa SEM prioridade \n2- Adicionar programa COM prioridade \n3- Inicializar Escalonador SEM prioridade \n4- Inicializar Escalonador COM prioridade");

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

                    case 0:
                        System.exit(0);
                        break;
                }

            } while (op != 0);
        }
    }
}


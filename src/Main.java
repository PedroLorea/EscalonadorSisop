import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<RrSP> programas = new ArrayList<RrSP>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        LerArquivo lerArquivo = new LerArquivo();

        int op;
        int op2;
        int quantum;
        int tempoChegada;
        String nomePrograma;


        do {
            System.out.println("Bem-vindo ao Escalonador. \n0- Sair \n1- Adicionar programa \n2- Inicializar Escalonador");

            op = in.nextInt();

            switch(op){

                case 1: //ADICIONAR PROGRAMA
                    System.out.println("Digite o nome do programa.");
                    in.nextLine();
                    nomePrograma = in.nextLine();
                    System.out.println("Digite o quantum do programa.");
                    quantum = in.nextInt();
                    System.out.println("Digite o tempo de chegada do programa.");
                    tempoChegada = in.nextInt();

                    RrSP prog1 = lerArquivo.Armazena(nomePrograma, quantum, tempoChegada);
                    programas.add(prog1);
                    break;

                case 2:
                    Execucao exe = new Execucao(programas);
                    break;

                case 0: System.exit(0);
                    break;
            }

        } while (op != 0);
    }
}

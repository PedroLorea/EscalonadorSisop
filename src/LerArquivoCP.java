import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LerArquivoCP {

    public ArrayList<String> programa = new ArrayList<String>();
    public Map<String, String> dados = new HashMap<String, String>();
    public Map<String, Integer> mapa = new HashMap<String, Integer>();

    public RrCP Armazena(String nomeArquivo, int quantum, int tempoChegada, int prioridade) {

        BufferedReader br = null;
        String linha = "";
        String espaço = " ";
        RrCP prog = null;

        try {
            br = new BufferedReader(new FileReader(nomeArquivo));

            while ((linha = br.readLine()) != null){
                System.out.println(linha);
                if(linha.equals(".endcode")){
                    linha = br.readLine();
                    System.out.println(linha);
                    linha = br.readLine();
                    mapa.put(".data", programa.size());
                    System.out.println(linha);
                }
                String[] separa = linha.split(espaço);
                if (separa[0].contains(":")) {
                    mapa.put(separa[0], programa.size()/2); //Adiociona os labels no mapa (HashMap, ou seja, label tal está no PC: 4)
                }
                if(separa[0].contains(":") || separa[0].contains(".")) continue; // Ex: .code, loop:

                programa.add(separa[2]); //Espaços no bloco de notas por isso é [2] e [3]
                programa.add(separa[3]);
            }
            data();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        prog = new RrCP(programa, dados, mapa, quantum, tempoChegada, nomeArquivo, prioridade);
        return prog;
    }

    public void data(){
        int a = mapa.get(".data");
        for(int i=a; i<programa.size(); i=i+2){
            dados.put(programa.get(i), programa.get(i+1));
        }
    }
}

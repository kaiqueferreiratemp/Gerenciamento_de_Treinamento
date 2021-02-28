import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EspacoPersister {
    public void limparEspaco() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Espacos.txt"));
        writer.close();
    }


    public void escritaEspaco(CadastroSalasEspacos espaco) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Espacos.txt", true));
        writer.write(espaco.nome+",");
        writer.write(String.valueOf(espaco.lotacao));
        writer.newLine();
        writer.close();
    }


    public ArrayList<CadastroSalasEspacos> leituraEspacos() throws IOException {
        ArrayList<CadastroSalasEspacos> _listaDeEspacos = new ArrayList<>();
        Scanner scan = new Scanner(new File("Espacos.txt"));
        scan.useDelimiter(Pattern.compile(","));
        while (scan.hasNext()) {
            CadastroSalasEspacos espaco = new CadastroSalasEspacos();
            espaco.setNome(scan.next());
            espaco.setLotacao(Integer.parseInt(scan.nextLine().substring(1)));
            _listaDeEspacos.add(espaco);
        }
        return _listaDeEspacos;
    }
}

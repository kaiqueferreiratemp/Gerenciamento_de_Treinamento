import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SalaPersister {
    public void limparSalas() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Salas.txt"));
        writer.close();
    }


    public void escritaSalas(CadastroSalasEspacos sala) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Salas.txt", true));
        writer.write(sala.nome+",");
        writer.write(String.valueOf(sala.lotacao));
        writer.newLine();
        writer.close();
    }


    public ArrayList<CadastroSalasEspacos> leituraSalas() throws IOException {
        ArrayList<CadastroSalasEspacos> _listaDeSalas = new ArrayList<>();
        Scanner scan = new Scanner(new File("Salas.txt"));
        scan.useDelimiter(Pattern.compile(","));
        while (scan.hasNext()) {
            CadastroSalasEspacos sala = new CadastroSalasEspacos();
            sala.setNome(scan.next());
            sala.setLotacao(Integer.parseInt(scan.nextLine().substring(1)));
            _listaDeSalas.add(sala);
        }

        return _listaDeSalas;
    }
}


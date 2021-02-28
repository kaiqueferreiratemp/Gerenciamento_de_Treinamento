import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PessoaPersister {
    public void limparPessoas() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Pessoas.txt"));
        writer.close();
    }


    public void escritaPessoas(CadastroPessoa pessoa) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Pessoas.txt", true));
        writer.write(pessoa.nome+",");
        writer.write(pessoa.sobrenome);
        writer.newLine();
        writer.close();
    }


    public ArrayList<CadastroPessoa> leituraPessoas() throws IOException {
        ArrayList<CadastroPessoa> _listaDePessoas = new ArrayList<>();
        Scanner scan = new Scanner(new File("Pessoas.txt"));
        scan.useDelimiter(Pattern.compile(","));
        while (scan.hasNext()) {
            CadastroPessoa pessoa = new CadastroPessoa();
            pessoa.setNome(scan.next());
            pessoa.setSobrenome(scan.nextLine().substring(1));
            _listaDePessoas.add(pessoa);
            Sistema.listaDePessoas.add(pessoa);
        }
        return _listaDePessoas;
    }
}

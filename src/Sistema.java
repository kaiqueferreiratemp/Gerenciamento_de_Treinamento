import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    public static List<List<String>> listaCompleta = new ArrayList<List<String>>();     //List which contains ALL information
    public static int[] posicaoEncontrada = new int[2];                                 //Variable that is returned when someone tries to access name/room/coffee break place
    public static PessoaPersister pessoaPersister = new PessoaPersister();              //Read/write people DB
    public static SalaPersister salaPersister = new SalaPersister();                    //Read/write room DB
    public static EspacoPersister espacoPersister = new EspacoPersister();              //Read/write coffee break place DB
    public static ArrayList<CadastroPessoa> listaDePessoas = new ArrayList<>();         //People list
    public static ArrayList<CadastroSalasEspacos> listaDeSalas = new ArrayList<>();     //Room list
    public static ArrayList<CadastroSalasEspacos> listaDeEspacos = new ArrayList<>();   //Coffee break place list
    public static int vagasDisponiveis =0;                                              //Available seats
    public static boolean desligar=false;                                               //Turn off system

    public static void main(String[] args) {//throws IOException {
        System.out.println();
        try { //try to read database, otherwise it will create a new one.
            listaDePessoas = pessoaPersister.leituraPessoas();
            listaDeSalas = salaPersister.leituraSalas();
            listaDeEspacos = espacoPersister.leituraEspacos();
            Functions.preencherListaCompleta();
        } catch (IOException e) { //clear all global lists
            listaDePessoas.clear();
            listaDeSalas.clear();
            listaDeEspacos.clear();
            try {   //create new database
                espacoPersister.limparEspaco();
                salaPersister.limparSalas();
                pessoaPersister.limparPessoas();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("Banco de dados nao carregado corretamente.");
        }
        Functions.preencherListaCompleta();
        while(!desligar)
        {
            scanear();
            Functions.preencherListaCompleta();
        }
        System.out.println("Nao e possivel fazer mais alteracoes.");
    }


    public static void scanear(){
        Scanner scan = new Scanner(System.in);

        System.out.println("1 - Cadastro de pessoas, com nome e sobrenome");
        System.out.println("2 - Cadastro das salas do evento, com nome e lotacao");
        System.out.println("3 - Cadastro dos espacos de café com lotacao");
        System.out.println("4 - Consulta de pessoa");
        System.out.println("5 - Consulta de cada sala e espaco");
        System.out.println("6 - Sair");
        System.out.println("Digite o numero da operacao que deseja realizar:");
        int opcao;
        Scanner scanName = new Scanner(System.in);
        String busca;

        if(scan.hasNextInt()) {
            opcao = scan.nextInt();
            switch (opcao) {
                case 1 -> {
                    Functions.verificarVagasDisponiveis();
                    if (vagasDisponiveis == 0) System.out.println("Não existem vagas nas salas/espacos cadastrados");
                    else {
                        System.out.println("1 - Cadastro de pessoas, com nome e sobrenome");
                        System.out.println("Favor digitar o Nome da pessoa:");

                        CadastroPessoa pessoa = new CadastroPessoa();
                        pessoa.setNome(scanName.nextLine());

                        System.out.println("Favor digitar o Sobrenome da pessoa:");
                        pessoa.setSobrenome(scanName.nextLine());

                        if (Functions.acharSalaPessoa(pessoa.getNome() + " " + pessoa.getSobrenome(), 0, true)) {
                            System.out.println("Pessoa ja cadastrada!");
                            break;
                        }

                        try {
                            pessoaPersister.escritaPessoas(pessoa);
                            System.out.println("Cadastro de \"" + pessoa.nome + " " + pessoa.sobrenome + "\" realizado com sucesso!");
                            listaDePessoas.add(pessoa);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                case 2 -> {
                    System.out.println("2 - Cadastro das salas do evento, com nome e lotacao");
                    System.out.println("Favor digitar o Nome da sala:");
                    CadastroSalasEspacos sala = new CadastroSalasEspacos();
                    sala.setNome(scanName.nextLine());
                    if (Functions.acharSala(sala.nome + " - periodo 1")) {
                        System.out.println("Sala ja cadastrada!");
                        break;
                    }
                    System.out.println("Favor digitar a lotacao maxima:");
                        if(scanName.hasNextInt())       sala.setLotacao(scanName.nextInt());
                      else {
                          System.out.println("Error: não foi informado um número inteiro!");
                          break;
                        }
                    if (Functions.LotacaoEValida(sala.lotacao)) {//lotacao válida
                        try {
                            salaPersister.escritaSalas(sala);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Cadastro da sala \"" + sala.nome + "\" com lotacao: " + sala.lotacao + ", realizado com sucesso!");
                        listaDeSalas.add(sala);
                        Functions.preencherListaCompleta();
                    } else
                        System.out.println("Lotacao invalida!");
                }
                case 3 -> {
                    System.out.println("3 - Cadastro dos espacos de café com lotacao");
                    System.out.println("Favor digitar o nome do Espaco de café:");
                    CadastroSalasEspacos espaco = new CadastroSalasEspacos();
                    espaco.setNome(scanName.nextLine());
                    if (Functions.acharSala(espaco.nome + " - periodo 1")) {
                        System.out.println("Espaco de cafe ja cadastrado!");
                        break;
                    }
                    System.out.println("Favor digitar a lotacao maxima:");
                    if(scanName.hasNextInt())       espaco.setLotacao(scanName.nextInt());
                    else {
                        System.out.println("Error: não foi informado um número inteiro!");
                        break;
                    }
                    try {
                        espacoPersister.escritaEspaco(espaco);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Cadastro do espaco \"" + espaco.nome + "\" com lotacao: " + espaco.lotacao + ", realizado com sucesso!");
                    listaDeEspacos.add(espaco);
                }
                case 4 -> {
                    Functions.preencherListaCompleta();
                    System.out.println("4 - Consulta de pessoa");
                    System.out.println("Favor digitar o Nome completo da pessoa:");
                    busca = scanName.nextLine();
                    if (Functions.acharSalaPessoa(busca, 0, true)) {
                        System.out.println(listaCompleta.get(posicaoEncontrada[0]).get(0));//saber sala periodo 01
                        Functions.acharSalaPessoa(busca, 0, false);
                        System.out.println(listaCompleta.get(posicaoEncontrada[0]).get(0));//saber cafe periodo 01
                        Functions.acharSalaPessoa(busca, 1, true);
                        System.out.println(listaCompleta.get(posicaoEncontrada[0]).get(0));//saber sala periodo 02
                        Functions.acharSalaPessoa(busca, 1, false);
                        System.out.println(listaCompleta.get(posicaoEncontrada[0]).get(0));//saber cafe periodo 02
                    } else System.out.println("Pessoa não encontrada!");
                }
                case 5 -> {
                    Functions.preencherListaCompleta();
                    System.out.println("5 - Consulta de cada sala e espaco");
                    System.out.println("Favor digitar o Nome da sala/espaco:");
                    scanName = new Scanner(System.in);
                    busca = scanName.nextLine() + " - periodo 1";
                    if (Functions.acharSala(busca)) {
                        System.out.println("Periodo 1:");
                        for (int i = 1; i < listaCompleta.get(posicaoEncontrada[0]).size(); i++) {
                            if (listaCompleta.get(posicaoEncontrada[0]).get(i) != null) {
                                System.out.println(listaCompleta.get(posicaoEncontrada[0]).get(i));
                            }
                        }
                        System.out.println("Periodo 2:");
                        for (int i = 1; i < listaCompleta.get(posicaoEncontrada[0] + 1).size(); i++) {
                            if (listaCompleta.get(posicaoEncontrada[0] + 1).get(i) != null) {
                                System.out.println(listaCompleta.get(posicaoEncontrada[0] + 1).get(i));
                            }
                        }
                    } else System.out.println("Sala não encontrada!");
                }
                case 6 -> {
                    desligar = true;
                    Functions.preencherListaCompleta();
                    System.out.println("Lista Completa:");
                    System.out.println(listaCompleta);
                }
                default -> System.out.println("Opcao invalida");
            }
        }
        else{
            System.out.println("Opcao invalida");
        }
    }
}

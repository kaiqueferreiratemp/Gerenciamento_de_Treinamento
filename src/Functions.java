import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.ceil;

public class Functions {
    public static boolean LotacaoEValida(int lotacao){
        /**
         * @brief   Check if capacity for new room is valid.
         * @param   lotacao          capacity given
         * @return  true if lotacao is valid
         */
        int _menorSala = menorSala();
        if (_menorSala>lotacao)     _menorSala=lotacao;
        int _qtdVagasTotalSalas=_menorSala;
        for (int i = 0; i < Sistema.listaDeSalas.size(); i++) {
            if (Sistema.listaDeSalas.get(i).lotacao > _menorSala)
                _qtdVagasTotalSalas += _menorSala + 1; //Max difference between rooms is 1
            else
                _qtdVagasTotalSalas += _menorSala;
        }
        return _qtdVagasTotalSalas >= Sistema.listaDePessoas.size();
    }


    public static int menorSala(){
        /**
         * @brief   Check which is the smallest room
         * @return  smallest room capacity
         */
        if(Sistema.listaDePessoas.size()!=0) {
            int _menorSala = Sistema.listaDeSalas.get(0).lotacao;
            for (int i = 0; i < Sistema.listaDeSalas.size(); i++) {
                if (_menorSala > Sistema.listaDeSalas.get(i).lotacao) _menorSala = Sistema.listaDeSalas.get(i).lotacao;
            }
            return _menorSala;
        }
        return 0;//Não existe sala cadastrada
    }


    public static void verificarVagasDisponiveis(){
        /**
         * @brief   Check how many people still can be registered (update variable "vagasDisponiveis")
         */
        int _qtdTotalSalas=0, _qtdTotalEspacos=0;
        int _menorSala;
        if (Sistema.listaDeSalas.size()!=0){
            _menorSala = menorSala();

            for (int i = 0; i < Sistema.listaDeSalas.size(); i++) {
                if (Sistema.listaDeSalas.get(i).lotacao > _menorSala)
                    _qtdTotalSalas += _menorSala + 1; //A diferença entre as salas é de no máximo 1
                else
                    _qtdTotalSalas += _menorSala;
            }
            for (int i = 0; i < Sistema.listaDeEspacos.size(); i++){
                _qtdTotalEspacos+=Sistema.listaDeEspacos.get(i).lotacao;
            }
            if (_qtdTotalSalas-_qtdTotalEspacos>0)            Sistema.vagasDisponiveis = _qtdTotalEspacos;
            else                                              Sistema.vagasDisponiveis = _qtdTotalSalas;
            Sistema.vagasDisponiveis -= Sistema.listaDePessoas.size();

            System.out.println("Quantidade de vagas disponiveis: " + Sistema.vagasDisponiveis);
        }
        else            System.out.println("Quantidade de vagas disponiveis: 0");

    }


    public static void preencherListaCompleta(){
        /**
         * @brief   update variable "listaCompleta"
         */
        preencherSalas();
        preencherEspacos();
    }


    public static void preencherSalas(){
        /**
         * @brief   put room list and people list in the arraylist "listaCompleta"
         */
        Sistema.listaCompleta.clear();
        for (int i=0;i<Sistema.listaDeSalas.size();i++) {
            Sistema.listaCompleta.add(new ArrayList<String>(Arrays.asList(Sistema.listaDeSalas.get(i).getNome() + " - periodo 1"))); //criar nova linha para a sala.
            Sistema.listaCompleta.add(new ArrayList<String>(Arrays.asList(Sistema.listaDeSalas.get(i).getNome() + " - periodo 2"))); //criar nova linha para a sala.
        }
        int _pessoasParaAlocar = Sistema.listaDePessoas.size();
        for (int j=0;j< ceil((float)Sistema.listaDePessoas.size()/ Sistema.listaDeSalas.size());j++) {
            for (int i=0;i<Sistema.listaDeSalas.size();i++){
                if(j < Sistema.listaDeSalas.get(i).getLotacao() && (_pessoasParaAlocar>0)){
                    Sistema.listaCompleta.get(i*2).add(j+1,Sistema.listaDePessoas.get(Sistema.listaDePessoas.size()-_pessoasParaAlocar).getNome()+" "+Sistema.listaDePessoas.get(Sistema.listaDePessoas.size()-_pessoasParaAlocar).getSobrenome());
                    Sistema.listaCompleta.get(i*2+1).add(j+1,Sistema.listaDePessoas.get(Sistema.listaDePessoas.size()-_pessoasParaAlocar).getNome()+" "+Sistema.listaDePessoas.get(Sistema.listaDePessoas.size()-_pessoasParaAlocar).getSobrenome());
                    _pessoasParaAlocar--;
                }
                else
                {
                    Sistema.listaCompleta.get(i*2).add(null);
                    Sistema.listaCompleta.get(i*2+1).add(null);
                }
            }
        }
        //SHIFT
        String aux;
        for (int j=0;j< ceil((float)Sistema.listaDePessoas.size()/ Sistema.listaDeSalas.size()/2);j++) {
            aux = Sistema.listaCompleta.get(1).get(j+1);
            for (int i=0;i<Sistema.listaDeSalas.size()-1;i++){
                Sistema.listaCompleta.get(i*2+1).set(j+1, Sistema.listaCompleta.get(i*2+2).get(j+1));
            }
            Sistema.listaCompleta.get((Sistema.listaDeSalas.size()-1)*2+1).set(j+1,aux);
        }
    }


    public static void preencherEspacos(){
        /**
         * @brief   put coffee break place list and people list in the arraylist "listaCompleta"
         */
        for (int i=0;i<Sistema.listaDeEspacos.size();i++) {
            Sistema.listaCompleta.add(new ArrayList<String>(Collections.singletonList(Sistema.listaDeEspacos.get(i).getNome() + " - periodo 1"))); //criar nova linha para a sala.
            Sistema.listaCompleta.add(new ArrayList<String>(Collections.singletonList(Sistema.listaDeEspacos.get(i).getNome() + " - periodo 2"))); //criar nova linha para a sala.
        }
        int _pessoasParaAlocar = Sistema.listaDePessoas.size();

        int _limiteEspacoMax=0;
        for (int i=0;i<Sistema.listaDeEspacos.size();i++) {
            if (_limiteEspacoMax<Sistema.listaDeEspacos.get(i).lotacao)                _limiteEspacoMax=Sistema.listaDeEspacos.get(i).lotacao;
        }

        for (int i=0;i<_limiteEspacoMax;i++)
        {
            for (int j=0;j<Sistema.listaDeEspacos.size();j++)
            {
                if (_pessoasParaAlocar>0 && i<Sistema.listaDeEspacos.get(j).lotacao) {
                    Sistema.listaCompleta.get((Sistema.listaDeSalas.size()+j)*2).add(i+1,Sistema.listaDePessoas.get(Sistema.listaDePessoas.size()-_pessoasParaAlocar).getNome()+" "+Sistema.listaDePessoas.get(Sistema.listaDePessoas.size()-_pessoasParaAlocar).getSobrenome());
                    Sistema.listaCompleta.get((Sistema.listaDeSalas.size()+j)*2+1).add(i+1,Sistema.listaDePessoas.get(Sistema.listaDePessoas.size()-_pessoasParaAlocar).getNome()+" "+Sistema.listaDePessoas.get(Sistema.listaDePessoas.size()-_pessoasParaAlocar).getSobrenome());
                    _pessoasParaAlocar--;
                }
                else {
                    Sistema.listaCompleta.get((Sistema.listaDeSalas.size()+j)*2).add(null);
                    Sistema.listaCompleta.get((Sistema.listaDeSalas.size()+j)*2+1).add(null);
                }
            }
        }
    }


    public static boolean acharSala(String busca)
    {
        /**
         * @brief   find the position of a room in "listaCompleta"
         * @param   busca          string that is being searched
         * @return  true if finds the given string.
         */
        if (Sistema.listaCompleta.size()==0) {
            return false;
        }
        Sistema.posicaoEncontrada[0]= -1; Sistema.posicaoEncontrada[1]= -1;
        for (int i=0;i< Sistema.listaCompleta.size()/2;i++){
            if (Sistema.listaCompleta.get(i*2).get(0).equals(busca)) {
                Sistema.posicaoEncontrada[0]=i*2;
                Sistema.posicaoEncontrada[1]=0;
                return true;
            }
        }
        return false;
    }


    public static boolean acharSalaPessoa(String busca, int periodo, boolean room) {
        /**
         * @brief   find which room/coffee break place a person will be
         * @param   busca          person that is being searched
         * @param   periodo        '0' for first period and '1' second period
         * @return  true if finds the given name and period.
         */

        Sistema.posicaoEncontrada[0]= -1; Sistema.posicaoEncontrada[1]= -1;
        for (int j=0;j< ceil((float)Sistema.listaDePessoas.size()/ Sistema.listaDeSalas.size());j++) {
            for (int i=0;i<Sistema.listaDeSalas.size()+Sistema.listaDeEspacos.size();i++){
                if (Sistema.listaCompleta.get(i*2+periodo).get(j+1)!=null) {
                    if (Sistema.listaCompleta.get(i * 2 + periodo).get(j + 1).equals(busca)) {
                        Sistema.posicaoEncontrada[0] = i * 2 + periodo;
                        Sistema.posicaoEncontrada[1] = j + 1;
                        if (room)                        return true;
                    }
                }
            }
        }
        return false;
    }
}

public class CadastroSalasEspacos {
    String nome;
    int lotacao;

    public String getNome() {
        return nome;
    }
    public int getLotacao() {
        return lotacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public String toString() {
        String s = "";
        s += "Nome:" + nome;
        s += " - lotacao:" + lotacao;
        return s;
    }
}

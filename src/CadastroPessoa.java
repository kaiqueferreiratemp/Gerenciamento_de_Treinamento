public class CadastroPessoa {
    String nome;
    String sobrenome;

    public String getNome() {
        return nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    //    @Override
    public String toString() {
        String s = "";
        s += "Nome:" + nome;
        s += " - Sobrenome:" + sobrenome;
        return s;
    }
}

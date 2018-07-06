package Entidades;

public class Cliente {

    private String nome;
    private String cpf;
    private int idade;
    private String sexo;

    public Cliente(String nome, String cpf, int idade, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}

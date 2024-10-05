package br.inatel.Model;

public class Funcionario {
    private String nome;
    private String email;
    private String cargo;

    public Funcionario(String nome, String email, String cargo){
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() { return cargo; }
}

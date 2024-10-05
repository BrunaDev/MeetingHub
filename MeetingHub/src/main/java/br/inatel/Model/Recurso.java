package br.inatel.Model;

public class Recurso {
    private String nome;
    private String quantidade;

    public Recurso(String nome, String quantidade){
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getQuantidade() {
        return quantidade;
    }
}

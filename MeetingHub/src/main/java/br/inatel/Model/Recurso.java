package br.inatel.Model;

public class Recurso {
    private String nome;
    private int quantidade;

    public Recurso(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}

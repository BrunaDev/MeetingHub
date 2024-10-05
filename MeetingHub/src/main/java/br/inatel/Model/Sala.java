package br.inatel.Model;

public class Sala {
    private String nome;
    private String capacidade;
    private String recursos_disponiveis;

    public Sala(String nome, String capacidade, String recursos_disponiveis){
        this.nome = nome;
        this.capacidade = capacidade;
        this.recursos_disponiveis = recursos_disponiveis;
    }

    public String getNome() {
        return nome;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public String getRecursos() {
        return recursos_disponiveis;
    }
}

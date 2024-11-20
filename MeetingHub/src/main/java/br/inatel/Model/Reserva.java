package br.inatel.Model;

public class Reserva {
    private String data_hora_inicio;
    private String data_hora_fim;
    private int Sala_id;
    private String emailFunc;

    public Reserva(String data_hora_inicio, String data_hora_fim, int Sala_id, String emailFunc) {
        this.data_hora_inicio = data_hora_inicio;
        this.data_hora_fim = data_hora_fim;
        this.Sala_id = Sala_id;
        this.emailFunc = emailFunc;
    }

    public String getDataHoraInicio() {
        return data_hora_inicio;
    }

    public String getDataHoraFim() { return data_hora_fim; }

    public int getSalaID() { return Sala_id; }
    public String getEmail() { return emailFunc; }
}

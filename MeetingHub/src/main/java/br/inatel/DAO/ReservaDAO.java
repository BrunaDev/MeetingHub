package br.inatel.DAO;

import br.inatel.Model.Funcionario;
import br.inatel.Model.Reserva;
import java.sql.SQLException;

// seria bom armazenar o email de quem reservou a sala
public class ReservaDAO extends ConnectionDAO {

    boolean sucesso = false;

    //------------------------INSERIR NOVA RESERVA----------------------------
    public boolean insertReserva(Reserva reserva) {
        connectToDB();
        String sql = "INSERT INTO reserva (data_hora_inicio, data_hora_fim, Sala_id, emailFunc) VALUES (?,?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, reserva.getDataHoraInicio());
            pst.setString(2, reserva.getDataHoraFim());
            pst.setInt(3, reserva.getSalaID());
            pst.setString(4, reserva.getEmail());
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexão = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão = " + e.getMessage());
            }
        }
        return sucesso;
    }

    //------------------------SELECIONAR RESERVAS POR SALA----------------------------
    public boolean selectReservasPorSala(int salaId) {
        connectToDB();
        boolean verificado = false;

        String sql = "SELECT * FROM reserva WHERE Sala_id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, salaId);
            rs = pst.executeQuery();

            while (rs.next()) {
                Reserva reservaTemp = new Reserva(
                        rs.getString("data_hora_inicio"),
                        rs.getString("data_hora_fim"),
                        rs.getInt("Sala_id"),
                        rs.getString("emailFunc")
                );
                if (reservaTemp.getSalaID() == salaId) {
                    verificado = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão = " + ex.getMessage());
            }
        }
        return verificado;
    }

    //------------------------ATUALIZAR RESERVAS----------------------------
    public boolean updateHorarioReserva(int reservaId, String novoInicio, String novoFim) {
        connectToDB();
        String sql = "UPDATE reserva SET data_hora_inicio = ?, data_hora_fim = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoInicio);
            pst.setString(2, novoFim);
            pst.setInt(3, reservaId);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar horário da reserva = " + ex.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão = " + ex.getMessage());
            }
        }
    }

    //------------------------SELECIONAR TODAS AS RESERVAS----------------------------
    public void selectAllReservas() {
        connectToDB();
        String sql = "SELECT * FROM reserva";

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Sala: " + rs.getInt("Sala_id") +
                        ", Início: " + rs.getString("data_hora_inicio") +
                        ", Fim: " + rs.getString("data_hora_fim") +
                        ", Email: " + rs.getString("emailFunc"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar reservas = " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão = " + ex.getMessage());
            }
        }
    }
}

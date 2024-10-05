package br.inatel.DAO;

import br.inatel.Model.Sala;
import java.sql.SQLException;

public class SalaDAO extends ConnectionDAO {

    boolean sucesso = false;

    //------------------------INSERIR NOVA SALA NO DATABASE----------------------------
    public boolean insertSala(Sala sala) {
        connectToDB();

        String sql = "INSERT INTO Sala (nome, capacidade, recursos_disponiveis) VALUES (?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, sala.getNome());
            pst.setString(2, sala.getCapacidade());
            pst.setString(3, sala.getRecursos());
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

    //------------------------SELECIONAR SALA POR NOME----------------------------
    public Sala selectSalaPorNome(String nomeSala) {
        connectToDB();
        Sala sala = null;

        String sql = "SELECT * FROM Sala WHERE nome = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeSala);
            rs = pst.executeQuery();

            if (rs.next()) {
                sala = new Sala(
                        rs.getString("nome"),
                        rs.getString("capacidade"),
                        rs.getString("recursos_disponiveis")
                );
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
        return sala;
    }
}

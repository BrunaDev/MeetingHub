package br.inatel.DAO;

import br.inatel.Model.Sala;
import java.sql.SQLException;

public class SalaDAO extends ConnectionDAO {

    //------------------------SELECIONAR SALA POR NOME----------------------------
    public Sala selectSalaPorNome(String nomeSala) {
        connectToDB();
        Sala sala = null;

        String sql = "SELECT * FROM sala WHERE nome = ?";

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

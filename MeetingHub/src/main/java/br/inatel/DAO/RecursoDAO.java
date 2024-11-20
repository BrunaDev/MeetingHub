package br.inatel.DAO;

import br.inatel.Model.Recurso;
import java.sql.SQLException;

public class RecursoDAO extends ConnectionDAO {

    //------------------------SELECIONAR TODOS OS RECURSOS DO DATABASE----------------------------
    public void listRecurso() {
        connectToDB();
        String sql = "SELECT * FROM recurso";

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Nome: " + rs.getString("nome") +
                        ", Quantidade: " + rs.getInt("quantidade") );
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar recursos = " + ex.getMessage());
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

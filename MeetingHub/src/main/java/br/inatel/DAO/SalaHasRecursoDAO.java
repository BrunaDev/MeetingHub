package br.inatel.DAO;
import java.sql.*;

import br.inatel.DAO.ConnectionDAO;

public class SalaHasRecursoDAO extends ConnectionDAO {

    //------------------------INSERIR NOVA RELAÇÂO NA TABELA 1:M DE SALA E RECURSOS----------------------------
    public boolean insertSalaRecurso(int salaId, int recursoId, String nome) {
        connectToDB();
        String sql = "INSERT INTO sala_has_recurso (sala_Id, recurso_Id, nome) VALUES (?, ?, ?)";

        boolean sucesso;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, salaId);
            pst.setInt(2, recursoId);
            pst.setString(3, nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexão = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                if (con != null) con.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão = " + e.getMessage());
            }
        }
        return sucesso;
    }

    //------------------------DELETAR RELAÇÂO ESPECIFICA NA TABELA N:M DE RECURSOS E SALAS----------------------------
    public boolean deleteSalaRecurso(int salaId, int recursoId) {
        connectToDB();
        boolean sucesso;
        String sql = "DELETE FROM sala_has_recurso WHERE Sala_Id=? AND Recurso_Id=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, salaId);
            pst.setInt(2, recursoId);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                if (con != null) con.close();
                if (pst != null) pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão = " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //------------------------BUSCAR RECURSOS----------------------------
    public int[] selectRecursosPorSala(int salaId) {
        int[] idRecursos = new int[30];
        int i = 0;
        connectToDB();

        String sql = "SELECT recurso_Id FROM sala_has_recurso WHERE sala_Id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, salaId);
            rs = pst.executeQuery();

            while (rs.next()) {
                idRecursos[i] = rs.getInt("recurso_Id");
                i++;
            }

        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            try {
                if (con != null) con.close();
                if (pst != null) pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão = " + ex.getMessage());
            }
        }
        return idRecursos;
    }
}

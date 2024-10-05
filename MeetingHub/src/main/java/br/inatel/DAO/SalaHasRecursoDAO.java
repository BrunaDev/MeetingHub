package br.inatel.DAO;
import java.sql.*;

import br.inatel.DAO.ConnectionDAO;

public class SalaHasRecursoDAO extends ConnectionDAO {

    //------------------------INSERIR NOVA RELAÇÂO NA TABELA 1:M DE SALA E RECURSOS----------------------------
    public boolean insertSalaRecurso(int Sala_Id,int Recurso_Id) {

        connectToDB();
        String sql = "INSERT INTO pedido_has_livro (Sala_Id,Recurso_Id) values (?,?)";

        boolean sucesso;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Sala_Id);
            pst.setInt(2, Recurso_Id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexao  = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexao " + e.getMessage());
            }
        }
        return sucesso;
    }

    //------------------------DELETAR RELAÇÂO ESPECIFICA NA TABELA N:M DE RECURSOS E SALAS----------------------------
    public boolean deleteLivroFromPedido(int Id_Livro,int Id_Pedido) {

        connectToDB();
        boolean verifica;
        String sql = "DELETE FROM pedido_has_livro WHERE Sala_Id=? AND Recurso_Id=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Id_Livro);
            pst.setInt(2, Id_Pedido);
            pst.execute();
            verifica = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            verifica = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return verifica;
    }

    //------------------------BUSCAR RECURSOS QUE ESTÃO SENDO USADOS NO DATABASE----------------------------
    public int[] selectLivrosPedido(int idPedido) {

        int[] idLivros = new int[30];
        int i = 0;

        connectToDB();

        String sql = "SELECT Sala_Id FROM pedido_has_livro WHERE Recurso_Id = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, idPedido);
            rs = pst.executeQuery();

            while (rs.next()) {
                Livro livroTemp = new Livro(rs.getInt("Sala_Id"));
                idLivros[i] =  livroTemp.getId();
                i += 1;
            }

        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return idLivros;
    }
}

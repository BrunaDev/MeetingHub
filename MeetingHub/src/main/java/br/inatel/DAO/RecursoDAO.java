package br.inatel.DAO;

import br.inatel.Model.Recurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecursoDAO extends ConnectionDAO {

    private Connection con; // Variável para a conexão
    private PreparedStatement pst; // Variável para o PreparedStatement
    private ResultSet rs; // Variável para o ResultSet
    private boolean sucesso = false;

    //------------------------SELECIONAR RECURSOS NO DATABASE----------------------------
    public boolean selectRecurso(String nome) {
        boolean verificado = false;
        String sql = "SELECT * FROM Recurso WHERE nome = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();

            while (rs.next()) {
                Recurso recursoTemp = new Recurso(rs.getString("nome"), rs.getString("quantidade"));
                if (recursoTemp.getNome().equals(nome)) {
                    verificado = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            closeResources(); // Fechar recursos
        }
        return verificado;
    }

    //------------------------SELECIONAR NOME DE FUNCIONARIO QUE SOLICITOU O RECURSO NO DATABASE----------------------------
    public String selectFuncionarioNome(String nomeFuncionario) {
        String nome = null;
        String sql = "SELECT * FROM Funcionario WHERE nome = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeFuncionario);
            rs = pst.executeQuery();

            if (rs.next()) {
                nome = rs.getString("nome");
            }

        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            closeResources(); // Fechar recursos
        }
        return nome;
    }

    //------------------------INSERIR RECURSO NO DATABASE----------------------------
    public boolean insertRecurso(Recurso recurso) {
        String sql = "INSERT INTO Recurso (nome, quantidade) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, recurso.getNome());
            pst.setString(2, recurso.getQuantidade());

            pst.execute();
            System.out.println("Recurso inserido com sucesso!");
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir recurso: " + e.getMessage());
            sucesso = false;
        } finally {
            closeResources(); // Fechar recursos
        }
        return sucesso;
    }

    // Método para fechar recursos após uso
    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão = " + ex.getMessage());
        }
    }
}
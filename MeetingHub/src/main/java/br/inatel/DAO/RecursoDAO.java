package br.inatel.DAO;

import br.inatel.Model.Recurso;
import java.sql.SQLException;

public class RecursoDAO extends ConnectionDAO {

    boolean sucesso = false;

    //------------------------INSERIR NOVO FUNCIONARIO NO DATABASE----------------------------
    public boolean insertRecurso(Recurso recurso) {
        connectToDB();

        String sql = "INSERT INTO Funcionario (nome, email, cargo) VALUES (?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, recurso.getNome());
            pst.setString(2, recurso.getQuantidade());
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

    //------------------------SELECIONAR RECURSOS NO DATABASE----------------------------
    public boolean selectRecurso(String nome) {

        connectToDB();
        boolean verificado = false;

        String sql = "SELECT * FROM Funcionario WHERE cargo = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();

            while (rs.next()) {
                Recurso funcionarioTemp = new Recurso(rs.getString("nome"), rs.getString("quantidade"));
                if (funcionarioTemp.getNome().equals(nome)) {
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

    //------------------------SELECIONAR NOME DE FUNCIONARIO QUE SOLICITOU O RECURSO NO DATABASE----------------------------
    public String selectFuncionarioNome(String nomeFuncionario) {

        connectToDB();
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
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão = " + ex.getMessage());
            }
        }
        return nome;
    }
}

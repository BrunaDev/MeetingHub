package br.inatel.DAO;

import br.inatel.Model.Funcionario;
import java.sql.SQLException;

public class FuncionarioDAO extends ConnectionDAO {

    boolean sucesso = false;

    //------------------------INSERIR NOVO FUNCIONARIO NO DATABASE----------------------------
    public boolean insertFuncionario(Funcionario funcionario) {
        connectToDB();

        String sql = "INSERT INTO funcionario (nome, email, cargo) VALUES (?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getEmail());
            pst.setString(3, funcionario.getCargo());
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

    //------------------------BUSCAR FUNCIONARIO NO DATABASE----------------------------
    // Erro: Erro de conexão = Unknown column 'email' in 'field list'
    public boolean selectFuncionario(String emailFuncionario) {
        connectToDB();
        String sql = "SELECT * FROM funcionario WHERE email = ?";

        boolean exist;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, emailFuncionario);
            pst.execute();
            exist = true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexão = " + ex.getMessage());
            exist = false;
        } finally {
            try {
                if (con != null) con.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão = " + e.getMessage());
            }
        }
        return exist;
    }
}
package br.inatel.DAO;

import br.inatel.Model.Funcionario;
import java.sql.SQLException;

public class FuncionarioDAO extends ConnectionDAO {

    boolean sucesso = false;

    //------------------------INSERIR NOVO FUNCIONARIO NO DATABASE----------------------------
    public boolean insertFuncionario(Funcionario funcionario) {
        connectToDB();

        String sql = "INSERT INTO Funcionario (nome, email, cargo) VALUES (?,?,?)";

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

    //------------------------SELECIONAR FUNCIONARIOS POR CARGO----------------------------
    public boolean selectFuncionarioCargo(String cargo) {
        connectToDB();
        boolean verificado = false;

        String sql = "SELECT * FROM Funcionario WHERE cargo = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cargo);
            rs = pst.executeQuery();

            while (rs.next()) {
                Funcionario funcionarioTemp = new Funcionario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cargo")
                );
                if (funcionarioTemp.getCargo().equals(cargo)) {
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
}

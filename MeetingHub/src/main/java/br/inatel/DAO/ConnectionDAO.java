package br.inatel.DAO;

import java.sql.*;

public class ConnectionDAO {

    Connection con; //conexão
    Statement st; //declaração(query) preparada - código em sql
    ResultSet rs; //resposta do banco
    PreparedStatement pst; //declaração(query) preparada - código em sql

    static final String database = "meetinghub";
    static final String user = "root";
    static final String password = "root";
    static final String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    public boolean check = false;

    public void connectToDB(){
        try{
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
}

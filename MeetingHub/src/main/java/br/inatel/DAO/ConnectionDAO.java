package br.inatel.DAO;

import java.sql.*;

public abstract class ConnectionDAO {

    Connection con; //conexão
    Statement st; //declaração(query) preparada - código em sql
    ResultSet rs; //resposta do banco
    PreparedStatement pst; //declaração(query) preparada - código em sql

    static final String database = "mysql"; // nome do banco de dados
    static final String user = "root";
    static final String password = "Root";
    static final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    public boolean check = false;

    public void connectToDB(){
        try{
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Banco conectado!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
}

package br.edu.ifba.inf008.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        //conexão com host / porta / nome do banco / usuario / senha 
        String url = "jdbc:mariadb://localhost:3307:3306/bookstore";
        String user = "root";                              
        String password = "root";                            

        return DriverManager.getConnection(url, user, password);
    }
}
package br.com.alura.livraria.factory; 

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    
    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/livraria";
        String user = "root";
        String pass = "root";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }

    }
}

package com.projeto.hospital.configBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private String url = "jdbc:mysql://localhost:3306/HOSPITAL";
    private String user = "root";
    private String pass = "";

    public Connection getConexao () throws SQLException {
       return DriverManager.getConnection(url, user, pass);
    }
}

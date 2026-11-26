package br.edu.ifsc.fln.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String HOST = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DBNAME = "ClinData";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL =
            "jdbc:mysql://" +
                    HOST + ":" +
                    PORT + "/" +
                    DBNAME;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}

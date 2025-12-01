package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

   
    private static final String HOST = "b8zd5cllzessethpk9hx-mysql.services.clever-cloud.com"; 
    private static final String DB   = "b8zd5cllzessethpk9hx"; // Tu Database Name
    private static final String USER = "u2fcoweigbhvwmgd";  // Tu User
    private static final String PASS = "S0Jg2R8tuLI6HC4a62CV";  // Tu Password
    
   
   private static final String URL = "jdbc:mysql://" + HOST + ":3306/" + DB + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static Connection getConexion() throws SQLException {
        try {
          
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de Conexión: " + e.getMessage());
            throw new SQLException(e);
        }
    }

}

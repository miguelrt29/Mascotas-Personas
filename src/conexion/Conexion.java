package conexion;

import java.sql.*;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/clinicaabc";
    private static final String USER = "root";
    private static final String PASS = ""; // pon tu contraseña si tienes

    public static Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

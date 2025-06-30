package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConexion {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ClinicaABC"; // Asegúrate que esta BD exista
        String usuario = "root";
        String contraseña = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("✅ Conexión exitosa a la base de datos.");
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ No se encontró el driver JDBC de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a la base de datos.");
            e.printStackTrace();
        }
    }
}

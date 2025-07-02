package procesos;

import javax.swing.*;
import java.sql.*;
import conexion.Conexion;

public class ProcesosMascota extends ProcesosGenerales {

    private JTextField nombre, documentoDuenio, raza, sexo;
    private JTextArea area;

    public ProcesosMascota(JTextField nombre, JTextField documentoDuenio, JTextField raza, JTextField sexo, JTextArea area) {
        this.nombre = nombre;
        this.documentoDuenio = documentoDuenio;
        this.raza = raza;
        this.sexo = sexo;
        this.area = area;
    }

    @Override
    public void registrar() {
        try (Connection con = Conexion.getConexion()) {
            String validar = "SELECT * FROM Persona WHERE documento = ?";
            PreparedStatement psVer = con.prepareStatement(validar);
            psVer.setString(1, documentoDuenio.getText());
            ResultSet rs = psVer.executeQuery();

            if (!rs.next()) {
                area.setText("El dueño no está registrado.");
                return;
            }

            String sql = "INSERT INTO Mascota (nombre, documento_dueno, raza, sexo) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre.getText());
            ps.setString(2, documentoDuenio.getText());
            ps.setString(3, raza.getText());
            ps.setString(4, sexo.getText());
            ps.executeUpdate();

            area.setText("Mascota registrada correctamente.");
        } catch (SQLException e) {
            area.setText("Error al registrar: " + e.getMessage());
        }
    }

    @Override
    public void consultar() {
        try (Connection con = Conexion.getConexion()) {
            String sql = "SELECT * FROM Mascota WHERE documento_dueno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, documentoDuenio.getText());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombre.setText(rs.getString("nombre"));
                raza.setText(rs.getString("raza"));
                sexo.setText(rs.getString("sexo"));
                area.setText("Mascota encontrada.");
            } else {
                area.setText("Mascota no encontrada.");
            }
        } catch (SQLException e) {
            area.setText("Error al consultar: " + e.getMessage());
        }
    }

    @Override
    public void actualizar() {
        try (Connection con = Conexion.getConexion()) {
            String sql = "UPDATE Mascota SET nombre=?, raza=?, sexo=? WHERE documento_dueno=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre.getText());
            ps.setString(2, raza.getText());
            ps.setString(3, sexo.getText());
            ps.setString(4, documentoDuenio.getText());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                area.setText("Mascota actualizada correctamente.");
            } else {
                area.setText("Mascota no encontrada.");
            }
        } catch (SQLException e) {
            area.setText("Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar() {
        try (Connection con = Conexion.getConexion()) {
            String sql = "DELETE FROM Mascota WHERE documento_dueno=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, documentoDuenio.getText());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                nombre.setText("");
                raza.setText("");
                sexo.setText("");
                area.setText("Mascota eliminada correctamente.");
            } else {
                area.setText("Mascota no encontrada.");
            }
        } catch (SQLException e) {
            area.setText("Error al eliminar: " + e.getMessage());
        }
    }

    @Override
    public void consultarLista() {
        try (Connection con = Conexion.getConexion()) {
            String sql = "SELECT * FROM Mascota";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("Nombre: ").append(rs.getString("nombre"))
                  .append(", Raza: ").append(rs.getString("raza"))
                  .append(", Sexo: ").append(rs.getString("sexo"))
                  .append(", Dueño: ").append(rs.getString("documento_dueno"))
                  .append("\n");
            }

            if (sb.length() == 0) {
                area.setText("No hay mascotas registradas.");
            } else {
                area.setText(sb.toString());
            }
        } catch (SQLException e) {
            area.setText("Error al consultar lista: " + e.getMessage());
        }
    }
}

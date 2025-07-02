package procesos;

import javax.swing.*;
import java.sql.*;
import conexion.Conexion;

public class ProcesosPersona {
    private JTextField doc, nombre, telefono;
    private JTextArea area;

    public ProcesosPersona(JTextField doc, JTextField nombre, JTextField telefono, JTextArea area) {
        this.doc = doc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.area = area;
    }

    public void registrar() {
        String sql = "INSERT INTO Persona (documento, nombre, telefono) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doc.getText());
            ps.setString(2, nombre.getText());
            ps.setString(3, telefono.getText());
            ps.executeUpdate();
            area.setText("Persona registrada correctamente.");
        } catch (Exception e) {
            area.setText("Error al registrar: " + e.getMessage());
        }
    }

    public void consultar() {
        String sql = "SELECT * FROM Persona WHERE documento = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doc.getText());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nombre.setText(rs.getString("nombre"));
                telefono.setText(rs.getString("telefono"));
                area.setText("Datos encontrados:\nDocumento: " + doc.getText() +
                        "\nNombre: " + rs.getString("nombre") +
                        "\nTeléfono: " + rs.getString("telefono"));
            } else {
                area.setText("Persona no encontrada.");
            }
        } catch (Exception e) {
            area.setText("Error al consultar: " + e.getMessage());
        }
    }

    public void actualizar() {
        String sql = "UPDATE Persona SET nombre = ?, telefono = ? WHERE documento = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre.getText());
            ps.setString(2, telefono.getText());
            ps.setString(3, doc.getText());
            int r = ps.executeUpdate();
            if (r > 0) {
                area.setText("Persona actualizada correctamente.");
            } else {
                area.setText("No se encontró la persona para actualizar.");
            }
        } catch (Exception e) {
            area.setText("Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminar() {
        String sql = "DELETE FROM Persona WHERE documento = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doc.getText());
            int r = ps.executeUpdate();
            if (r > 0) {
                area.setText("Persona eliminada correctamente.");
            } else {
                area.setText("No se encontró la persona para eliminar.");
            }
        } catch (Exception e) {
            area.setText("Error al eliminar: " + e.getMessage());
        }
    }

    public void consultarLista() {
        String sql = "SELECT * FROM Persona";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("Documento: ").append(rs.getString("documento"))
                        .append(" - Nombre: ").append(rs.getString("nombre"))
                        .append(" - Tel: ").append(rs.getString("telefono"))
                        .append("\n");
            }

            if (sb.length() > 0) {
                area.setText(sb.toString());
            } else {
                area.setText("No hay personas registradas.");
            }

        } catch (Exception e) {
            area.setText("Error al consultar lista: " + e.getMessage());
        }
    }
}

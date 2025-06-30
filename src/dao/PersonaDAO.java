package dao;

import conexion.Conexion;
import vo.PersonaVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO extends EntidadDAO<PersonaVO> {

    @Override
    public boolean insertar(PersonaVO persona) {
        String sql = "INSERT INTO Persona VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, persona.getDocumento());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getTelefono());
            ps.setString(4, persona.getCorreo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public PersonaVO consultar(String documento) {
        String sql = "SELECT * FROM Persona WHERE documento=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PersonaVO(
                    rs.getString("documento"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("correo")
                );
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public boolean actualizar(PersonaVO persona) {
        String sql = "UPDATE Persona SET nombre=?, telefono=?, correo=? WHERE documento=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getTelefono());
            ps.setString(3, persona.getCorreo());
            ps.setString(4, persona.getDocumento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(String documento) {
        String sql = "DELETE FROM Persona WHERE documento=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, documento);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<PersonaVO> listar() {
        List<PersonaVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Persona";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                PersonaVO persona = new PersonaVO(
                    rs.getString("documento"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("correo")
                );
                lista.add(persona);
            }
        } catch (SQLException e) {
            return lista;
        }
        return lista;
    }
}

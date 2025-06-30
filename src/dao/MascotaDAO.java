package dao;

import conexion.Conexion;
import vo.MascotaVO;
import vo.PersonaVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO extends EntidadDAO<MascotaVO> {

    PersonaDAO personaDAO = new PersonaDAO();

    @Override
    public boolean insertar(MascotaVO mascota) {
        if (personaDAO.consultar(mascota.getDocumentoDueno()) == null) {
            System.out.println("El dueño no está registrado.");
            return false;
        }

        String sql = "INSERT INTO Mascota (nombre, especie, raza, edad, documento_dueno) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setString(3, mascota.getRaza());
            ps.setInt(4, mascota.getEdad());
            ps.setString(5, mascota.getDocumentoDueno());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public MascotaVO consultar(String idStr) {
        String sql = "SELECT * FROM Mascota WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idStr));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new MascotaVO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getInt("edad"),
                    rs.getString("documento_dueno")
                );
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public boolean actualizar(MascotaVO mascota) {
        String sql = "UPDATE Mascota SET nombre=?, especie=?, raza=?, edad=?, documento_dueno=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setString(3, mascota.getRaza());
            ps.setInt(4, mascota.getEdad());
            ps.setString(5, mascota.getDocumentoDueno());
            ps.setInt(6, mascota.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(String idStr) {
        String sql = "DELETE FROM Mascota WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idStr));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<MascotaVO> listar() {
        List<MascotaVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Mascota";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MascotaVO mascota = new MascotaVO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getInt("edad"),
                    rs.getString("documento_dueno")
                );
                lista.add(mascota);
            }
        } catch (SQLException e) {
            return lista;
        }
        return lista;
    }
}

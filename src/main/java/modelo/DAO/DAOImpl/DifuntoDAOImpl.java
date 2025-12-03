/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.DAO.DAOImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import modelo.DAO.DifuntoDAO;
import modelo.DTO.DifuntoDTO;
import modelo.DTO.EspacioDTO;
import modelo.Conexion;

public class DifuntoDAOImpl implements DifuntoDAO {

    // --- MÉTODO AUXILIAR PARA MAPEAR (Evita repetir código) ---
    private DifuntoDTO mapearDifunto(ResultSet rs) throws SQLException {
        DifuntoDTO difunto = new DifuntoDTO();
        difunto.setId(rs.getInt("id"));
        difunto.setNombre(rs.getString("nombre"));

        // Conversión segura de Fechas
        Date fechaNac = rs.getDate("fecha_nacimiento");
        if (fechaNac != null) {
            difunto.setFechaNacimiento(fechaNac.toLocalDate());
        }

        Date fechaDef = rs.getDate("fecha_defuncion");
        if (fechaDef != null) {
            difunto.setFechaDefuncion(fechaDef.toLocalDate());
        }

        Time horaEntierro = rs.getTime("hora_entierro");
        if (horaEntierro != null) {
            difunto.setHoraEntierro(horaEntierro.toLocalTime());
        }

        // Nuevos Campos
        difunto.setNacionalidad(rs.getString("nacionalidad"));
        difunto.setEstadoCivil(rs.getString("estado_civil"));
        difunto.setCausaDefuncion(rs.getString("causa_defuncion"));
        difunto.setReligion(rs.getString("religion"));
        difunto.setTipoCeremonia(rs.getString("tipo_ceremonia"));

        // Relación con Espacio (Si existe)
        if (rs.getObject("espacio_id") != null) {
            EspacioDTO espacio = new EspacioDTO();
            espacio.setId(rs.getInt("espacio_id"));
            // Estos alias (e_codigo, etc.) deben coincidir con la query SQL
            espacio.setCodigo(rs.getString("e_codigo"));
            espacio.setTipo(rs.getString("e_tipo"));
            espacio.setEstado(rs.getString("e_estado"));
            difunto.setEspacio(espacio);
        }

        return difunto;
    }

    @Override
    public List<DifuntoDTO> listarTodos() throws SQLException {
        List<DifuntoDTO> lista = new ArrayList<>();
        // Usamos LEFT JOIN para traer los datos del espacio en la misma consulta
        String sql = "SELECT d.*, e.id as e_id, e.codigo as e_codigo, e.tipo as e_tipo, e.estado as e_estado "
                   + "FROM difuntos d "
                   + "LEFT JOIN espacios e ON d.espacio_id = e.id "
                   + "ORDER BY d.id DESC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(mapearDifunto(rs));
            }
        }
        return lista;
    }

    @Override
    public DifuntoDTO buscarPorId(int id) throws SQLException {
        String sql = "SELECT d.*, e.id as e_id, e.codigo as e_codigo, e.tipo as e_tipo, e.estado as e_estado "
                   + "FROM difuntos d "
                   + "LEFT JOIN espacios e ON d.espacio_id = e.id "
                   + "WHERE d.id = ?";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearDifunto(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void insertar(DifuntoDTO difunto) throws SQLException {
        String sql = "INSERT INTO difuntos (nombre, fecha_nacimiento, fecha_defuncion, hora_entierro, espacio_id, "
                   + "nacionalidad, estado_civil, causa_defuncion, religion, tipo_ceremonia) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, difunto.getNombre());

            // Validaciones para evitar NullPointerException
            if (difunto.getFechaNacimiento() != null) {
                ps.setDate(2, Date.valueOf(difunto.getFechaNacimiento()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            if (difunto.getFechaDefuncion() != null) {
                ps.setDate(3, Date.valueOf(difunto.getFechaDefuncion()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            if (difunto.getHoraEntierro() != null) {
                ps.setTime(4, Time.valueOf(difunto.getHoraEntierro()));
            } else {
                ps.setNull(4, java.sql.Types.TIME);
            }

            if (difunto.getEspacio() != null && difunto.getEspacio().getId() > 0) {
                ps.setInt(5, difunto.getEspacio().getId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            // Nuevos campos (String)
            ps.setString(6, difunto.getNacionalidad());
            ps.setString(7, difunto.getEstadoCivil());
            ps.setString(8, difunto.getCausaDefuncion());
            ps.setString(9, difunto.getReligion());
            ps.setString(10, difunto.getTipoCeremonia());

            ps.executeUpdate();
        }
    }

    @Override
    public void actualizar(DifuntoDTO difunto) throws SQLException {
        // SQL actualizado con TODOS los campos nuevos
        String sql = "UPDATE difuntos SET nombre=?, fecha_nacimiento=?, fecha_defuncion=?, hora_entierro=?, espacio_id=?, "
                   + "nacionalidad=?, estado_civil=?, causa_defuncion=?, religion=?, tipo_ceremonia=? "
                   + "WHERE id=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // 1. Nombre
            ps.setString(1, difunto.getNombre());

            // 2. Fecha Nacimiento (con validación de nulo)
            if (difunto.getFechaNacimiento() != null) {
                ps.setDate(2, Date.valueOf(difunto.getFechaNacimiento()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            // 3. Fecha Defunción
            if (difunto.getFechaDefuncion() != null) {
                ps.setDate(3, Date.valueOf(difunto.getFechaDefuncion()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            // 4. Hora Entierro
            if (difunto.getHoraEntierro() != null) {
                ps.setTime(4, Time.valueOf(difunto.getHoraEntierro()));
            } else {
                ps.setNull(4, java.sql.Types.TIME);
            }

            // 5. Espacio ID
            if (difunto.getEspacio() != null && difunto.getEspacio().getId() > 0) {
                ps.setInt(5, difunto.getEspacio().getId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            // 6-10. Nuevos Campos de Texto
            ps.setString(6, difunto.getNacionalidad());
            ps.setString(7, difunto.getEstadoCivil());
            ps.setString(8, difunto.getCausaDefuncion());
            ps.setString(9, difunto.getReligion());
            ps.setString(10, difunto.getTipoCeremonia());
            
            // 11. ID para el WHERE (Vital para saber a quién actualizar)
            ps.setInt(11, difunto.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM difuntos WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    // Método buscarPorNombre (Opcional, si lo usas en el buscador)
    @Override
    public List<DifuntoDTO> buscarPorNombre(String termino) throws SQLException {
        List<DifuntoDTO> lista = new ArrayList<>();
        String sql = "SELECT d.*, e.id as e_id, e.codigo as e_codigo, e.tipo as e_tipo, e.estado as e_estado "
                   + "FROM difuntos d "
                   + "LEFT JOIN espacios e ON d.espacio_id = e.id "
                   + "WHERE d.nombre LIKE ?";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + termino + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearDifunto(rs));
                }
            }
        }
        return lista;
    }
}
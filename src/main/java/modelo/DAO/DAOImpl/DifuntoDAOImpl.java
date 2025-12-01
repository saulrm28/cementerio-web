/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.DAO.DAOImpl;

import modelo.DAO.DifuntoDAO;
import modelo.DTO.DifuntoDTO;
import modelo.Conexion; // Asegúrate de que la clase Conexion esté en el paquete 'modelo.util'

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import modelo.DTO.EspacioDTO;

public class DifuntoDAOImpl implements DifuntoDAO {

    // Método de ayuda para mapear el ResultSet
    private DifuntoDTO mapearDifunto(ResultSet rs) throws SQLException {
        DifuntoDTO difunto = new DifuntoDTO();
        difunto.setId(rs.getInt("id"));
        difunto.setNombre(rs.getString("nombre"));
        difunto.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        difunto.setFechaDefuncion(rs.getDate("fecha_defuncion").toLocalDate());
        
        // Verificamos si tiene hora de entierro
        if (rs.getTime("hora_entierro") != null) {
            difunto.setHoraEntierro(rs.getTime("hora_entierro").toLocalTime());
        }

        // Verificamos si tiene un espacio asociado (gracias al LEFT JOIN)
        if (rs.getObject("espacio_id") != null) {
            EspacioDTO espacio = new EspacioDTO();
            espacio.setId(rs.getInt("espacio_id"));
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
        // Query con LEFT JOIN para traer la info del espacio
        String sql = "SELECT d.*, e.id as espacio_id, e.codigo as e_codigo, e.tipo as e_tipo, e.estado as e_estado " +
                     "FROM difuntos d " +
                     "LEFT JOIN espacios e ON d.espacio_id = e.id " +
                     "ORDER BY d.id DESC";
        
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
        // Query con LEFT JOIN
        String sql = "SELECT d.*, e.id as espacio_id, e.codigo as e_codigo, e.tipo as e_tipo, e.estado as e_estado " +
                     "FROM difuntos d " +
                     "LEFT JOIN espacios e ON d.espacio_id = e.id " +
                     "WHERE d.id = ?";
        
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
        String sql = "INSERT INTO difuntos (nombre, fecha_nacimiento, fecha_defuncion, hora_entierro, espacio_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, difunto.getNombre());
            
            // --- VALIDACIÓN DE SEGURIDAD PARA FECHAS ---
            // Si la fecha no es nula, la convertimos. Si es nula, guardamos NULL en la BD.
            
            if (difunto.getFechaNacimiento() != null) {
                ps.setDate(2, java.sql.Date.valueOf(difunto.getFechaNacimiento()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            if (difunto.getFechaDefuncion() != null) {
                ps.setDate(3, java.sql.Date.valueOf(difunto.getFechaDefuncion()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            if (difunto.getHoraEntierro() != null) {
                ps.setTime(4, java.sql.Time.valueOf(difunto.getHoraEntierro()));
            } else {
                ps.setNull(4, java.sql.Types.TIME);
            }
            
            // Validación para el Espacio
            if (difunto.getEspacio() != null) {
                ps.setInt(5, difunto.getEspacio().getId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            
            ps.executeUpdate();
        }
    }

    @Override
    public void actualizar(DifuntoDTO difunto) throws SQLException {
        // Actualizamos todos los campos, incluyendo la hora y el ID del espacio
        String sql = "UPDATE difuntos SET nombre = ?, fecha_nacimiento = ?, fecha_defuncion = ?, hora_entierro = ?, espacio_id = ? WHERE id = ?";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, difunto.getNombre());
            ps.setDate(2, Date.valueOf(difunto.getFechaNacimiento()));
            ps.setDate(3, Date.valueOf(difunto.getFechaDefuncion()));
            ps.setTime(4, Time.valueOf(difunto.getHoraEntierro())); // Nueva columna
            ps.setInt(5, difunto.getEspacio().getId()); // Nuevo vínculo con espacio
            ps.setInt(6, difunto.getId()); // El ID para el WHERE
            
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

 @Override
    public List<DifuntoDTO> buscarPorNombre(String termino) throws SQLException {
        List<DifuntoDTO> lista = new ArrayList<>();
        
        // ACTUALIZADO: Usamos LEFT JOIN para traer también los datos del espacio
        // igual que en 'listarTodos' o 'buscarPorId'
        String sql = "SELECT d.*, e.id as espacio_id, e.codigo as e_codigo, e.tipo as e_tipo, e.estado as e_estado " +
                     "FROM difuntos d " +
                     "LEFT JOIN espacios e ON d.espacio_id = e.id " +
                     "WHERE d.nombre LIKE ?";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, "%" + termino + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Usamos el método 'mapearDifunto' que creamos arriba 
                    // para no repetir código y llenar bien el objeto
                    lista.add(mapearDifunto(rs));
                }
            }
        }
        return lista;
    }
}

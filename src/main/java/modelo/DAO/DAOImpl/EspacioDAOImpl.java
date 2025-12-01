/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Archivo: modelo/DAO/DAOImpl/EspacioDAOImpl.java
package modelo.DAO.DAOImpl;

import modelo.DAO.EspacioDAO;
import modelo.DTO.EspacioDTO;
import modelo.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspacioDAOImpl implements EspacioDAO {

    @Override
    public void insertar(EspacioDTO espacio) throws SQLException {
        String sql = "INSERT INTO espacios (codigo, tipo, estado) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, espacio.getCodigo());
            ps.setString(2, espacio.getTipo());
            ps.setString(3, espacio.getEstado());
            ps.executeUpdate();
        }
    }

    @Override
    public List<EspacioDTO> listarTodos() throws SQLException {
        List<EspacioDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM espacios ORDER BY codigo";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EspacioDTO espacio = new EspacioDTO();
                espacio.setId(rs.getInt("id"));
                espacio.setCodigo(rs.getString("codigo"));
                espacio.setTipo(rs.getString("tipo"));
                espacio.setEstado(rs.getString("estado"));
                lista.add(espacio);
            }
        }
        return lista;
    }
    @Override
    public EspacioDTO buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM espacios WHERE id = ?";
        EspacioDTO espacio = null;
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    espacio = new EspacioDTO();
                    espacio.setId(rs.getInt("id"));
                    espacio.setCodigo(rs.getString("codigo"));
                    espacio.setTipo(rs.getString("tipo"));
                    espacio.setEstado(rs.getString("estado"));
                }
            }
        }
        return espacio;
    }

    @Override
    public void actualizar(EspacioDTO espacio) throws SQLException {
        String sql = "UPDATE espacios SET codigo = ?, tipo = ?, estado = ? WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, espacio.getCodigo());
            ps.setString(2, espacio.getTipo());
            ps.setString(3, espacio.getEstado());
            ps.setInt(4, espacio.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM espacios WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}


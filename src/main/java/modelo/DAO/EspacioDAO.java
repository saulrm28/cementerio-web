/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Archivo: modelo/DAO/EspacioDAO.java
package modelo.DAO;

import modelo.DTO.EspacioDTO;
import java.sql.SQLException;
import java.util.List;

public interface EspacioDAO {

    void insertar(EspacioDTO espacio) throws SQLException;

    List<EspacioDTO> listarTodos() throws SQLException;

    EspacioDTO buscarPorId(int id) throws SQLException;

    void actualizar(EspacioDTO espacio) throws SQLException;

    void eliminar(int id) throws SQLException;
}

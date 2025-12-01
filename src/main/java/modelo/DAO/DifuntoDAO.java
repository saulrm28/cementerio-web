// Archivo: modelo/DAO/DifuntoDAO.java
package modelo.DAO;

import modelo.DTO.DifuntoDTO;
import java.sql.SQLException;
import java.util.List;

public interface DifuntoDAO {
    List<DifuntoDTO> listarTodos() throws SQLException;
    DifuntoDTO buscarPorId(int id) throws SQLException;
    void insertar(DifuntoDTO difunto) throws SQLException;
    void actualizar(DifuntoDTO difunto) throws SQLException;
    void eliminar(int id) throws SQLException;
    // NUEVO MÉTODO AÑADIDO
    List<DifuntoDTO> buscarPorNombre(String termino) throws SQLException;
}
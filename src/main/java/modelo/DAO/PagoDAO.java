package modelo.DAO;

import java.sql.SQLException;
import java.util.List;
import modelo.DTO.PagoDTO;

public interface PagoDAO {

    List<PagoDTO> listarPendientes() throws SQLException;

    void realizarPago(int id) throws SQLException;
}

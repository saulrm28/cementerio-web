package modelo.DAO.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.DAO.PagoDAO;
import modelo.DTO.PagoDTO;
import modelo.Conexion;


public class PagoDAOImpl implements PagoDAO {

    @Override
    public List<PagoDTO> listarPendientes() throws SQLException {
        List<PagoDTO> lista = new ArrayList<>();
        // Solo mostramos lo que NO se ha pagado aún
        String sql = "SELECT * FROM pagos WHERE estado = 'Pendiente' ORDER BY vencimiento ASC";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                PagoDTO p = new PagoDTO();
                p.setId(rs.getInt("id"));
                p.setConcepto(rs.getString("concepto"));
                p.setVencimiento(rs.getDate("vencimiento").toLocalDate());
                p.setMonto(rs.getDouble("monto"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public void realizarPago(int id) throws SQLException {
        // En lugar de borrar, actualizamos el estado a 'Pagado'
        String sql = "UPDATE pagos SET estado = 'Pagado' WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
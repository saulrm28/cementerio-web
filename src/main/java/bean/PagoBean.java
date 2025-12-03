/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import modelo.DAO.DAOImpl.PagoDAOImpl;
import modelo.DAO.PagoDAO;
import modelo.DTO.PagoDTO;

@Named
@ViewScoped
public class PagoBean implements Serializable {

    private PagoDAO pagoDAO;
    private List<PagoDTO> listaPagos;

    @PostConstruct
    public void init() {
        pagoDAO = (PagoDAO) new PagoDAOImpl();
        cargarPagos();
    }

    public void cargarPagos() {
        try {
            listaPagos = pagoDAO.listarPendientes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pagar(PagoDTO pago) {
        try {
            pagoDAO.realizarPago(pago.getId());
            cargarPagos(); // Recargamos la lista para que desaparezca el pagado
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getter
    public List<PagoDTO> getListaPagos() {
        return listaPagos;
    }
}
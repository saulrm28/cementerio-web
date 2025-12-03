/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import modelo.DAO.DAOImpl.DifuntoDAOImpl;
import modelo.DAO.DAOImpl.EspacioDAOImpl;
import modelo.DAO.DifuntoDAO;
import modelo.DAO.EspacioDAO;
import modelo.DTO.DifuntoDTO;
import modelo.DTO.EspacioDTO;

@Named
@ViewScoped
public class ModificarDifuntoBean implements Serializable {

    @Inject
    private Flash flash;

    private DifuntoDAO difuntoDAO;
    private EspacioDAO espacioDAO;

    private DifuntoDTO difunto;
    private List<EspacioDTO> listaEspaciosDisponibles;
    private int espacioSeleccionadoId;

    @PostConstruct
    public void init() {
        difuntoDAO = new DifuntoDAOImpl();
        espacioDAO = new EspacioDAOImpl();

        difunto = (DifuntoDTO) flash.get("difuntoSeleccionado");

        if (difunto != null) {

            if (difunto.getEspacio() != null) {
                this.espacioSeleccionadoId = difunto.getEspacio().getId();
            }
            cargarEspaciosParaEdicion();
        } else {

            difunto = new DifuntoDTO();
        }
    }

    private void cargarEspaciosParaEdicion() {
        try {

            listaEspaciosDisponibles = espacioDAO.listarTodos().stream()
                    .filter(e -> "Disponible".equals(e.getEstado()))
                    .collect(Collectors.toList());

            if (difunto.getEspacio() != null) {
                listaEspaciosDisponibles.add(0, difunto.getEspacio());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String actualizarDifunto() {
        try {

            EspacioDTO nuevoEspacio = new EspacioDTO();
            nuevoEspacio.setId(espacioSeleccionadoId);
            difunto.setEspacio(nuevoEspacio);

            difuntoDAO.actualizar(difunto);

            return "index?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DifuntoDTO getDifunto() {
        return difunto;
    }

    public void setDifunto(DifuntoDTO difunto) {
        this.difunto = difunto;
    }

    public List<EspacioDTO> getListaEspaciosDisponibles() {
        return listaEspaciosDisponibles;
    }

    public int getEspacioSeleccionadoId() {
        return espacioSeleccionadoId;
    }

    public void setEspacioSeleccionadoId(int espacioSeleccionadoId) {
        this.espacioSeleccionadoId = espacioSeleccionadoId;
    }
}

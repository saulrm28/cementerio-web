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
import java.time.LocalDate;
import modelo.DAO.DAOImpl.EspacioDAOImpl;
import modelo.DAO.EspacioDAO;
import modelo.DTO.EspacioDTO;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import modelo.DAO.DAOImpl.DifuntoDAOImpl;
import modelo.DAO.DifuntoDAO;
import modelo.DTO.DifuntoDTO;

@Named
@ViewScoped
public class DifuntoBean implements Serializable {

    private DifuntoDAO difuntoDAO;

    private List<DifuntoDTO> listaDifuntos;
    private DifuntoDTO nuevoDifunto;
    private DifuntoDTO difuntoSeleccionado;
    private LocalDate fechaNac;
    private LocalDate fechaDef;
    @Inject
    private Flash flash;

    private EspacioDAO espacioDAO;
    private List<EspacioDTO> listaEspaciosDisponibles;


    private LocalTime horaEntierro;
    private int espacioSeleccionadoId;

    @PostConstruct
    public void init() {
        difuntoDAO = new DifuntoDAOImpl();
        espacioDAO = new EspacioDAOImpl();
        cargarDifuntos();
        cargarEspaciosDisponibles();
        nuevoDifunto = new DifuntoDTO();
    }

    private void cargarEspaciosDisponibles() {
        try {
            listaEspaciosDisponibles = espacioDAO.listarTodos().stream()
                    .filter(e -> "Disponible".equals(e.getEstado()))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarDifuntos() {
        try {
            listaDifuntos = difuntoDAO.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
          }
    }

    public String agregarDifunto() {
        try {
            nuevoDifunto.setFechaNacimiento(fechaNac);
            nuevoDifunto.setFechaDefuncion(fechaDef);
            nuevoDifunto.setHoraEntierro(horaEntierro);  
            EspacioDTO esp = new EspacioDTO();
            esp.setId(espacioSeleccionadoId);
            nuevoDifunto.setEspacio(esp); 

            difuntoDAO.insertar(nuevoDifunto);

           
            nuevoDifunto = new DifuntoDTO();
            fechaNac = null;
            fechaDef = null;
            horaEntierro = null;
            espacioSeleccionadoId = 0;

            cargarDifuntos();
            cargarEspaciosDisponibles(); 

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String eliminarDifunto(int idDifunto) {
        try {
            difuntoDAO.eliminar(idDifunto);
            cargarDifuntos(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String prepararVista(int idDifunto, String vistaDestino) {
        try {
            DifuntoDTO difunto = difuntoDAO.buscarPorId(idDifunto);

        
            flash.put("difuntoSeleccionado", difunto);

           
            return vistaDestino + "?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public List<EspacioDTO> getListaEspaciosDisponibles() {
        return listaEspaciosDisponibles;
    }

    public LocalTime getHoraEntierro() {
        return horaEntierro;
    }

    public void setHoraEntierro(LocalTime horaEntierro) {
        this.horaEntierro = horaEntierro;
    }

    public int getEspacioSeleccionadoId() {
        return espacioSeleccionadoId;
    }

    public void setEspacioSeleccionadoId(int espacioSeleccionadoId) {
        this.espacioSeleccionadoId = espacioSeleccionadoId;
    }

    public List<DifuntoDTO> getListaDifuntos() {
        return listaDifuntos;
    }

    public void setListaDifuntos(List<DifuntoDTO> listaDifuntos) {
        this.listaDifuntos = listaDifuntos;
    }

    public DifuntoDTO getNuevoDifunto() {
        return nuevoDifunto;
    }

    public void setNuevoDifunto(DifuntoDTO nuevoDifunto) {
        this.nuevoDifunto = nuevoDifunto;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public LocalDate getFechaDef() {
        return fechaDef;
    }

    public void setFechaDef(LocalDate fechaDef) {
        this.fechaDef = fechaDef;
    }

    public DifuntoDTO getDifuntoSeleccionado() {
        return difuntoSeleccionado;
    }

    public void setDifuntoSeleccionado(DifuntoDTO difuntoSeleccionado) {
        this.difuntoSeleccionado = difuntoSeleccionado;
    }
}

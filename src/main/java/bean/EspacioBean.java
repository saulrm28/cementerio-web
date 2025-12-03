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
import modelo.DAO.DAOImpl.EspacioDAOImpl;
import modelo.DAO.EspacioDAO;
import modelo.DTO.EspacioDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@ViewScoped 
public class EspacioBean implements Serializable {

    @Inject
    private Flash flash; 

    private EspacioDAO espacioDAO;
    private List<EspacioDTO> listaEspacios;
    private EspacioDTO nuevoEspacio; 

    @PostConstruct
    public void init() {
        espacioDAO = new EspacioDAOImpl();
        nuevoEspacio = new EspacioDTO();
        cargarEspacios();
    }

    private void cargarEspacios() {
        try {
            listaEspacios = espacioDAO.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
    }

   
    public String agregarEspacio() {
        try {
            espacioDAO.insertar(nuevoEspacio);
            nuevoEspacio = new EspacioDTO(); 
            cargarEspacios(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   
    public String eliminarEspacio(int idEspacio) {
        try {
            espacioDAO.eliminar(idEspacio);
            cargarEspacios(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }


    public String irAModificar(EspacioDTO espacio) {
       
        flash.put("espacioSeleccionado", espacio);
        return "modificarEspacio?faces-redirect=true";
    }

  

    public List<EspacioDTO> getListaEspacios() {
        return listaEspacios;
    }

    public void setListaEspacios(List<EspacioDTO> listaEspacios) {
        this.listaEspacios = listaEspacios;
    }

    public EspacioDTO getNuevoEspacio() {
        return nuevoEspacio;
    }

    public void setNuevoEspacio(EspacioDTO nuevoEspacio) {
        this.nuevoEspacio = nuevoEspacio;
    }
}

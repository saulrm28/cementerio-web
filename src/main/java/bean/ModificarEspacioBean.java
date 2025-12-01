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

@Named 
@ViewScoped
public class ModificarEspacioBean implements Serializable {

    @Inject
    private Flash flash;

    private EspacioDAO espacioDAO;
    private EspacioDTO espacio; 

    @PostConstruct
    public void init() {
        espacioDAO = new EspacioDAOImpl();
        
        espacio = (EspacioDTO) flash.get("espacioSeleccionado");

        
        if (espacio == null) {
            espacio = new EspacioDTO();
        }
    }

   
    public String actualizarEspacio() {
        try {
            espacioDAO.actualizar(espacio);
            return "espacios?faces-redirect=true"; 
        } catch (SQLException e) {
            e.printStackTrace();
            return null; 
        }
    }



    public EspacioDTO getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioDTO espacio) {
        this.espacio = espacio;
    }
}
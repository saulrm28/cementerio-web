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
import modelo.DTO.DifuntoDTO;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Named
@ViewScoped
public class DetalleBean implements Serializable {

    @Inject
    private Flash flash;
    
    private DifuntoDTO difunto;

    @PostConstruct
    public void init() {
        
        difunto = (DifuntoDTO) flash.get("difuntoSeleccionado");
    }
    
   
public String calcularEdadAlFallecer() {
   
    if (difunto == null || difunto.getFechaNacimiento() == null || difunto.getFechaDefuncion() == null) {
        return "Sin datos de fecha";
    }
 
    java.time.Period period = java.time.Period.between(difunto.getFechaNacimiento(), difunto.getFechaDefuncion());
    
    return String.format("%d años, %d meses y %d días",
            period.getYears(),
            period.getMonths(),
            period.getDays());
}


    public DifuntoDTO getDifunto() {
        return difunto;
    }
}
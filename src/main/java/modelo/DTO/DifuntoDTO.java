// Archivo: src/main/java/modelo/DTO/DifuntoDTO.java
package modelo.DTO;

import java.time.LocalDate;
import java.time.LocalTime; // Importamos LocalTime

public class DifuntoDTO {

    private int id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate fechaDefuncion;

    // --- CAMPOS NUEVOS ---
    private LocalTime horaEntierro;
    private EspacioDTO espacio; // Reemplaza a 'String ubicacion'

    // Getters y Setters para todos los campos...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(LocalDate fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    // --- Getters/Setters NUEVOS ---
    public LocalTime getHoraEntierro() {
        return horaEntierro;
    }

    public void setHoraEntierro(LocalTime horaEntierro) {
        this.horaEntierro = horaEntierro;
    }

    public EspacioDTO getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioDTO espacio) {
        this.espacio = espacio;
    }
}

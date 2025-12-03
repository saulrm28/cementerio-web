package modelo.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class DifuntoDTO {

    private int id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate fechaDefuncion;
    private LocalTime horaEntierro;
    private EspacioDTO espacio;

    // --- NUEVOS CAMPOS ---
    private String nacionalidad;
    private String estadoCivil;
    private String causaDefuncion;
    private String religion;
    private String tipoCeremonia;

    // Getters y Setters EXISTENTES (id, nombre, fechas, etc.) ...
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

    // --- NUEVOS GETTERS Y SETTERS ---
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCausaDefuncion() {
        return causaDefuncion;
    }

    public void setCausaDefuncion(String causaDefuncion) {
        this.causaDefuncion = causaDefuncion;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTipoCeremonia() {
        return tipoCeremonia;
    }

    public void setTipoCeremonia(String tipoCeremonia) {
        this.tipoCeremonia = tipoCeremonia;
    }
}

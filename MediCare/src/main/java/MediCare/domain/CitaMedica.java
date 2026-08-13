package MediCare.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "cita_medica")
public class CitaMedica{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCita;

    @Column(name = "paciente_nombre", length = 150)
    private String nombrePaciente;

    @Column(name = "especialidad", length = 100)
    private String areaEspecialidad;

    @Column(name = "fecha")
    private LocalDate fechaCita;

    @Column(name = "costo")
    private Double precioConsulta;

    @Column(name = "activa")
    private Boolean estaActiva = true;

    public CitaMedica() {
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getAreaEspecialidad() {
        return areaEspecialidad;
    }

    public void setAreaEspecialidad(String areaEspecialidad) {
        this.areaEspecialidad = areaEspecialidad;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Double getPrecioConsulta() {
        return precioConsulta;
    }

    public void setPrecioConsulta(Double precioConsulta) {
        this.precioConsulta = precioConsulta;
    }

    public Boolean getEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(Boolean estaActiva) {
        this.estaActiva = estaActiva;
    }
}
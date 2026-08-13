package MediCare.repository;

import MediCare.domain.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {
    // 1. Consulta derivada: Filtrar por estado de la cita (activas / inactivas)
    List<CitaMedica> findByEstaActiva(Boolean estaActiva);

    // 2. Consulta derivada con Containing: Búsqueda parcial por nombre de la especialidad
    List<CitaMedica> findByAreaEspecialidadContainingIgnoreCase(String especialidad);

    // 3. Consulta personalizada (@Query JPQL): Citas en un rango de fechas
    @Query("SELECT c FROM CitaMedica c WHERE c.fechaCita BETWEEN :fechaInicio AND :fechaFin")
    List<CitaMedica> buscarPorRangoFechas(@Param("fechaInicio") LocalDate fechaInicio, 
                                          @Param("fechaFin") LocalDate fechaFin);
}
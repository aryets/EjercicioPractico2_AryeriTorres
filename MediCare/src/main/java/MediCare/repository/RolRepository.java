package MediCare.repository;

import MediCare.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    // Consulta derivada para buscar un rol por su nombre
    Optional<Rol> findByNombreRol(String nombreRol);
}

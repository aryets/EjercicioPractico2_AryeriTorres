package MediCare.repository;

import MediCare.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método clave para Spring Security: buscar un usuario por su correo para el login
    Optional<Usuario> findByCorreo(String correo);

    // Consulta derivada para filtrar usuarios según el nombre de su rol asignado
    List<Usuario> findByRolAsignadoNombreRol(String nombreRol);
}

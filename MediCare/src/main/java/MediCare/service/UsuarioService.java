package MediCare.service;

import MediCare.domain.Rol;
import MediCare.domain.Usuario;
import MediCare.repository.RolRepository;
import MediCare.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private CorreoService correoService;

    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        boolean esNuevo = (usuario.getIdUsuario() == null);

        // Si es nuevo y no tiene rol asignado, se le da PACIENTE por defecto
        if (esNuevo && usuario.getRolAsignado() == null) {
            Rol rolPaciente = rolRepository.findByNombreRol("PACIENTE")
                    .orElseThrow(() -> new RuntimeException("El rol PACIENTE no existe en la BD"));
            usuario.setRolAsignado(rolPaciente);
        }

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Envía correo de bienvenida solo si es nuevo
        if (esNuevo && usuarioGuardado.getCorreo() != null) {
            correoService.enviarCorreoBienvenida(
                    usuarioGuardado.getCorreo(),
                    usuarioGuardado.getNombreCompleto()
            );
        }

        return usuarioGuardado;
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
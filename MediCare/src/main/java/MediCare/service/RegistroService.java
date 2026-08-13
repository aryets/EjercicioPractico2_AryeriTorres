package MediCare.service;

import MediCare.domain.Rol;
import MediCare.domain.Usuario;
import MediCare.repository.RolRepository;
import MediCare.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private CorreoService correoService;

    public Usuario registrar(Usuario usuario) {
        // Validar correo único
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        // Rol por defecto PACIENTE
        if (usuario.getRolAsignado() == null) {
            Rol rolPaciente = rolRepository.findByNombreRol("PACIENTE")
                    .orElseThrow(() -> new RuntimeException("Rol PACIENTE no existe"));
            usuario.setRolAsignado(rolPaciente);
        }

        usuario.setActivo(true);
        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        // Enviar correo de bienvenida
        correoService.enviarCorreoBienvenida(nuevoUsuario.getEmail(), nuevoUsuario.getNombre());

        return nuevoUsuario;
    }
}
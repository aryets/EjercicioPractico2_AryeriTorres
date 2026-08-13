package MediCare.service;

import MediCare.domain.Usuario;
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
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Envía el correo automático de bienvenida si es un usuario nuevo
        if (esNuevo && usuarioGuardado.getCorreo() != null) {
            correoService.enviarCorreoBienvenida(usuarioGuardado.getCorreo(), usuarioGuardado.getNombreCompleto());
        }

        return usuarioGuardado;
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
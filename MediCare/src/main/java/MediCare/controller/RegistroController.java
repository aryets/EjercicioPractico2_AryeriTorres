package MediCare.controller;

import MediCare.domain.Usuario;
import MediCare.service.CorreoService;
import MediCare.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CorreoService correoService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro"; // vista registro.html
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        correoService.enviarCorreoBienvenida(usuario.getCorreo(), usuario.getNombreCompleto());
        return "redirect:/login?registrado";
    }
}
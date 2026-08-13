package MediCare.controller;

import MediCare.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/listar")
    public String listarRoles(Model model) {
        model.addAttribute("roles", rolService.obtenerTodosLosRoles());
        return "usuario/listado";
    }
}

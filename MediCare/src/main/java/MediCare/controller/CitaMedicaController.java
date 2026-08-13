package MediCare.controller;

import MediCare.domain.CitaMedica;
import MediCare.service.CitaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaMedicaController {
    
    @Autowired
    private CitaMedicaService citaMedicaService;

    // Listar todas las citas
    @GetMapping
    public String listarCitas(Model model) {
        List<CitaMedica> citas = citaMedicaService.obtenerTodasLasCitas();
        model.addAttribute("citas", citas);
        return "citas/listado";
    }

    // Formulario para nueva cita
    @GetMapping("/nueva")
    public String formularioNuevaCita(Model model) {
        model.addAttribute("cita", new CitaMedica());
        return "citas/formulario";
    }

    // Formulario para editar cita
    @GetMapping("/editar/{id}")
    public String formularioEditarCita(@PathVariable("id") Long id, Model model) {
        CitaMedica cita = citaMedicaService.obtenerCitaPorId(id);
        if (cita != null) {
            model.addAttribute("cita", cita);
            return "citas/formulario";
        }
        return "redirect:/citas";
    }

    // Guardar o actualizar cita
    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute("cita") CitaMedica cita) {
        citaMedicaService.guardarCita(cita);
        return "redirect:/citas";
    }

    // Eliminar cita
    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable("id") Long id) {
        citaMedicaService.eliminarCita(id);
        return "redirect:/citas";
    }

    // --- MÉTODOS PARA CONSULTAS AVANZADAS / FILTROS ---
    // 1. Filtrar por estado de la cita
    @GetMapping("/filtrar/estado")
    public String filtrarPorEstado(@RequestParam("activa") Boolean activa, Model model) {
        List<CitaMedica> citas = citaMedicaService.filtrarPorEstado(activa);
        model.addAttribute("citas", citas);
        return "citas/listado";
    }

    // 2. Filtrar por especialidad (coincidencia parcial)
    @GetMapping("/filtrar/especialidad")
    public String filtrarPorEspecialidad(@RequestParam("especialidad") String especialidad, Model model) {
        List<CitaMedica> citas = citaMedicaService.filtrarPorEspecialidad(especialidad);
        model.addAttribute("citas", citas);
        return "citas/listado";
    }

    // 3. Filtrar por rango de fechas
    @GetMapping("/filtrar/fechas")
    public String filtrarPorFechas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
            Model model) {
        List<CitaMedica> citas = citaMedicaService.filtrarPorRangoFechas(inicio, fin);
        model.addAttribute("citas", citas);
        return "citas/listado";
    }
}
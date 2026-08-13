package MediCare.service;

import MediCare.domain.CitaMedica;
import MediCare.domain.Usuario;
import MediCare.repository.CitaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CitaMedicaService {

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Transactional(readOnly = true)
    public List<CitaMedica> obtenerTodasLasCitas() {
        return citaMedicaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CitaMedica obtenerCitaPorId(Long id) {
        return citaMedicaRepository.findById(id).orElse(null);
    }

    @Transactional
    public CitaMedica guardarCita(CitaMedica cita) {
        return citaMedicaRepository.save(cita);
    }

    @Transactional
    public void eliminarCita(Long id) {
        citaMedicaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> filtrarPorEstado(Boolean activa) {
        return citaMedicaRepository.findByEstaActiva(activa);
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> filtrarPorEspecialidad(String especialidad) {
        return citaMedicaRepository.findByAreaEspecialidadContainingIgnoreCase(especialidad);
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> filtrarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return citaMedicaRepository.buscarPorRangoFechas(inicio, fin);
    }
}
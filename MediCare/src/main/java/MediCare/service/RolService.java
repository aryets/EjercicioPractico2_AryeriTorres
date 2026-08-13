package MediCare.service;

import MediCare.domain.Rol;
import MediCare.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Transactional(readOnly = true)
    public List<Rol> obtenerTodosLosRoles() {
        return rolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Rol obtenerRolPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Transactional
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Transactional
    public void eliminarRol(Long id) {
        rolRepository.deleteById(id);
    }
}
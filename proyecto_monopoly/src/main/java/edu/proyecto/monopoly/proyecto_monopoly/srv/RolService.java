package edu.proyecto.monopoly.proyecto_monopoly.srv;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.RolDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.enums.RolNombre;
import edu.proyecto.monopoly.proyecto_monopoly.repository.RolRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;

@Service
@Transactional // Mantiene la coherencia en la BD si hay varios de accesos de escritura concurrentes
public class RolService {
    @Autowired 
    RolRepository rolRepository;

    public Optional<RolDb> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByNombre(rolNombre);
    }

    public void save(@NonNull RolDb rol){
        rolRepository.save(rol);
    }
}

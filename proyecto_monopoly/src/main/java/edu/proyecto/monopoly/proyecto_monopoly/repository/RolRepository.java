package edu.proyecto.monopoly.proyecto_monopoly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.RolDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.enums.RolNombre;

public interface RolRepository extends JpaRepository<RolDb, Integer>{
    Optional<RolDb> findByNombre(RolNombre rolNombre);
}

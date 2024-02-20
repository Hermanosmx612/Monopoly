package edu.proyecto.monopoly.proyecto_monopoly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.CasillaDb;

@Repository
public interface CasillaRepository extends JpaRepository<CasillaDb, Integer>{
    Optional<CasillaDb> findById(Integer id);
}

package edu.proyecto.monopoly.proyecto_monopoly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.PartidaDb;

@Repository
public interface PartidaRepository extends JpaRepository<PartidaDb, Long>{
    Optional<PartidaDb> findById(Integer id);
    List<PartidaDb> findAll();
    @Query("SELECT p FROM PartidaDb p WHERE NOT EXISTS (SELECT 1 FROM JugadorDb j WHERE j.partida = p AND j.usuario.id = :idUsuario)")
    List<PartidaDb> findPartidasNoAsociadasAUsuario(Integer idUsuario);



} 

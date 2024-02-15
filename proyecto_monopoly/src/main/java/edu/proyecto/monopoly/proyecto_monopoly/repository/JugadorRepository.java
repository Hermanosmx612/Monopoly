package edu.proyecto.monopoly.proyecto_monopoly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.JugadorDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.PartidaDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorDb, Long>{
    boolean existsByUsuarioIdAndPartidaId(int idUsuario, int idPartida);
    int countByPartida(PartidaDb partida);
    Optional<JugadorDb> findOneDistinctByUsuarioAndPartida(UsuarioDb usuario, PartidaDb partida);

}
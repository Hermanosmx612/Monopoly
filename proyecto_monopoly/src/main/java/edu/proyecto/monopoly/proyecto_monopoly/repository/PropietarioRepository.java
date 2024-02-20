package edu.proyecto.monopoly.proyecto_monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.PropietarioDb;

@Repository
public interface PropietarioRepository extends JpaRepository<PropietarioDb, Integer>{
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropietarioDb p WHERE p.partida.id = :idPartida AND p.casilla.id = :idCasilla")
    boolean existsByPartidaIdAndCasillaId(@Param("idPartida") int idPartida, @Param("idCasilla") int idCasilla);
    
}

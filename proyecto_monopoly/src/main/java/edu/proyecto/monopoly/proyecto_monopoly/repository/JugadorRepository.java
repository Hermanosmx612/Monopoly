package edu.proyecto.monopoly.proyecto_monopoly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.JugadorDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.PartidaDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;
import jakarta.transaction.Transactional;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorDb, Long>{
    boolean existsByUsuarioIdAndPartidaId(int idUsuario, int idPartida);
    int countByPartida(PartidaDb partida);
    Optional<JugadorDb> findOneDistinctByUsuarioAndPartida(UsuarioDb usuario, PartidaDb partida);
    @Modifying
    @Transactional
    @Query("UPDATE JugadorDb j SET j.posicion = j.posicion + :casillasAvanzar WHERE j.usuario.id = :idUser AND j.partida.id = :idPartida")
    void avanzarJugador(@Param("idUser") Integer idUser, @Param("idPartida") Integer idPartida, @Param("casillasAvanzar") Integer casillasAvanzar);
    @Query("SELECT j.posicion FROM JugadorDb j WHERE j.usuario.id = :idUser AND j.partida.id = :idPartida")
    public Integer obtenerPosicionJugador(@Param("idUser") Integer idUser, @Param("idPartida") Integer idPartida);
    @Modifying
    @Transactional
    @Query("UPDATE JugadorDb j SET j.posicion = :casillasAvanzar WHERE j.usuario.id = :idUser AND j.partida.id = :idPartida")
    void avanzarJugadorReset(@Param("idUser") Integer idUser, @Param("idPartida") Integer idPartida, @Param("casillasAvanzar") Integer casillasAvanzar);
    @Query("SELECT j.colorFicha, j.posicion FROM JugadorDb j WHERE j.partida.id = :idPartida")
    List<Object[]> findColorFichaByPartidaId(Integer idPartida);
    @Modifying
    @Transactional
    @Query("UPDATE JugadorDb j SET j.dineroActual = j.dineroActual - :dineroResta WHERE j.usuario.id = :idUser AND j.partida.id = :idPartida")
    void restarDineroPlayer(@Param("idUser") Integer idUser, @Param("idPartida") Integer idPartida, @Param("dineroResta") Integer dineroARestar);

}
package edu.proyecto.monopoly.proyecto_monopoly.srv.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.JugadorDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.PartidaDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.JugadorAddPartidaDto;
import edu.proyecto.monopoly.proyecto_monopoly.repository.JugadorRepository;
import edu.proyecto.monopoly.proyecto_monopoly.srv.JugadorService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.mapper.JugadorMapper;

@Service
public class JugadorServiceImpl implements JugadorService{

    @Autowired
    private final JugadorRepository jugadorRepository;

    
    public JugadorServiceImpl(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }


    @Override
    public JugadorAddPartidaDto save(JugadorAddPartidaDto jugadorAddPartidaDto) {
        JugadorDb jugadorDb = JugadorMapper.INSTANCE.jugadorNuevoToJugadorDb(jugadorAddPartidaDto);
        JugadorDb savedJugadorDb = jugadorRepository.save(jugadorDb);
        return JugadorMapper.INSTANCE.jugadorDbToJugadorNuevo(savedJugadorDb);
}


    @Override
    public Optional<JugadorDb> buscarJugadorPorUsuarioYPartida(UsuarioDb usuario, PartidaDb partida) {
        return jugadorRepository.findOneDistinctByUsuarioAndPartida(usuario, partida);

    }


    @Override
    public int contarIdPartida(PartidaDb partida) {
        return jugadorRepository.countByPartida(partida);
    }

    @Override
    public void avanzarJugador(Integer idUsuario, Integer idPartida, Integer casillasAvanzar) {
        jugadorRepository.avanzarJugador(idUsuario, idPartida, casillasAvanzar);

    }

    @Override
    public void avanzarJugadorReset(Integer idUsuario, Integer idPartida, Integer casillasAvanzar) {
        jugadorRepository.avanzarJugadorReset(idUsuario, idPartida, casillasAvanzar);

    }

    @Override
    public List<Object[]> getColorFichaByPartidaId(Integer idPartida) {
        return jugadorRepository.findColorFichaByPartidaId(idPartida);
    }


    @Override
    public Integer obtenerPosicionJugador(Integer idUsuario, Integer idPartida) {
        return jugadorRepository.obtenerPosicionJugador(idUsuario, idPartida);
    }


    @Override
    public void restarDinero(Integer idUser, Integer idPartida, Integer dineroArestar) {
        jugadorRepository.restarDineroPlayer(idUser, idPartida, dineroArestar);
    }


}

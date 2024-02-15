package edu.proyecto.monopoly.proyecto_monopoly.srv.impl;


import java.util.Optional;

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


}

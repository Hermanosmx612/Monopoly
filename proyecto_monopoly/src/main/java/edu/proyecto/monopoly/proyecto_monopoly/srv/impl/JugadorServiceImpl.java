package edu.proyecto.monopoly.proyecto_monopoly.srv.impl;

import org.springframework.stereotype.Service;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.JugadorDb;
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

}

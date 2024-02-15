package edu.proyecto.monopoly.proyecto_monopoly.srv;


import java.util.Optional;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.JugadorDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.PartidaDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.JugadorAddPartidaDto;

public interface JugadorService {
    public JugadorAddPartidaDto save(JugadorAddPartidaDto jugadorAddPartidaDto);
    public Optional<JugadorDb> buscarJugadorPorUsuarioYPartida(UsuarioDb usuario, PartidaDb partida);
    public int contarIdPartida(PartidaDb partida);
}

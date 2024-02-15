package edu.proyecto.monopoly.proyecto_monopoly.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.JugadorDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.JugadorAddPartidaDto;

@Mapper
public interface JugadorMapper {
    JugadorMapper INSTANCE = Mappers.getMapper(JugadorMapper.class);


    @Mapping(source = "idUser", target = "usuario.id")
    @Mapping(source = "idPartida", target = "partida.id")
    JugadorDb jugadorNuevoToJugadorDb(JugadorAddPartidaDto jugadorAddPartidaDto);

    @Mapping(target = "idUser", source = "usuario.id")
    @Mapping(target = "idPartida", source = "partida.id")
    JugadorAddPartidaDto jugadorDbToJugadorNuevo(JugadorDb jugadorDb);
    
}

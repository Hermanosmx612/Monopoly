package edu.proyecto.monopoly.proyecto_monopoly.srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.PropietarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.PropietarioDto;

@Mapper
public interface PropietarioMapper {
    PropietarioMapper INSTANCE = Mappers.getMapper(PropietarioMapper.class);

    @Mapping(source = "idCasilla", target = "casilla.id")
    @Mapping(source = "idPartida", target = "partida.id")
    @Mapping(source = "idJugador", target = "jugador.id")
    PropietarioDb propietarioNuevoToPropietarioDb(PropietarioDto propietarioDto);

    @Mapping(target = "idCasilla", source = "casilla.id")
    @Mapping(target = "idPartida", source = "partida.id")
    @Mapping(target = "idJugador", source = "jugador.id")
    PropietarioDto propietarioDbToPropietarioNuevo(PropietarioDb propietarioDb);



}

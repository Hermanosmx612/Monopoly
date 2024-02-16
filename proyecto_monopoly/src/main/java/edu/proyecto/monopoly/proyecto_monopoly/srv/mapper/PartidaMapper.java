package edu.proyecto.monopoly.proyecto_monopoly.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.PartidaDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.ListaPartidasDto;

@Mapper
public interface PartidaMapper {
    PartidaMapper INSTANCE = Mappers.getMapper(PartidaMapper.class);
    List<ListaPartidasDto> partidaDbToPartidaList(List<PartidaDb> partidaDb);


}

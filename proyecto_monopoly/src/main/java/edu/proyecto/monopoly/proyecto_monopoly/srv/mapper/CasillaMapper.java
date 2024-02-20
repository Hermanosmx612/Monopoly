package edu.proyecto.monopoly.proyecto_monopoly.srv.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.CasillaDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.CasillaInfo;

@Mapper
public interface CasillaMapper {
    CasillaMapper INSTANCE = Mappers.getMapper(CasillaMapper.class);

    CasillaInfo casillaDbToCastillaInfo(CasillaDb casillaDb);

}

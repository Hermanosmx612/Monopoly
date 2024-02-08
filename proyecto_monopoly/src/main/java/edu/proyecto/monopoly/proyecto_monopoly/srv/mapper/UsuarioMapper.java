package edu.proyecto.monopoly.proyecto_monopoly.srv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.UserList;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    
    List<UserList> usuarioDbToUsuarioList(List<UsuarioDb> usuariosDb);
}

package edu.proyecto.monopoly.proyecto_monopoly.srv;

import java.util.List;
import java.util.Optional;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.PartidaDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.ListaPartidasDto;

public interface PartidaService{
    public PartidaDb crearPartidaDb();
    public Optional<PartidaDb> getById(Integer id);
    public List<ListaPartidasDto> obtenerPartidasNoAsociadasAUsuario(int idUsuario);
} 
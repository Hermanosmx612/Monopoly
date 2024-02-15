package edu.proyecto.monopoly.proyecto_monopoly.srv;

import java.util.Optional;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.PartidaDb;

public interface PartidaService{
    public PartidaDb crearPartidaDb();
    public Optional<PartidaDb> getById(Integer id);
} 
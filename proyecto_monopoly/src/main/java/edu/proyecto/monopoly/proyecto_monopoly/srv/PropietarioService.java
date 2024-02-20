package edu.proyecto.monopoly.proyecto_monopoly.srv;

import edu.proyecto.monopoly.proyecto_monopoly.model.dto.PropietarioDto;

public interface PropietarioService {
    boolean checkIfBuyed(Integer idPartida, Integer idCasilla);
    public PropietarioDto save(PropietarioDto propietarioDto);

}

package edu.proyecto.monopoly.proyecto_monopoly.srv;

import edu.proyecto.monopoly.proyecto_monopoly.model.dto.PropietarioDto;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.PropietarioInfo;

public interface PropietarioService {
    boolean checkIfBuyed(Integer idPartida, Integer idCasilla);
    public PropietarioDto save(PropietarioDto propietarioDto);
    public PropietarioInfo getUserPropietario(Integer idPartida, Integer idCasilla, Integer idJugador);

}

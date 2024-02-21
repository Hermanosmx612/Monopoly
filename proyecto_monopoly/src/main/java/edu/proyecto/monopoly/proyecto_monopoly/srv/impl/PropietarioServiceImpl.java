package edu.proyecto.monopoly.proyecto_monopoly.srv.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.PropietarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.PropietarioDto;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.PropietarioInfo;
import edu.proyecto.monopoly.proyecto_monopoly.repository.PropietarioRepository;
import edu.proyecto.monopoly.proyecto_monopoly.srv.PropietarioService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.mapper.PropietarioMapper;

@Service
public class PropietarioServiceImpl implements PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    public PropietarioServiceImpl(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    @Override
    public boolean checkIfBuyed(Integer idPartida, Integer idCasilla) {
        return propietarioRepository.existsByPartidaIdAndCasillaId(idPartida, idCasilla);
    }

    @Override
    public PropietarioDto save(PropietarioDto propietarioDto) {
        PropietarioDb propietarioDb = PropietarioMapper.INSTANCE.propietarioNuevoToPropietarioDb(propietarioDto);
        PropietarioDb savedPropietarioDb = propietarioRepository.save(propietarioDb);
        return PropietarioMapper.INSTANCE.propietarioDbToPropietarioNuevo(savedPropietarioDb);
    }

    @Override
    public PropietarioInfo getUserPropietario(Integer idPartida, Integer idCasilla, Integer idJugador) {
        return PropietarioMapper.INSTANCE.propietarioDbPropietarioInfo(
                propietarioRepository.getUserIdPropietario(idJugador, idCasilla, idPartida).get());
    }

}

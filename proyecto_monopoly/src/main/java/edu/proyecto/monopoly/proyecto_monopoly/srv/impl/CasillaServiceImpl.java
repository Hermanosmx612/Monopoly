package edu.proyecto.monopoly.proyecto_monopoly.srv.impl;


import org.springframework.stereotype.Service;

import edu.proyecto.monopoly.proyecto_monopoly.model.dto.CasillaInfo;
import edu.proyecto.monopoly.proyecto_monopoly.repository.CasillaRepository;
import edu.proyecto.monopoly.proyecto_monopoly.srv.CasillaService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.mapper.CasillaMapper;

@Service
public class CasillaServiceImpl implements CasillaService{

    private CasillaRepository casillaRepository;

    public CasillaServiceImpl(CasillaRepository casillaRepository) {
        this.casillaRepository = casillaRepository;
    }



    @Override
    public CasillaInfo findCasilla(Integer id) {
        return CasillaMapper.INSTANCE.casillaDbToCastillaInfo(casillaRepository.findById(id).get());
    }
    
}

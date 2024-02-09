package edu.proyecto.monopoly.proyecto_monopoly.srv.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import edu.proyecto.monopoly.proyecto_monopoly.srv.GameService;

@Service
public class GameServiceImpl implements GameService{

    @Override
    public Integer numeroRand() {
        Random random = new Random();
        int randomNumber = random.nextInt(6) + 1;
        return randomNumber;
    }
    
}

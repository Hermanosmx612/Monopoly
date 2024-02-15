package edu.proyecto.monopoly.proyecto_monopoly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.monopoly.proyecto_monopoly.srv.GameService;



@RestController
@RequestMapping("/api/v1/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/aleatorio")
    public ResponseEntity<Integer> getAleatoryNumber(){ 
        return ResponseEntity.ok().body(gameService.numeroRand());
        
    }
    
}

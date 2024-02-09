package edu.proyecto.monopoly.proyecto_monopoly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/game")
public class GameController {
    @GetMapping("/aleatorio")
    public ResponseEntity<Integer> getAleatoryNumber(){
        
        return ResponseEntity.ok().body(2);
        
    }
}

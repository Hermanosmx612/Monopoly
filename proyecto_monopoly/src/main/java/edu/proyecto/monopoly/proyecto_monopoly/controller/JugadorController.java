package edu.proyecto.monopoly.proyecto_monopoly.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.monopoly.proyecto_monopoly.model.dto.JugadorAddPartidaDto;
import edu.proyecto.monopoly.proyecto_monopoly.srv.JugadorService;

@RestController
@RequestMapping("/jugador")
public class JugadorController {
    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PostMapping("addplayer")
    public ResponseEntity<?> addplayerToGame(@RequestBody JugadorAddPartidaDto jugadorAddPartidaDto) {
        try {
            JugadorAddPartidaDto jugadorAgregado = jugadorService.save(jugadorAddPartidaDto);
            return new ResponseEntity<>(jugadorAgregado, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Error adding player to game", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

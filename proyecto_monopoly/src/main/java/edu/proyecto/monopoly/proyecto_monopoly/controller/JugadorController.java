package edu.proyecto.monopoly.proyecto_monopoly.controller;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.JugadorDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.JugadorAddPartidaDto;
import edu.proyecto.monopoly.proyecto_monopoly.srv.JugadorService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.PartidaService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/jugador")
public class JugadorController {
    private final JugadorService jugadorService;
    private final PartidaService partidaService;
    private final UsuarioService usuarioService;

    

    public JugadorController(JugadorService jugadorService, PartidaService partidaService,
            UsuarioService usuarioService) {
        this.jugadorService = jugadorService;
        this.partidaService = partidaService;
        this.usuarioService = usuarioService;
    }



    @PostMapping("addplayer")
    public ResponseEntity<?> addplayerToGame(@RequestBody JugadorAddPartidaDto jugadorAddPartidaDto) {
        try {
            Optional<JugadorDb> playerDb = jugadorService.buscarJugadorPorUsuarioYPartida(usuarioService.getById(jugadorAddPartidaDto.getIdUser()).get(), partidaService.getById(jugadorAddPartidaDto.getIdPartida()).get());
            if(!playerDb.isPresent()){
                JugadorAddPartidaDto jugadorAgregado = jugadorService.save(jugadorAddPartidaDto);
                return new ResponseEntity<>(jugadorAgregado, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Error el player ya existe en esta partida", HttpStatus.CONFLICT);

            }
            
        } catch(Exception e) {
            return new ResponseEntity<>("Error adding player to game", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("empezar/{id}") // Comprobar si minimo son 2 jugadores para poder empezar la partida
    public boolean empezarPartida(@PathVariable("id") Integer id) {
        if(jugadorService.contarIdPartida(partidaService.getById(id).get()) >= 2){
            return true;
        }
        return false;
    }

    @PostMapping("avanzar/{idUser}/{idPartida}/{casillasAvanzar}")
    public ResponseEntity<?> avanzarFicha(@PathVariable("idUser") Integer idUser, @PathVariable("idPartida") Integer idPartida, @PathVariable("casillasAvanzar") Integer casillasAvanzar) {
            int numeroActual = jugadorService.obtenerPosicionJugador(idUser, idPartida);
            if(numeroActual + casillasAvanzar > 39){
                casillasAvanzar = Math.abs(39 - (numeroActual + casillasAvanzar));
                jugadorService.avanzarJugadorReset(idUser, idPartida, casillasAvanzar);
            }else{
                jugadorService.avanzarJugador(idUser, idPartida, casillasAvanzar);
                
            }
            return new ResponseEntity<>(jugadorService.getColorFichaByPartidaId(idPartida), HttpStatus.OK);
    }


    
}

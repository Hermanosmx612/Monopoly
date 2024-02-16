package edu.proyecto.monopoly.proyecto_monopoly.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.PartidaDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.ListaPartidasDto;
import edu.proyecto.monopoly.proyecto_monopoly.srv.PartidaService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/partida")
public class PartidaController {
    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping("/crear")
    public PartidaDb crearPartida() {
        return partidaService.crearPartidaDb();
    }

    // Sacar la lista de la partida al que el user no pertenece
    @GetMapping("lista/{idUser}")
    public ResponseEntity<List<ListaPartidasDto>> listaPartidasNoEsta(@PathVariable("idUser") Integer idUser) { 
        List<ListaPartidasDto> partidasNoAsociadas = partidaService.obtenerPartidasNoAsociadasAUsuario(idUser);
        
        if (partidasNoAsociadas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(partidasNoAsociadas, HttpStatus.OK);
        }    
    }

    

    
    
    
}

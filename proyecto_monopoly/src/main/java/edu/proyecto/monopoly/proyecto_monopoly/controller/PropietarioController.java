package edu.proyecto.monopoly.proyecto_monopoly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.monopoly.proyecto_monopoly.model.dto.CasillaInfo;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.PropietarioDto;
import edu.proyecto.monopoly.proyecto_monopoly.srv.CasillaService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.JugadorService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.PropietarioService;

@RestController
@RequestMapping("propiedad")
public class PropietarioController {
    private final PropietarioService propietarioService;
    private final CasillaService casillaService;
    private final JugadorService jugadorService;


    





    public PropietarioController(PropietarioService propietarioService, CasillaService casillaService,
            JugadorService jugadorService) {
        this.propietarioService = propietarioService;
        this.casillaService = casillaService;
        this.jugadorService = jugadorService;
    }








    @PostMapping("comprar/{idUser}/{idPartida}/{idCasilla}/{colorPropiedad}")
    public ResponseEntity<?> comprarPropiedad(@PathVariable("idUser") Integer idUser, @PathVariable("idPartida") Integer idPartida, @PathVariable("idCasilla") Integer idCasilla,
    @PathVariable("colorPropiedad") String colorPropiedad) {
        boolean casillaComprada = propietarioService.checkIfBuyed(idPartida, idCasilla);

        if (casillaComprada) {
            // La casilla ya ha sido comprada en la partida
            return ResponseEntity.badRequest().body("La casilla ya ha sido comprada en esta partida");
        } else {
            propietarioService.save(new PropietarioDto(idCasilla, idPartida, idUser, colorPropiedad));
            CasillaInfo casilla = casillaService.findCasilla(idCasilla);
            if(casilla.getTipo().equals("propiedad")){
                int moneyToPay = casilla.getValor();
                jugadorService.restarDinero(idUser, idPartida, moneyToPay);
            }
            return ResponseEntity.ok("La casilla se ha comprado correctamente");
        }
    }

}

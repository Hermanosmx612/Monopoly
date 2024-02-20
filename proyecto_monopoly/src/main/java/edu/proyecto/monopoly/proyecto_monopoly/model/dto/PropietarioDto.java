package edu.proyecto.monopoly.proyecto_monopoly.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropietarioDto {
    private Integer id;
    private Integer idCasilla;
    private Integer idPartida;
    private Integer idJugador;
    private String color;
    
    public PropietarioDto(Integer idCasilla, Integer idPartida, Integer idJugador, String color) {
        this.idCasilla = idCasilla;
        this.idPartida = idPartida;
        this.idJugador = idJugador;
        this.color = color;
    }
}


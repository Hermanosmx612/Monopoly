package edu.proyecto.monopoly.proyecto_monopoly.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JugadorAddPartidaDto {
    @NotBlank
    private Integer id;
    private Integer idUser;
    private Integer idPartida;
    private String colorFicha;
    private Double dineroActual;

}

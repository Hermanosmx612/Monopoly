package edu.proyecto.monopoly.proyecto_monopoly.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CasillaInfo {
    private int id;
    private String nombre;
    private String tipo;
    private Integer valor;
}

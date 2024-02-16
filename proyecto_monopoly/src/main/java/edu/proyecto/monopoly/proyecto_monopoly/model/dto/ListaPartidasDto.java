package edu.proyecto.monopoly.proyecto_monopoly.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaPartidasDto {
    Integer id;
    Date fechaInicio;
    String estado;
}

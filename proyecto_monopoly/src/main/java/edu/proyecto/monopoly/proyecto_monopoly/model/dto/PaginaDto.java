package edu.proyecto.monopoly.proyecto_monopoly.model.dto;

import java.util.List;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginaDto<T> {
    int number; // numero de pagina solicitada
    int size; //tamaño de la pagina
    long totalElements ; //Tamaño total sin paginacion
    int totalPages; // Total de paginas
    List<T> content; //Listado
    Sort sort; // Ordenacion
}

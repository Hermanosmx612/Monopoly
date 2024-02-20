package edu.proyecto.monopoly.proyecto_monopoly.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "propietarios") // Nombre de la tabla en la base de datos
public class PropietarioDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_partida") // Nombre de la columna de la clave externa
    private PartidaDb partida;

    @ManyToOne
    @JoinColumn(name = "id_casilla") // Nombre de la columna de la clave externa
    private CasillaDb casilla;

    @ManyToOne
    @JoinColumn(name = "id_jugador") // Nombre de la columna de la clave externa
    private JugadorDb jugador;

    @Column(name = "num_casas")
    private int numCasas;

    @Column(name = "num_hoteles")
    private int numHoteles;
    
    private boolean hipotecada;

    private String color;
}

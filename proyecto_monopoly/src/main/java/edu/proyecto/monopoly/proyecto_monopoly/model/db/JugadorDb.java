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
@Table(name = "jugadores") // Nombre de la tabla en la base de datos
public class JugadorDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario") // Nombre de la columna de la clave externa
    private UsuarioDb usuario;

    @ManyToOne
    @JoinColumn(name = "id_partida") // Nombre de la columna de la clave externa
    private PartidaDb partida;

    @Column(name = "color_ficha")
    private String colorFicha;

    @Column(name = "dinero_actual")
    private double dineroActual;

    // Constructores, getters y setters
}
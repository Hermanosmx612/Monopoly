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
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "jugadores") // Nombre de la tabla en la base de datos
public class JugadorDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private Integer id;

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

    public JugadorDb(){
        this.dineroActual = 500.00;
        this.colorFicha = "default";
    }
}
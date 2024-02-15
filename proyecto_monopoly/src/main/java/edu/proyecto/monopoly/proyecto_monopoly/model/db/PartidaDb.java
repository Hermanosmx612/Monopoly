package edu.proyecto.monopoly.proyecto_monopoly.model.db;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "partidas") // Nombre de la tabla en la base de datos
public class PartidaDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partida")
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    private int duracion;

    private String estado;

    @Column(name = "otros_detalles")
    private String otrosDetalles;

    public PartidaDb(){
        this.fechaInicio = new Date();
        this.duracion = 0;
        this.estado = "iniciada";
    }
}
package org.example.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participa {
    @EmbeddedId
    private ParticipaPk id;

    @ManyToOne
    @MapsId("eventoId")
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @MapsId("personajeId")
    @JoinColumn(name = "id_personaje")
    private Personaje personaje;

    private LocalDate fecha;
    private String rol;
}

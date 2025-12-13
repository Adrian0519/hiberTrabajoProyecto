package org.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ParticipaPk {
    @Column(name = "id_evento")
    private int eventoId;
    @Column(name = "id_personaje")
    private int personajeId;
}

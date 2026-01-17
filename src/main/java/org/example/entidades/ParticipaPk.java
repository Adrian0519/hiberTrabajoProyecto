package org.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ParticipaPk implements Serializable {
    @Column(name = "id_evento")
    private int eventoId;
    @Column(name = "id_personaje")
    private int personajeId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParticipaPk that = (ParticipaPk) o;
        return eventoId == that.eventoId && personajeId == that.personajeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventoId, personajeId);
    }
}

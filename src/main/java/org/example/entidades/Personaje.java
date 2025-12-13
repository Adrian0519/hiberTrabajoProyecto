package org.example.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "personaje")

public class Personaje {
    @Id
    private int id;
    @NonNull
    private String nombre;
    private String alias;

    @OneToOne
    @JoinColumn(name = "id_traje")
    private Traje traje;

    @OneToMany(mappedBy = "personaje")
    private List<Participa>participaciones;

    @ManyToMany(mappedBy = "personajes")
    private List<Habilidad>habilidades;

    public Personaje(int id, @NonNull String nombre, String alias) {
        this.id = id;
        this.nombre = nombre;
        this.alias = alias;
    }

    public void agregarHabilidad(Habilidad habilidad){
        habilidades.add(habilidad);
        habilidad.getPersonajes().add(this);
    }

    public void agregarParticipacion(Participa participa){
        participaciones.add(participa);
        participa.setPersonaje(this);

    }
}

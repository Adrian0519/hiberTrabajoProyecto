package org.example.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habilidad {
    @Id
    private int id;
    private String nombre;
    private String descripcion;

    @ManyToMany
    @JoinTable(
            name = "Personaje_Habilidad",
            joinColumns = @JoinColumn(name ="id_habilidad"),
            inverseJoinColumns = @JoinColumn(name = "id_personaje")
    )
    private List<Personaje> personajes;
}

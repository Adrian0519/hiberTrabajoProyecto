package org.example.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Evento {
    @Id
    private int id;
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "Evento")
    private List <Participa>participantes;

}

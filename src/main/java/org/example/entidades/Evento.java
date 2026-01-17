package org.example.entidades;

import jakarta.persistence.*;
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
    private String lugar;

    @OneToMany(mappedBy = "evento",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List <Participa>participantes;


    public void agregarParticipantes(Participa participa){
        participantes.add(participa);
        participa.setEvento(this);
    }
}

package org.example.tablas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "personajes")
public class Personajes {
    @Id
    @OneToMany(mappedBy = "habilidad")
    private int id;
    private String nombre;
    private String alias;
    @OneToOne(mappedBy = "Traje")
    private int id_traje;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getId_traje() {
        return id_traje;
    }

    public void setId_traje(int id_traje) {
        this.id_traje = id_traje;
    }
}

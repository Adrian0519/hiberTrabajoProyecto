package org.example.tablas;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participa")
public class Participa {
    @OneToMany(mappedBy = "Personajes")
    private int id_personaje;
    @OneToOne(mappedBy = "Evento")
    private int id_evento;
    private Data fecha;
    private String rol;

    public int getId_personaje() {
        return id_personaje;
    }

    public void setId_personaje(int id_personaje) {
        this.id_personaje = id_personaje;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public Data getFecha() {
        return fecha;
    }

    public void setFecha(Data fecha) {
        this.fecha = fecha;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

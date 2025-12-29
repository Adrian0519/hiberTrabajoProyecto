package org.example.Repositorios;

import org.example.entidades.Evento;
import org.example.entidades.Participa;
import org.example.entidades.ParticipaPk;
import org.example.entidades.Personaje;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class EventosR {
    private Session session;

    public EventosR(Session session) {
        this.session = session;
    }

    public void participacionEvento(String nombre, String eventoNombre, String rol, LocalDate localDate){
        Transaction transaction=null;
        String compN="select n from Personaje n where n.nombre=:nombre";
        String evenN="select e from Evento e where e.nombre=:nombre";
        String participa="select p from Participa p where p.personaje.id=:personajeId and p.evento.id=:eventoId";
        try {
            transaction= session.beginTransaction();
            Personaje personaje=(Personaje) session.createQuery(compN)
                    .setParameter("nombre",nombre)
                    .uniqueResult();
            Evento evento1=(Evento) session.createQuery(evenN)
                    .setParameter("nombre",eventoNombre)
                    .uniqueResult();
            if (personaje==null || evento1==null){
                System.out.println("los datos proporcionados son erroneos");
                return;
            }
            Participa participcionExistente=(Participa) session.createQuery(participa)
                    .setParameter("personajeId",personaje.getId())
                    .setParameter("eventoId",evento1.getId())
                    .uniqueResult();
            if (participcionExistente!=null){
                System.out.println("Ya esta registrado");
                return;
            }
            ParticipaPk participaPk=new ParticipaPk(personaje.getId(),evento1.getId());
            Participa participa1=new Participa(participaPk,evento1,personaje,localDate,rol);
            personaje.agregarParticipacion(participa1);
            evento1.agregarParticipantes(participa1);
            session.persist(participa1);
            transaction.commit();
            System.out.println("participacion registrada");
        } catch (Exception e) {
            System.out.println("error en la creacion de la participacion");
        }
    }

    public void participaEnEvento(String nombreEvento){
        String consulta="select p from Personaje p inner join p.participaciones pa inner join pa.evento e where e.nombre = :nombre";
        try {
            List <Personaje>list= (List<Personaje>) session.createQuery(consulta)
                    .setParameter("nombre",nombreEvento)
                    .list();
            for (int i = 0; i < list.size(); i++) {
                Personaje personaje=list.get(i);
                System.out.println(personaje.getNombre());
            }
        } catch (Exception e) {
            System.out.println("Error en la consulta, el evento no existe");
        }
    }
}

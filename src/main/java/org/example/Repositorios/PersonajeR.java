package org.example.Repositorios;

import org.example.entidades.Habilidad;
import org.example.entidades.Participa;
import org.example.entidades.Personaje;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonajeR {
    private Session session;

    public PersonajeR(Session session) {
        this.session = session;
    }

    public void crearPersonaje(String nombre, String alias) {
        String sentenciaId = "select MAX(p.id) from Personaje p";
        Transaction transaccion = null;
        try {
            transaccion = session.beginTransaction();
            Integer id = (Integer) session.createQuery(sentenciaId)
                    .uniqueResult();
            int nuevoId = id + 1;
            Personaje personaje = new Personaje(nuevoId, nombre, alias);
            session.persist(personaje);
            transaccion.commit();
            System.out.println("Creado exitosamente");
        } catch (Exception e) {
            System.out.println("Error en la creacion " + e);
        }
    }

    public void actualizarPersonaje(int id, String nombre, String alias) {
        Transaction transaction = null;
        String sentencia = "select p from Personaje p where p.id= :id";
        try {
            transaction = session.beginTransaction();
            Personaje personaje = (Personaje) session.createQuery(sentencia)
                    .setParameter("id", id)
                    .uniqueResult();
            if (personaje == null) {
                System.out.println("No existe el personaje");
                transaction.rollback();
                return;
            }
            personaje.setNombre(nombre);
            personaje.setAlias(alias);
            transaction.commit();
            System.out.println("Personaje actualizado correctamente");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void borrarPersonaje(int id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Personaje personaje = (Personaje) session.createQuery("from Personaje p where p.id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
            if (personaje == null) {
                System.out.println("No existe el personaje");
                transaction.rollback();
                return;
            }

            if (personaje.getTraje() != null) {
                session.remove(personaje.getTraje());
                personaje.setTraje(null);
            }

            if (personaje.getParticipaciones() != null) {
                for (Participa participa : personaje.getParticipaciones()) {
                    session.remove(participa);
                }
                personaje.getParticipaciones().clear();
            }

            if (personaje.getHabilidades() != null) {
                for (Habilidad hab : personaje.getHabilidades()) {
                    hab.getPersonajes().remove(personaje);
                }
                personaje.getHabilidades().clear();
            }

            session.remove(personaje);
            transaction.commit();
            System.out.println("Personaje eliminado correctamente");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

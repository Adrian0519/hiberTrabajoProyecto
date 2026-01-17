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

    public void mostrarPersonaje(int id){
        String sentencia="select p from Personaje p left join fetch p.traje t where p.id= :id";
        try {
            Personaje personaje=(Personaje) session.createQuery(sentencia)
                    .setParameter("id",id)
                    .uniqueResult();
            personaje.getHabilidades().size();
            for (int i = 0; i < personaje.getParticipaciones().size(); i++) {
                Participa pa = personaje.getParticipaciones().get(i);
                if (pa.getEvento() != null) {
                    pa.getEvento().getNombre();
                }
            }
            System.out.println(personaje.getNombre() + " tiene el alias " + personaje.getAlias() + " y se corresponde de con la id " + personaje.getId());
            if (personaje.getTraje()!=null){
                System.out.println("El traje es " + personaje.getTraje().getEspecificacion());
            }
            System.out.println("/////////////////////////////////////////");
            for (int i = 0; i < personaje.getHabilidades().size(); i++) {
                Habilidad habilidad=personaje.getHabilidades().get(i);
                System.out.println("Su habilidad " + habilidad.getNombre());
            }
            System.out.println("/////////////////////////////////////////");
            for (int i = 0; i < personaje.getParticipaciones().size(); i++) {
                Participa pa = personaje.getParticipaciones().get(i);
                System.out.println("El nombre del evento " + pa.getEvento().getNombre());
                System.out.println("El evento es en el lugar " + pa.getEvento().getLugar());
                System.out.println("Tiene el rol de  " + pa.getRol());
                System.out.println("En la fecha " + pa.getFecha());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

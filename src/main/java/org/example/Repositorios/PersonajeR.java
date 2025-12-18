package org.example.Repositorios;

import org.example.entidades.Personaje;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonajeR {
    private Session session;

    public PersonajeR(Session session) {
        this.session = session;
    }

    public void  crearPersonaje(String nombre, String alias){
        String sentenciaId="select MAX(p.id) from Personaje p";
                Transaction transaccion=null;
        try {
            transaccion= session.beginTransaction();
            Integer id= (Integer) session.createQuery(sentenciaId)
                    .uniqueResult();
            int nuevoId=id+1;
            Personaje personaje=new Personaje(nuevoId,nombre,alias);
            session.persist(personaje);
            transaccion.commit();
            System.out.println("Creado exitosamente");
        } catch (Exception e) {
            System.out.println("Error en la creacion " + e);
        }
    }

    public void actualizarPersonaje(int id, String nombre, String alias) {
        Transaction tx = null;
        String sentencia = "select p from Personaje p where p.id= :id";
        try {
            tx = session.beginTransaction();
            Personaje personaje =(Personaje) session.createQuery(sentencia)
                    .setParameter("id", id)
                    .uniqueResult();
            if (personaje == null) {
                System.out.println("No existe el personaje");
                tx.rollback();
                return;
            }
            personaje.setNombre(nombre);
            personaje.setAlias(alias);
            tx.commit();
            System.out.println("Personaje actualizado correctamente");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

}

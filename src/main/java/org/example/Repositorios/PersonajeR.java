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
}

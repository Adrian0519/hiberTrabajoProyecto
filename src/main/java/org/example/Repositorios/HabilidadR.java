package org.example.Repositorios;

import org.example.entidades.Habilidad;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HabilidadR {
    private Session session;

    public HabilidadR(Session session) {
        this.session = session;
    }

    public void crearHabilidadees(String nombre, String descripcion){
        Transaction transaction=null;
        try {
            transaction= session.beginTransaction();
            String maxId="select max (p.id) from Habilidad p";
            Integer id=(Integer) session.createQuery(maxId)
                    .uniqueResult();
            int nuevoId=id+1;
            Habilidad habilidad=new Habilidad(nuevoId,nombre,descripcion);
            session.persist(habilidad);
            transaction.commit();
            System.out.println("creada exitosamente");
        } catch (Exception e) {
            System.out.println("error en la creacion de la habilidad");
        }
    }
}

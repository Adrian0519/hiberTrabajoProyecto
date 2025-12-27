package org.example.Repositorios;

import org.example.entidades.Habilidad;
import org.example.entidades.Personaje;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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

    public void borrarHabilidad(String borrar){
        Transaction transaction=null;
        String buscarhablidad="from Habilidad h where h.nombre = :nombre";
        try {
            transaction= session.beginTransaction();
            Habilidad habilidad= (Habilidad) session.createQuery(buscarhablidad)
                    .setParameter("nombre",borrar)
                    .uniqueResult();
            List <Personaje> list=habilidad.getPersonajes();
            for (int i = 0; i <list.size() ; i++) {
                list.get(i).getHabilidades().remove(habilidad);
            }
            habilidad.getPersonajes().clear();
            session.remove(habilidad);
            transaction.commit();
            System.out.println("Eliminacion realizada");
        } catch (Exception e) {
            System.out.println("Error en la eliminacion");
        }
    }

    public void modificarHabilidad(int id,String nombre, String des){
        Transaction transaction=null;
        String sentencia="select h from Habilidad h where h.id=:id";
        try {
            transaction= session.beginTransaction();
            Habilidad habilidad=(Habilidad) session.createQuery(sentencia)
                    .setParameter("id",id)
                    .uniqueResult();
            if (habilidad==null){
                System.out.println("No hay ninguna Habilidad con dicha id ");
                return;
            }
            habilidad.setNombre(nombre);
            habilidad.setDescripcion(des);
            transaction.commit();
            System.out.println("Se actualizaron los dastos");
        } catch (Exception e) {
            System.out.println("Error en la modificacion " +e.getMessage());
        }
    }
}

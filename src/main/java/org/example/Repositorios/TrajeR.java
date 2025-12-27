package org.example.Repositorios;

import org.example.entidades.Personaje;
import org.example.entidades.Traje;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TrajeR {
    private Session session;

    public TrajeR(Session session) {
        this.session = session;
    }

    public void asignarTraje(String traje, String personaje){
        Transaction transaction=null;
        String senciaTraje="select t from Traje t where t.especificacion =:especificacion";
        String sentenciaPersonaje="select p from Personaje p where p.nombre = :nombre";
        String sentenciaId="select max(t.id) from Traje t";
        try {
            transaction= session.beginTransaction();
            Personaje personaje1=(Personaje)session.createQuery(sentenciaPersonaje)
                    .setParameter("nombre",personaje)
                    .uniqueResult();
                    if (personaje1==null){
                        System.out.println("El personaje indicado no existe");
                        return;
                    }
            Traje traje1=(Traje) session.createQuery(senciaTraje)
                    .setParameter("especificacion",traje)
                    .uniqueResult();
                    if (traje1!=null){
                        System.out.println("El traje ya existe");
                        return;
                    }
                    Integer integer=(Integer) session.createQuery(sentenciaId)
                            .uniqueResult();
                    int id=integer+1;
                    Traje nTraje=new Traje(id,traje);
                    Traje trajeAnterior=personaje1.getTraje();
                    if (trajeAnterior!=null){
                        personaje1.setTraje(null);
                        trajeAnterior.setPersonaje(null);
                    }
                    personaje1.setTraje(nTraje);
                    nTraje.setPersonaje(personaje1);
                    session.persist(nTraje);
                    transaction.commit();
            System.out.println("Se le asigno un traje");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        //VER SI SE PUEDE IMPLEMENTAR BIEN

    /**
     *  private void crearTraje(String especificacion,Transaction transaction){
     *         String sentenciaId="select max(t.id)from Traje t";
     *         try {
     *             transaction= session.beginTransaction();
     *             Integer integer=(Integer)session.createQuery(sentenciaId)
     *                     .uniqueResult();
     *             int id=integer+1;
     *             Traje traje=new Traje(id,especificacion);
     *             session.persist(traje);
     *             transaction.commit();
     *             System.out.println("se creo el traje");
     *         } catch (Exception e) {
     *             System.out.println("error en la creacion del traje ");
     *         }
     *     }
     */
}

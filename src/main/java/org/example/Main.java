package org.example;

import org.example.Repositorios.EventosR;
import org.example.Repositorios.HabilidadR;
import org.example.Repositorios.PersonajeR;
import org.example.Repositorios.TrajeR;
import org.example.entidades.Personaje;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int opciones=999999;
        Session session = HibernateUtil.get().openSession();
        PersonajeR personajeR=new PersonajeR(session);
        EventosR eventosR=new EventosR(session);
        HabilidadR habilidadR=new HabilidadR(session);
        TrajeR trajeR=new TrajeR(session);
        while (opciones!=0){
            System.out.println(
                            "1-Crear un personaje.\n" +
                            "2-Modificar un personaje.\n" +
                            "3-Borrar un personaje.\n" +
                            "4-Crear una habilidad.\n" +
                            "5-borrar (por nombre) una habilidad.\n" +
                            "6-modificar una habilidad.\n" +
                            "7-Asignar una habilidad a un personaje (la asignación se hará a partir del nombre del personaje y el nombre de la habilidad).\n" +
                            "8-Registrar la participación de un personaje en un evento con un rol concreto y una fecha (Se pedirá por teclado el nombre del personaje, el nombre del evento, el rol y la fecha de participación).\n" +
                            "9-Cambiar el traje de un personaje (El método recibirá el nombre del personaje, la especificación del nuevo traje y el nuevo traje).\n" +
                            "10-Mostrar los datos de un personaje (id, nombre, alias, traje, habilidades, eventos en los que ha participado con su rol y fecha).\n" +
                            "11-Mostrar los personajes que participaron en un evento determinado.\n" +
                            "12-Mostrar cuantos personajes tienen una habilidad concreta.\n"+
                            "0-Salir del programa.");
            opciones=scanner.nextInt();
            scanner.nextLine();
            switch (opciones){
                case 1:
                    System.out.println("Dime el nombre");
                    String nombre= scanner.nextLine();
                    System.out.println("Dime el alias");
                    String alias=scanner.nextLine();
                    personajeR.crearPersonaje(nombre,alias);
                    break;
                case 2:
                    System.out.println("Dime el id del personaje ");
                    int id= scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserta el nombre del personaje");
                    String nombreUp=scanner.nextLine();
                    System.out.println("Dime su alias");
                    String aliasUp= scanner.nextLine();
                    personajeR.actualizarPersonaje(id,nombreUp,aliasUp);
                    break;
                case 3:
                    System.out.println("Pasame el id del personaje a borrar");
                    int dId= scanner.nextInt();
                    scanner.nextLine();
                    personajeR.borrarPersonaje(dId);
                    break;
                case 4:
                    System.out.println("Dime el nombre de la habilidad");
                    String nombreH= scanner.nextLine();
                    System.out.println("En que consiste");
                    String descripcionH= scanner.nextLine();
                    habilidadR.crearHabilidadees(nombreH,descripcionH);
                    break;
                case 5:
                    System.out.println("Dime el nombre de la habilidad ");
                    String habilidadNombre= scanner.nextLine();
                    habilidadR.borrarHabilidad(habilidadNombre);
                    break;
                case 6:
                    System.out.println("Dime el id de la habilidad que deseas modificar");
                    int idhablidadMod= scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Dime su nombre");
                    String nNombre=scanner.nextLine();
                    System.out.println("Y su descripcion");
                    String nDesc= scanner.nextLine();
                    habilidadR.modificarHabilidad(idhablidadMod,nNombre,nDesc);
                case 7:
                    System.out.println("Dime la habilidad que quieres asignar");
                    String habilidadAsignable=scanner.nextLine();
                    System.out.println("Dime el usuario de la habilidad");
                    String nombreEjecutor= scanner.nextLine();
                    habilidadR.asignarHabilidadPersonaje(nombreEjecutor,habilidadAsignable);
                    break;
                case 8:
                    System.out.println("Dime el nombre del particpante");
                    String nombreParticipante=scanner.nextLine();
                    System.out.println("Donde participa");
                    String eventoParticipa=scanner.nextLine();
                    System.out.println("Que rol");
                    String rol= scanner.nextLine();
                    System.out.println("Y ahora la fecha");
                    String fecha=scanner.nextLine();
                    LocalDate localDate=LocalDate.parse(fecha);
                    eventosR.participacionEvento(nombreParticipante,eventoParticipa,rol,localDate);
                    break;
                case 9:
                    System.out.println("Dime el nombre del personaje");
                    String asignarTraje=scanner.nextLine();
                    System.out.println("Que traje le vas a equipar");
                    String especificacionTraje=scanner.nextLine();
                    trajeR.asignarTraje(especificacionTraje,asignarTraje);
                    break;
                case 10:
                    System.out.println("Dime la id");
                    int idMostrar=scanner.nextInt();
                    scanner.nextLine();
                    personajeR.mostrarPersonaje(idMostrar);
                case 11:
                    System.out.println("Dime en que evento quieres mirar");
                    String nombreEvento= scanner.nextLine();
                    eventosR.participaEnEvento(nombreEvento);
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
            }
        }
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}

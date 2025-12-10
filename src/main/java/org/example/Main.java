package org.example;

import org.hibernate.Session;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int opciones=999999;
        Session session = HibernateUtil.get().openSession();
        while (opciones!=0){
            System.out.println(
                            "1-Crear un personaje.\n" +
                            "2-Modificar un personaje" +
                            "3-Borrar un personaje" +
                            "4-Crear una habilidad " +
                            "5-borrar (por nombre) una habilidad" +
                            "6-modificar una habilidad.\n" +
                            "7-Asignar una habilidad a un personaje (la asignación se hará a partir del nombre del personaje y el nombre de la habilidad).\n" +
                            "8-Registrar la participación de un personaje en un evento con un rol concreto y una fecha (Se pedirá por teclado el nombre del personaje, el nombre del evento, el rol y la fecha de participación).\n" +
                            "9-Cambiar el traje de un personaje (El método recibirá el nombre del personaje, la especificación del nuevo traje y el nuevo traje).\n" +
                            "10-Mostrar los datos de un personaje (id, nombre, alias, traje, habilidades, eventos en los que ha participado con su rol y fecha).\n" +
                            "11-Mostrar los personajes que participaron en un evento determinado.\n" +
                            "12-Mostrar cuantos personajes tienen una habilidad concreta.\n"+
                            "0-Salir del programa.");
            opciones=scanner.nextInt();
        }
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}

package org.example.Repositorios;

import org.hibernate.Session;

public class EventosR {
    private Session session;

    public EventosR(Session session) {
        this.session = session;
    }
}

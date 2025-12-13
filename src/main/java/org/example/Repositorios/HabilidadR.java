package org.example.Repositorios;

import org.hibernate.Session;

public class HabilidadR {
    private Session session;

    public HabilidadR(Session session) {
        this.session = session;
    }
}

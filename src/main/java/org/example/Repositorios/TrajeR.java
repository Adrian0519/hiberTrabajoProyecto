package org.example.Repositorios;

import org.hibernate.Session;

public class TrajeR {
    private Session session;

    public TrajeR(Session session) {
        this.session = session;
    }
}

package com.hibernate;

import com.hibernate.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            // create session, open transaction and save test entity to db
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
        Session session = sessionFactory.openSession();
        try {
            UserRepository.saveUser(session);
            RoomRepository.saveRoom(session);
            MessageRepository.saveMessage(session);
            RoomRepository.addUserToRoom(session);
        } finally {
            session.close();
            sessionFactory.close();
        }
    }



}

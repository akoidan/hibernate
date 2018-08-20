package com.hibernate;

import com.hibernate.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepository {

    private static final Logger logger = Logger.getLogger(App.class);

    public static void saveUser(Session session) {
        Transaction tx = session.beginTransaction();
        try {
            User user = new User();
            user.setName("deathangel908");
            user.setSurname("surname");
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("cannot commit transaction", e);
        }
    }

    public static User getUserById(Session session) {
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class, 1L);
            System.out.println(user);
            return user;
        } catch (Exception e) {
            tx.rollback();
            logger.error("cannot commit transaction", e);
            return null;
        }
    }

    public static void updateUser(Session session) {
        Transaction tx = session.beginTransaction();
        try {
            User user = new User();
            user.setId(1L);
            user.setName("deathangel908");
            user.setSurname("surname");
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("cannot commit transaction", e);
        }
    }


    public static void updateUserColumn(Session session) {
        Transaction tx = session.beginTransaction();

        String hqlUpdate = "update User u set u.name = :newName where u.id = :id";
// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
        int updatedEntities = session.createQuery( hqlUpdate )
                .setString( "newName", "deathangel909" )
                .setLong( "id", 1 )
                .executeUpdate();
        tx.commit();
    }


    public static void deleteUser(Session session) {
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setId(1L);
        tx.begin();
        session.delete(user);
        tx.commit();
    }
}

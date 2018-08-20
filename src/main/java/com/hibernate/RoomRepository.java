package com.hibernate;

import com.hibernate.model.Message;
import com.hibernate.model.Room;
import com.hibernate.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoomRepository {

    private static final Logger logger = Logger.getLogger(App.class);

    public static void saveRoom(Session session) {
        Transaction tx = session.beginTransaction();
        try {
            Room r = new Room();
            r.setName("main");
            session.save(r);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("cannot commit transaction", e);
        }
    }


    public static Room getRoomById(Session session) {
        Transaction tx = session.beginTransaction();
        try {
            Room room = session.get(Room.class, 2L);
            System.out.println(room);
            return room;
        } catch (Exception e) {
            tx.rollback();
            logger.error("cannot commit transaction", e);
            return null;
        }
    }

    public static void addUserToRoom(Session session) {
        Transaction tx = session.beginTransaction();
        try {
            User userById = UserRepository.getUserById(session);
            Room roomById = getRoomById(session);
            userById.getRooms().add(roomById);
            session.update(userById);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("cannot commit transaction", e);
        }
    }
}

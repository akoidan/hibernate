package com.hibernate;

import com.hibernate.model.Message;
import com.hibernate.model.Room;
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

    public static void AddUserToRoom(Session session) {
        Transaction tx = session.beginTransaction();
        try {
            Message m = new Message();
            m.setText("message");
            Room room = new Room();
            room.setId(1L);
            m.setRoom(room);
            session.save(m);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("cannot commit transaction", e);
        }
    }
}

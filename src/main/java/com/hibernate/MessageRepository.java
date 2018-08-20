package com.hibernate;

import com.hibernate.model.Message;
import com.hibernate.model.Room;
import com.hibernate.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MessageRepository {

    private static final Logger logger = Logger.getLogger(App.class);

    public static void saveMessage(Session session) {
        Transaction tx = session.beginTransaction();
        try {
            Message m = new Message();
            m.setText("message");
            Room room = new Room();
            room.setId(2L);
            m.setRoom(room);
            session.save(m);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("cannot commit transaction", e);
        }
    }
}

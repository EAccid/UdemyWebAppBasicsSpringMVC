package com.eaccid.spring.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Component("messagesDao")
public class MessagesDao {

    @Autowired
    private SessionFactory sessionFactory;

    public MessagesDao() {
        System.out.println("Successfully loaded messages Dao");
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Message> getMessages() {
        Criteria crit = session().createCriteria(Message.class);
        crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    public List<Message> getMessages(String username) {

        Criteria crit = session().createCriteria(Message.class);
        crit.createAlias("user", "u");
        crit.add(Restrictions.eq("u.enabled", true));
        crit.add(Restrictions.eq("u.username", username));
        return crit.list();

    }

    public void saveOrUpdate(Message message) {
        System.out.println(" - > Save Or Update: \n" + message);
        session().save(message);
    }

    public boolean delete(int id) {
        Query query = session().createQuery("delete from Message where id=:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public Message getMessage(int id) {

        Criteria crit = session().createCriteria(Message.class);

        crit.createAlias("user", "u");

        crit.add(Restrictions.eq("u.enabled", true));
        crit.add(Restrictions.idEq(id));

        return (Message) crit.uniqueResult();
    }

}

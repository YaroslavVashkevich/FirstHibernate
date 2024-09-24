package ru.javabegin.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javabegin.hibernate.HibernateUtil;
import ru.javabegin.hibernate.dao.interfaces.objects.ActivityDAO;
import ru.javabegin.hibernate.entity.Activity;
import ru.javabegin.hibernate.entity.User;

public class ActivityDAOImpl implements ActivityDAO {

    @Override
    public Activity get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Activity user = session.get(Activity.class, id);
        session.close();
        return user;
    }

    @Override
    public void update(Activity obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        throw new IllegalStateException("you cant delete activity by yourself - only DB can");
    }

    @Override
    public void add(Activity obj) {
        throw new IllegalStateException("you cant add activity by yourself - only DB's trigger can");
    }

    @Override
    public Activity getByUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Activity> query = session.createQuery("FROM Activity where user.email like :email", Activity.class);
        query.setParameter("email", "%" + email + "%");
        Activity stat = query.uniqueResult();
        session.close();
        return stat;
    }

    @Override
    public Activity getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Activity> query = session.createQuery("FROM Activity where user.email like :email", Activity.class);
        query.setParameter("email", "%" + user.getEmail() + "%");
        Activity stat = query.uniqueResult();
        session.close();
        return stat;
    }
}

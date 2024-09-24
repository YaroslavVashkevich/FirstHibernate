package ru.javabegin.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javabegin.hibernate.HibernateUtil;
import ru.javabegin.hibernate.dao.interfaces.objects.TaskDAO;
import ru.javabegin.hibernate.entity.Task;

import java.util.List;

public class TaskDAOImpl implements TaskDAO {
    @Override
    public List<Task> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task", Task.class);
        List<Task> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public List<Task> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task t WHERE t.user.email LIKE:email", Task.class);
        query.setParameter("email", "%" + email + "%");
        List<Task> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public Task get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Task user = session.get(Task.class, id);
        session.close();
        return user;
    }

    @Override
    public void update(Task obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Task user = new Task();// создаем временный объект и заполняем его id
        user.setId(id);
        session.remove(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Task obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Task> find(boolean completed, String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task t where t.user.email like :email and t.completed = :completed", Task.class);
        query.setParameter("email", "%" + email + "%");
        query.setParameter("completed", completed);
        List<Task> list = query.getResultList();
        session.close();
        return list;
    }
}

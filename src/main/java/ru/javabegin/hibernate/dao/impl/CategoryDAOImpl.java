package ru.javabegin.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javabegin.hibernate.HibernateUtil;
import ru.javabegin.hibernate.dao.interfaces.objects.CategoryDAO;
import ru.javabegin.hibernate.entity.Category;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Category> query = session.createQuery("FROM Category", Category.class);
        List<Category> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public List<Category> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Category> query = session.createQuery("FROM Category p where p.user.email like :email", Category.class);
        query.setParameter("email", "%" + email + "%");
        List<Category> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public Category get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category user = session.get(Category.class, id);
        session.close();
        return user;
    }

    @Override
    public void update(Category obj) {
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
        Category u = new Category();
        u.setId(id);
        session.remove(u);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Category obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        session.close();
    }
}

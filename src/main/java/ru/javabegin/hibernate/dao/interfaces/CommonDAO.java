package ru.javabegin.hibernate.dao.interfaces;

public interface CommonDAO<T> {

    T get(long id);

    void update(T obj);

    void delete(long id);

    void add(T obj);

}

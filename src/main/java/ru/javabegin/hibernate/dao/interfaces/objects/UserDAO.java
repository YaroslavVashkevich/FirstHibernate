package ru.javabegin.hibernate.dao.interfaces.objects;

import ru.javabegin.hibernate.dao.interfaces.CommonDAO;
import ru.javabegin.hibernate.dao.interfaces.FindDAO;
import ru.javabegin.hibernate.entity.User;


public interface UserDAO extends CommonDAO<User>, FindDAO<User> {

    // получение только одного пользователя по email
    User getByEmail(String email);
}

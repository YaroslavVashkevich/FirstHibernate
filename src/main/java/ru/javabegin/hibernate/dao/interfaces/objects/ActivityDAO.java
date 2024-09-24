package ru.javabegin.hibernate.dao.interfaces.objects;

import ru.javabegin.hibernate.dao.interfaces.CommonDAO;
import ru.javabegin.hibernate.entity.Activity;
import ru.javabegin.hibernate.entity.User;


public interface ActivityDAO extends CommonDAO<Activity> {

    // получение активности конкретного пользователя (несколькими способами)
    Activity getByUser(String email);
    Activity getByUser(User user);

}

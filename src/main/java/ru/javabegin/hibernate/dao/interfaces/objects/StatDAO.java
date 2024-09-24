package ru.javabegin.hibernate.dao.interfaces.objects;

import ru.javabegin.hibernate.entity.Stat;
import ru.javabegin.hibernate.entity.User;


public interface StatDAO {

    // получение статистики конкретного пользователя (несколькими способами)
    Stat getByUser(String email);
    Stat getByUser(User user);
}

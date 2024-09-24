package ru.javabegin.hibernate.dao.interfaces.objects;

import ru.javabegin.hibernate.dao.interfaces.CommonDAO;
import ru.javabegin.hibernate.dao.interfaces.FindDAO;
import ru.javabegin.hibernate.entity.Task;

import java.util.List;

public interface TaskDAO extends CommonDAO<Task>, FindDAO<Task> {

    // найти все завершенные или незавершенные задачи по любому пользователю
    List<Task> find(boolean completed, String email);

}

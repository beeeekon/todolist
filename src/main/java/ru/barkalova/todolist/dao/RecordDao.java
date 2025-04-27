package ru.barkalova.todolist.dao;

import java.util.Calendar;
import java.util.List;
import ru.barkalova.todolist.entity.Record;

public interface RecordDao {
    public List<ru.barkalova.todolist.entity.Record> showAll();
    public ru.barkalova.todolist.entity.Record getById(int id);
    public List<ru.barkalova.todolist.entity.Record> getByName(String name);
    public List<ru.barkalova.todolist.entity.Record> getByCalendar(Calendar deadline);
    public List<ru.barkalova.todolist.entity.Record> getByIsCompleted(boolean isCompleted);

    public void save(Record record);
    public void update(int id, Record updateRecord);
    public void delete(int id);
}

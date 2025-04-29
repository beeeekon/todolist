package ru.barkalova.todolist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.barkalova.todolist.config.HibernateSessionFactoryUtil;
import ru.barkalova.todolist.entity.Record;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.List;

@Component
public class RecordDaoImpl implements RecordDao {
    private final JdbcTemplate jdbcTemplate;
    //private static int RECORD_COUNT;


    @Autowired
    public RecordDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Record> showAll() {
        return jdbcTemplate.query("select * from \"RecordSchema\".\"RecordTable\"",
                new RecordMapper());
    }

    @Override
    public Record getById(int id) {
        List<Record> records= jdbcTemplate.query("select * from \"RecordSchema\".\"RecordTable\" " +
                        "where record_id=?",
                new Object[]{id},
                new RecordMapper());
        Record record = new Record();
        if(records.size()==1)
            record= records.get(0);
        return record;
       // return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Record.class, id);
    }

    @Override
    public List<Record> getByName(String name) {
        return jdbcTemplate.query("select * from \"RecordSchema\".\"RecordTable\" " +
                        "where record_name=?",
                new Object[]{name},
                new RecordMapper());
    }

    @Override
    public List<Record> getByCalendar(Calendar deadline) {
        return jdbcTemplate.query("select * from \"RecordSchema\".\"RecordTable\" " +
                        "where record_deadline=?",
                new Object[]{deadline},
                new RecordMapper());
    }

    @Override
    public List<Record> getByIsCompleted(boolean isCompleted) {
        return jdbcTemplate.query("select * from \"RecordSchema\".\"RecordTable\" " +
                        "where record_is_completed=?",
                new Object[]{isCompleted},
                new RecordMapper());
    }

    @Override
    public void save(Record record) {
        jdbcTemplate.update("insert into \"RecordSchema\".\"RecordTable\" VALUES(?, ?, ?)",
                record.getName(), record.getDeadline(), record.isCompleted());

    }

    @Override
    public void update(int id, Record updateRecord) {
        jdbcTemplate.update("update \"RecordSchema\".\"RecordTable\" " +
                        "set record_name=?, record_deadline=?, record_is_completed=? " +
                        "where record_id=?",
                updateRecord.getName(), updateRecord.getDeadline(), updateRecord.isCompleted(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from \"RecordSchema\".\"RecordTable\" " +
                "where record_id=?", id);
    }
}

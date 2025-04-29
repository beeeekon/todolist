package ru.barkalova.todolist.dao;


import org.springframework.jdbc.core.RowMapper;
import ru.barkalova.todolist.entity.Record;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RecordMapper implements RowMapper<Record> {

    @Override
    public Record mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Record record=new Record();
        record.setId(resultSet.getInt("record_id"));
        record.setName(resultSet.getString("record_name"));

        Timestamp timestamp = resultSet.getTimestamp("record_deadline");
        Calendar deadline=new GregorianCalendar();
        deadline.set(Calendar.YEAR, timestamp.getYear());
        deadline.set(Calendar.MONTH, timestamp.getMonth());
        deadline.set(Calendar.DAY_OF_MONTH, timestamp.getDay());
        deadline.set(Calendar.HOUR_OF_DAY, timestamp.getHours());
        deadline.set(Calendar.MINUTE, timestamp.getMinutes());
        deadline.set(Calendar.SECOND, timestamp.getSeconds());

        record.setDeadline((GregorianCalendar) deadline);
        record.setCompleted(resultSet.getBoolean("record_is_completed"));
        return record;
    }
}

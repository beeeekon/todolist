package ru.barkalova.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.barkalova.todolist.entity.Record;
import ru.barkalova.todolist.repository.RecordRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> showAll(){
        return recordRepository.showAll();
    }

    public Record getById(int id){
        return recordRepository.getById(id);
    }

    public List<Record> getByName(String name){
        return recordRepository.getByName(name);
    }

    public List<Record> getByCalendar(Calendar deadline){
        return recordRepository.getByCalendar(deadline);
    }
    public List<Record> getByIsCompleted(boolean isCompleted){
        return recordRepository.getByIsCompleted(isCompleted);
    }

    public void save(Record record){
        recordRepository.save(record);
    }

    public void update(int id, Record record){
        recordRepository.update(id, record);
    }

    public void delete(int id){
        recordRepository.delete(id);
    }

    public void updateIsCompleted(int id, boolean isCompleted){
        recordRepository.updateIsCompleted(id,isCompleted);
    }



}

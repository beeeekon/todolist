package ru.barkalova.todolist.repository;

import org.springframework.stereotype.Repository;
import ru.barkalova.todolist.dao.RecordDao;
import ru.barkalova.todolist.entity.Record;

import java.util.Calendar;
import java.util.List;

//если не использовать dao то можно было запросы вот так завернуть:
//public interface UserRepository extends JpaRepository<User, Long>
// @Query(value = "select * from users where email = :email", nativeQuery = true)
// Optional<User> findByEmail(String email);
//это ^^^ с урока https://github.com/EVG-A/demo/blob/main/src/main/java/com/example/demo/repository/UserRepository.java

@Repository
public class RecordRepository {
     private final RecordDao recordDao;

    public RecordRepository(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public List<Record> showAll(){
        return recordDao.showAll();
    }
    public Record getById(int id){
        return recordDao.getById(id);
    }

    public List<Record> getByName(String name){
        return recordDao.getByName(name);
    }
    public List<Record> getByCalendar(Calendar deadline){
        return recordDao.getByCalendar(deadline);
    }
    public List<Record> getByIsCompleted(boolean isCompleted){
        return recordDao.getByIsCompleted(isCompleted);
    }

    public void save(Record record){
        recordDao.save(record);
    }

    public void update(int id,Record record){
        recordDao.update(id, record);
    }
    public void delete(int id){
        recordDao.delete(id);
    }

    public void updateIsCompleted(int id, boolean isCompleted){
        Record record = recordDao.getById(id);
        record.setCompleted(isCompleted);
        recordDao.update(id,record);
    }

}

package ru.barkalova.todolist.entity;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
@Table(name="RecordShema.RecordTable")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="record_id")
    private int id;

    @Column(name="record_name")
    private String name;

    @Column(name= "record_deadline")
    private Calendar deadline;

    @Column(name= "record_is_completed")
    private boolean isCompleted;

    public Record(){
        this.id = 0;
        this.name = "pypypy";
        this.deadline = new GregorianCalendar(2000,01,01,12,01,01);
        this.isCompleted = false;
    }

    public Record(String name, Calendar deadline, boolean isCompleted) {
        this.name = name;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }
//    public Record(int id, String name, Calendar deadline, boolean isCompleted) {
//        this.id = id;
//        this.name = name;
//        this.deadline = deadline;
//        this.isCompleted = isCompleted;
//    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }


    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(GregorianCalendar deadline) {
        this.deadline = deadline;
    }


    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deadline=" + deadline.get(Calendar.YEAR) +"-"+
                deadline.get(Calendar.MONTH) +"-"+
                deadline.get(Calendar.DAY_OF_MONTH) +" "+
                deadline.get(Calendar.HOUR) +":"+
                deadline.get(Calendar.MINUTE) +":"+
                deadline.get(Calendar.SECOND) +
                ", isCompleted=" + isCompleted +
                '}';
    }
}

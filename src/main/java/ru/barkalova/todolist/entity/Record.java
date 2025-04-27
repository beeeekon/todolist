package ru.barkalova.todolist.entity;


import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Record {
    private int id;
    private String name;
    private Calendar deadline;
    private boolean isCompleted;

    public Record(){

    }

    public Record(int id, String name, Calendar deadline, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }


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
}

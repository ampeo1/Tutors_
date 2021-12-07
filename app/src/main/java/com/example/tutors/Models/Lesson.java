package com.example.tutors.Models;

import java.util.Date;

public class Lesson {
    private String id;
    public String topic;
    public String tutorId;
    public String studentId;
    public Date dateEvent;
    public ItemsTypes type;
    public int mark;

    public Lesson(){

    }

    public Lesson(String id, String topic, String tutorId, String studentId, Date dateEvent, ItemsTypes type, int mark) {
        this.id = id;
        this.topic = topic;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.dateEvent = dateEvent;
        this.type = type;
        this.mark = mark;
    }

    public Lesson(String topic, String tutorId, String studentId, Date dateEvent, ItemsTypes type, int mark) {
        this.topic = topic;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.dateEvent = dateEvent;
        this.type = type;
        this.mark = mark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

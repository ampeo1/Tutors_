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
    public LessonStatus lessonStatus;

    public Lesson(){

    }

    public Lesson(String id, int mark, LessonStatus lessonStatus, Lesson oldLesson) {
        this.id = id;
        this.mark = mark;
        this.lessonStatus = lessonStatus;

        this.topic = oldLesson.topic;
        this.tutorId = oldLesson.tutorId;
        this.studentId = oldLesson.studentId;
        this.dateEvent = oldLesson.dateEvent;
        this.type = oldLesson.type;
    }

    public Lesson(String topic, String tutorId, String studentId, Date dateEvent, ItemsTypes type) {
        this.topic = topic;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.dateEvent = dateEvent;
        this.type = type;
        this.mark = -1;
        this.lessonStatus = LessonStatus.PLANNED;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

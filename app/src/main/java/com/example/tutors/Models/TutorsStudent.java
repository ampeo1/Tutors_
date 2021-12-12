package com.example.tutors.Models;

public class TutorsStudent {
    public String studentId;
    public Boolean status;

    public TutorsStudent()
    {

    }

    public TutorsStudent(Boolean status, String studentId)
    {
        this.studentId = studentId;
        this.status = status;
    }
}
